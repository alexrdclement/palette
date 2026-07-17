package com.alexrdclement.palette.app.theme.primitive.interaction

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.core.ButtonDemo
import com.alexrdclement.palette.components.demo.core.ButtonDemoControl
import com.alexrdclement.palette.components.demo.core.rememberButtonDemoControl
import com.alexrdclement.palette.components.demo.core.rememberButtonDemoState
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.modifiers.ColorSplitMode
import com.alexrdclement.palette.modifiers.NoiseColorMode
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.IndicationPrimitiveToken
import com.alexrdclement.palette.theme.primitive.IndicationTokenSet
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun IndicationScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberPrimitiveIndicationScreenState(themeState = themeController)
    val buttonDemoState = rememberButtonDemoState(
        buttonStyleInitial = PaletteTheme.component.core.button.primary.copy(
            indication = themeController.primitive.indication.getValue(state.subject).toIndication(),
        ),
    )
    val buttonDemoControl = rememberButtonDemoControl(buttonDemoState)
    val control = rememberPrimitiveIndicationScreenControl(
        state = state,
        themeController = themeController,
        buttonDemoControl = buttonDemoControl,
    )

    // Preview the selected primitive on the demo button: keep the themed style for the chosen button
    // variant and swap in the selected primitive's indication. Keyed on the token set (a stable data
    // class) and the button variant, so it re-syncs when either changes.
    val baseButtonStyle by rememberUpdatedState(
        PaletteTheme.component.core.button[buttonDemoState.style],
    )
    val tokenSet = state.tokenSet(state.subject)
    LaunchedEffect(tokenSet, buttonDemoState.style) {
        buttonDemoControl.updateStyle(baseButtonStyle.copy(indication = tokenSet.toIndication()))
    }

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Indication",
                onNavigateUp = onNavigateUp,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        Demo(
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            this@Demo.ButtonDemo(
                state = buttonDemoState,
                control = buttonDemoControl,
            )
        }
    }
}

@Composable
fun rememberPrimitiveIndicationScreenState(
    themeState: ThemeState,
): PrimitiveIndicationScreenState {
    return rememberSaveable(
        themeState,
        saver = PrimitiveIndicationScreenStateSaver(themeState),
    ) {
        PrimitiveIndicationScreenState(themeState = themeState)
    }
}

@Stable
class PrimitiveIndicationScreenState(
    val themeState: ThemeState,
    subjectInitial: IndicationPrimitiveToken = IndicationPrimitiveToken.ColorSplit,
) {
    var subject by mutableStateOf(subjectInitial)

    fun tokenSet(token: IndicationPrimitiveToken): IndicationTokenSet =
        themeState.primitive.indication.getValue(token)
}

private const val subjectKey = "subject"

fun PrimitiveIndicationScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state -> mapOf(subjectKey to state.subject.name) },
    restore = { map ->
        PrimitiveIndicationScreenState(
            themeState = themeState,
            subjectInitial = (map[subjectKey] as? String)
                ?.let { IndicationPrimitiveToken.valueOf(it) }
                ?: IndicationPrimitiveToken.ColorSplit,
        )
    }
)

@Composable
fun rememberPrimitiveIndicationScreenControl(
    state: PrimitiveIndicationScreenState,
    themeController: ThemeController,
    buttonDemoControl: ButtonDemoControl,
): PrimitiveIndicationScreenControl {
    return remember(state, themeController, buttonDemoControl) {
        PrimitiveIndicationScreenControl(
            state = state,
            themeController = themeController,
            buttonDemoControl = buttonDemoControl,
        )
    }
}

@Stable
class PrimitiveIndicationScreenControl(
    val state: PrimitiveIndicationScreenState,
    val themeController: ThemeController,
    val buttonDemoControl: ButtonDemoControl,
) {
    private val indicationControl = enumControl(
        name = "Indication",
        values = { IndicationPrimitiveToken.entries },
        selectedValue = { state.subject },
        onValueChange = { state.subject = it },
    )

    private val colorInvertHoverAmountControl = Control.Slider(
        name = "Hover amount",
        value = { colorInvert().hoverAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.ColorInvert>(IndicationPrimitiveToken.ColorInvert) {
                it.copy(hoverAmount = amount)
            }
        },
        valueRange = { 0f..1f },
    )

    private val colorInvertPressAmountControl = Control.Slider(
        name = "Press amount",
        value = { colorInvert().pressAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.ColorInvert>(IndicationPrimitiveToken.ColorInvert) {
                it.copy(pressAmount = amount)
            }
        },
        valueRange = { 0f..1f },
    )

    private val colorSplitColorModeControl = enumControl(
        name = "Color mode",
        values = { ColorSplitMode.entries },
        selectedValue = { colorSplit().colorMode },
        onValueChange = { colorMode ->
            updateIndication<IndicationTokenSet.ColorSplit>(IndicationPrimitiveToken.ColorSplit) {
                it.copy(colorMode = colorMode)
            }
        },
    )

    private val colorSplitHoverAmountControl = Control.Slider(
        name = "Hover amount",
        value = { colorSplit().hoverAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.ColorSplit>(IndicationPrimitiveToken.ColorSplit) {
                it.copy(hoverAmount = amount)
            }
        },
        valueRange = { -1f..1f },
    )

    private val colorSplitPressAmountControl = Control.Slider(
        name = "Press amount",
        value = { colorSplit().pressAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.ColorSplit>(IndicationPrimitiveToken.ColorSplit) {
                it.copy(pressAmount = amount)
            }
        },
        valueRange = { -1f..1f },
    )

    private val noiseHoverAmountControl = Control.Slider(
        name = "Hover amount",
        value = { noise().hoverAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.Noise>(IndicationPrimitiveToken.Noise) {
                it.copy(hoverAmount = amount)
            }
        },
        valueRange = { 0f..1f },
    )

    private val noisePressAmountControl = Control.Slider(
        name = "Press amount",
        value = { noise().pressAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.Noise>(IndicationPrimitiveToken.Noise) {
                it.copy(pressAmount = amount)
            }
        },
        valueRange = { 0f..1f },
    )

    private val noiseColorModeControl = enumControl(
        name = "Color mode",
        values = { NoiseColorMode.entries },
        selectedValue = { noise().colorMode },
        onValueChange = { colorMode ->
            updateIndication<IndicationTokenSet.Noise>(IndicationPrimitiveToken.Noise) {
                it.copy(colorMode = colorMode)
            }
        },
    )

    private val pixelateHoverSubdivisionsControl = Control.Slider(
        name = "Hover subdivisions",
        value = { pixelate().hoverSubdivisions.toFloat() },
        onValueChange = { subdivisions ->
            updateIndication<IndicationTokenSet.Pixelate>(IndicationPrimitiveToken.Pixelate) {
                it.copy(hoverSubdivisions = subdivisions.toInt())
            }
        },
        valueRange = { 0f..100f },
    )

    private val pixelatePressSubdivisionsControl = Control.Slider(
        name = "Press subdivisions",
        value = { pixelate().pressSubdivisions.toFloat() },
        onValueChange = { subdivisions ->
            updateIndication<IndicationTokenSet.Pixelate>(IndicationPrimitiveToken.Pixelate) {
                it.copy(pressSubdivisions = subdivisions.toInt())
            }
        },
        valueRange = { 0f..100f },
    )

    private val warpPressAmountControl = Control.Slider(
        name = "Press amount",
        value = { warp().pressAmount },
        onValueChange = { amount ->
            updateIndication<IndicationTokenSet.Warp>(IndicationPrimitiveToken.Warp) {
                it.copy(pressAmount = amount)
            }
        },
        valueRange = { -5f..5f },
    )

    private val warpPressRadiusControl = Control.Slider(
        name = "Press radius",
        value = { warp().pressRadius.value },
        onValueChange = { radius ->
            updateIndication<IndicationTokenSet.Warp>(IndicationPrimitiveToken.Warp) {
                it.copy(pressRadius = radius.dp)
            }
        },
        valueRange = { 0f..1000f },
    )

    private val buttonControls = Control.ControlColumn(
        name = "Demo button",
        controls = { buttonDemoControl.controls },
        expandedInitial = false,
    )

    val controls: PersistentList<Control>
        get() = buildList {
            add(indicationControl)
            when (state.subject) {
                IndicationPrimitiveToken.None -> Unit
                IndicationPrimitiveToken.ColorInvert -> {
                    add(colorInvertHoverAmountControl)
                    add(colorInvertPressAmountControl)
                }
                IndicationPrimitiveToken.ColorSplit -> {
                    add(colorSplitColorModeControl)
                    add(colorSplitHoverAmountControl)
                    add(colorSplitPressAmountControl)
                }
                IndicationPrimitiveToken.Noise -> {
                    add(noiseColorModeControl)
                    add(noiseHoverAmountControl)
                    add(noisePressAmountControl)
                }
                IndicationPrimitiveToken.Pixelate -> {
                    add(pixelateHoverSubdivisionsControl)
                    add(pixelatePressSubdivisionsControl)
                }
                IndicationPrimitiveToken.Warp -> {
                    add(warpPressAmountControl)
                    add(warpPressRadiusControl)
                }
            }
            add(buttonControls)
        }.toPersistentList()

    private fun colorInvert() =
        state.tokenSet(IndicationPrimitiveToken.ColorInvert) as IndicationTokenSet.ColorInvert

    private fun colorSplit() =
        state.tokenSet(IndicationPrimitiveToken.ColorSplit) as IndicationTokenSet.ColorSplit

    private fun noise() =
        state.tokenSet(IndicationPrimitiveToken.Noise) as IndicationTokenSet.Noise

    private fun pixelate() =
        state.tokenSet(IndicationPrimitiveToken.Pixelate) as IndicationTokenSet.Pixelate

    private fun warp() =
        state.tokenSet(IndicationPrimitiveToken.Warp) as IndicationTokenSet.Warp

    private inline fun <reified T : IndicationTokenSet> updateIndication(
        token: IndicationPrimitiveToken,
        crossinline transform: (T) -> IndicationTokenSet,
    ) {
        themeController.updatePrimitive {
            it.copy(indication = it.indication + (token to transform(it.indication.getValue(token) as T)))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        IndicationScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
