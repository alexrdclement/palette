package com.alexrdclement.palette.app.theme.primitive.interaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.ComponentDemo
import com.alexrdclement.palette.components.demo.ComponentDemoControl
import com.alexrdclement.palette.components.demo.rememberComponentDemoControl
import com.alexrdclement.palette.components.demo.rememberComponentDemoState
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
    val componentDemoState = rememberComponentDemoState()
    val componentDemoControl = rememberComponentDemoControl(componentDemoState)
    val control = rememberPrimitiveIndicationScreenControl(
        state = state,
        themeController = themeController,
        componentDemoControl = componentDemoControl,
    )

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
            val interactionSource = remember { MutableInteractionSource() }
            val indication = remember(state.tokenSet) { state.tokenSet.toIndication() }
            ComponentDemo(
                state = componentDemoState,
                control = componentDemoControl,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = indication,
                        onClick = {},
                    ),
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

    val tokenSet: IndicationTokenSet
        get() = tokenSet(subject)

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
    componentDemoControl: ComponentDemoControl,
): PrimitiveIndicationScreenControl {
    return remember(state, themeController, componentDemoControl) {
        PrimitiveIndicationScreenControl(
            state = state,
            themeController = themeController,
            componentDemoControl = componentDemoControl,
        )
    }
}

@Stable
class PrimitiveIndicationScreenControl(
    val state: PrimitiveIndicationScreenState,
    val themeController: ThemeController,
    val componentDemoControl: ComponentDemoControl,
) {
    private val indicationControl = enumControl(
        name = "Indication",
        values = { IndicationPrimitiveToken.entries },
        selectedValue = { state.subject },
        onValueChange = { state.subject = it },
    )

    private val colorInvertAmountControl = Control.Slider(
        name = "Amount",
        value = { colorInvert().amount },
        onValueChange = { amount ->
            updateIndication(IndicationPrimitiveToken.ColorInvert) {
                (it as IndicationTokenSet.ColorInvert).copy(amount = amount)
            }
        },
        valueRange = { 0f..1f },
    )

    private val colorSplitAmountControl = Control.Slider(
        name = "Amount",
        value = { colorSplit().amount },
        onValueChange = { amount ->
            updateIndication(IndicationPrimitiveToken.ColorSplit) {
                (it as IndicationTokenSet.ColorSplit).copy(amount = amount)
            }
        },
        valueRange = { -1f..1f },
    )

    private val colorSplitColorModeControl = enumControl(
        name = "Color mode",
        values = { ColorSplitMode.entries },
        selectedValue = { colorSplit().colorMode },
        onValueChange = { colorMode ->
            updateIndication(IndicationPrimitiveToken.ColorSplit) {
                (it as IndicationTokenSet.ColorSplit).copy(colorMode = colorMode)
            }
        },
    )

    private val noiseAmountControl = Control.Slider(
        name = "Amount",
        value = { noise().amount },
        onValueChange = { amount ->
            updateIndication(IndicationPrimitiveToken.Noise) {
                (it as IndicationTokenSet.Noise).copy(amount = amount)
            }
        },
        valueRange = { 0f..1f },
    )

    private val noiseColorModeControl = enumControl(
        name = "Color mode",
        values = { NoiseColorMode.entries },
        selectedValue = { noise().colorMode },
        onValueChange = { colorMode ->
            updateIndication(IndicationPrimitiveToken.Noise) {
                (it as IndicationTokenSet.Noise).copy(colorMode = colorMode)
            }
        },
    )

    private val pixelateSubdivisionsControl = Control.Slider(
        name = "Subdivisions",
        value = { pixelate().subdivisions.toFloat() },
        onValueChange = { subdivisions ->
            updateIndication(IndicationPrimitiveToken.Pixelate) {
                (it as IndicationTokenSet.Pixelate).copy(subdivisions = subdivisions.toInt())
            }
        },
        valueRange = { 0f..100f },
    )

    private val warpAmountControl = Control.Slider(
        name = "Amount",
        value = { warp().amount },
        onValueChange = { amount ->
            updateIndication(IndicationPrimitiveToken.Warp) {
                (it as IndicationTokenSet.Warp).copy(amount = amount)
            }
        },
        valueRange = { -5f..5f },
    )

    private val warpRadiusControl = Control.Slider(
        name = "Radius",
        value = { warp().radius.value },
        onValueChange = { radius ->
            updateIndication(IndicationPrimitiveToken.Warp) {
                (it as IndicationTokenSet.Warp).copy(radius = radius.dp)
            }
        },
        valueRange = { 0f..1000f },
    )

    private val componentControls = Control.ControlColumn(
        name = "Component",
        controls = { componentDemoControl.controls },
        expandedInitial = false,
    )

    val controls: PersistentList<Control>
        get() = buildList {
            add(indicationControl)
            when (state.subject) {
                IndicationPrimitiveToken.None -> Unit
                IndicationPrimitiveToken.ColorInvert -> add(colorInvertAmountControl)
                IndicationPrimitiveToken.ColorSplit -> {
                    add(colorSplitColorModeControl)
                    add(colorSplitAmountControl)
                }
                IndicationPrimitiveToken.Noise -> {
                    add(noiseAmountControl)
                    add(noiseColorModeControl)
                }
                IndicationPrimitiveToken.Pixelate -> add(pixelateSubdivisionsControl)
                IndicationPrimitiveToken.Warp -> {
                    add(warpAmountControl)
                    add(warpRadiusControl)
                }
            }
            add(componentControls)
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

    private fun updateIndication(
        token: IndicationPrimitiveToken,
        transform: (IndicationTokenSet) -> IndicationTokenSet,
    ) {
        themeController.updatePrimitive {
            it.copy(indication = it.indication + (token to transform(it.indication.getValue(token))))
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
