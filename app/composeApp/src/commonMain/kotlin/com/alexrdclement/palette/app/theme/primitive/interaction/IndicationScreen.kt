package com.alexrdclement.palette.app.theme.primitive.interaction

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
import com.alexrdclement.palette.modifiers.colorInvert
import com.alexrdclement.palette.modifiers.colorSplit
import com.alexrdclement.palette.modifiers.noise
import com.alexrdclement.palette.modifiers.pixelate
import com.alexrdclement.palette.modifiers.warp
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.primitive.IndicationPrimitiveToken
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun IndicationScreen(
    onNavigateUp: () -> Unit,
) {
    val state = rememberPrimitiveIndicationScreenState()
    val componentDemoState = rememberComponentDemoState()
    val componentDemoControl = rememberComponentDemoControl(componentDemoState)
    val control = rememberPrimitiveIndicationScreenControl(
        state = state,
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
            ComponentDemo(
                state = componentDemoState,
                control = componentDemoControl,
                modifier = state.indicationModifier()
                    .align(Alignment.Center),
            )
        }
    }
}

@Composable
fun rememberPrimitiveIndicationScreenState(): PrimitiveIndicationScreenState {
    return rememberSaveable(saver = PrimitiveIndicationScreenStateSaver) {
        PrimitiveIndicationScreenState()
    }
}

@Stable
class PrimitiveIndicationScreenState(
    subjectInitial: IndicationPrimitiveToken = IndicationPrimitiveToken.ColorSplit,
    colorInvertAmountInitial: Float = 1f,
    colorSplitXAmountInitial: Float = 0.05f,
    colorSplitYAmountInitial: Float = 0.05f,
    colorSplitColorModeInitial: ColorSplitMode = ColorSplitMode.RGB,
    noiseAmountInitial: Float = 0.5f,
    noiseColorModeInitial: NoiseColorMode = NoiseColorMode.Monochrome,
    pixelateSubdivisionsInitial: Int = 6,
    warpAmountInitial: Float = 0.5f,
    warpRadiusInitial: Dp = 320.dp,
) {
    var subject by mutableStateOf(subjectInitial)
    var colorInvertAmount by mutableStateOf(colorInvertAmountInitial)
    var colorSplitXAmount by mutableStateOf(colorSplitXAmountInitial)
    var colorSplitYAmount by mutableStateOf(colorSplitYAmountInitial)
    var colorSplitColorMode by mutableStateOf(colorSplitColorModeInitial)
    var noiseAmount by mutableStateOf(noiseAmountInitial)
    var noiseColorMode by mutableStateOf(noiseColorModeInitial)
    var pixelateSubdivisions by mutableStateOf(pixelateSubdivisionsInitial)
    var warpAmount by mutableStateOf(warpAmountInitial)
    var warpRadius by mutableStateOf(warpRadiusInitial)
    var warpOffset by mutableStateOf(Offset.Zero)

    fun indicationModifier(): Modifier = when (subject) {
        IndicationPrimitiveToken.None -> Modifier
        IndicationPrimitiveToken.ColorInvert -> Modifier.colorInvert(
            amount = { colorInvertAmount },
        )
        IndicationPrimitiveToken.ColorSplit -> Modifier.colorSplit(
            xAmount = { colorSplitXAmount },
            yAmount = { colorSplitYAmount },
            colorMode = { colorSplitColorMode },
        )
        IndicationPrimitiveToken.Noise -> Modifier.noise(
            colorMode = noiseColorMode,
            amount = { noiseAmount },
        )
        IndicationPrimitiveToken.Pixelate -> Modifier.pixelate(
            subdivisions = { pixelateSubdivisions },
        )
        IndicationPrimitiveToken.Warp -> Modifier
            .warp(
                offset = { warpOffset },
                radius = { warpRadius },
                amount = { warpAmount },
            )
            .onSizeChanged { warpOffset = Offset(it.width / 2f, it.height / 2f) }
    }
}

private const val subjectKey = "subject"
private const val colorInvertAmountKey = "colorInvertAmount"
private const val colorSplitXAmountKey = "colorSplitXAmount"
private const val colorSplitYAmountKey = "colorSplitYAmount"
private const val colorSplitColorModeKey = "colorSplitColorMode"
private const val noiseAmountKey = "noiseAmount"
private const val noiseColorModeKey = "noiseColorMode"
private const val pixelateSubdivisionsKey = "pixelateSubdivisions"
private const val warpAmountKey = "warpAmount"
private const val warpRadiusKey = "warpRadius"

val PrimitiveIndicationScreenStateSaver = mapSaverSafe(
    save = { state ->
        mapOf(
            subjectKey to state.subject.name,
            colorInvertAmountKey to state.colorInvertAmount,
            colorSplitXAmountKey to state.colorSplitXAmount,
            colorSplitYAmountKey to state.colorSplitYAmount,
            colorSplitColorModeKey to state.colorSplitColorMode.ordinal,
            noiseAmountKey to state.noiseAmount,
            noiseColorModeKey to state.noiseColorMode.ordinal,
            pixelateSubdivisionsKey to state.pixelateSubdivisions,
            warpAmountKey to state.warpAmount,
            warpRadiusKey to state.warpRadius.value,
        )
    },
    restore = { map ->
        PrimitiveIndicationScreenState(
            subjectInitial = (map[subjectKey] as? String)
                ?.let { IndicationPrimitiveToken.valueOf(it) }
                ?: IndicationPrimitiveToken.ColorSplit,
            colorInvertAmountInitial = map[colorInvertAmountKey] as Float,
            colorSplitXAmountInitial = map[colorSplitXAmountKey] as Float,
            colorSplitYAmountInitial = map[colorSplitYAmountKey] as Float,
            colorSplitColorModeInitial = ColorSplitMode.entries[map[colorSplitColorModeKey] as Int],
            noiseAmountInitial = map[noiseAmountKey] as Float,
            noiseColorModeInitial = NoiseColorMode.entries[map[noiseColorModeKey] as Int],
            pixelateSubdivisionsInitial = map[pixelateSubdivisionsKey] as Int,
            warpAmountInitial = map[warpAmountKey] as Float,
            warpRadiusInitial = (map[warpRadiusKey] as Float).dp,
        )
    },
)

@Composable
fun rememberPrimitiveIndicationScreenControl(
    state: PrimitiveIndicationScreenState,
    componentDemoControl: ComponentDemoControl,
): PrimitiveIndicationScreenControl {
    return remember(state, componentDemoControl) {
        PrimitiveIndicationScreenControl(
            state = state,
            componentDemoControl = componentDemoControl,
        )
    }
}

@Stable
class PrimitiveIndicationScreenControl(
    val state: PrimitiveIndicationScreenState,
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
        value = { state.colorInvertAmount },
        onValueChange = { state.colorInvertAmount = it },
        valueRange = { 0f..1f },
    )

    private val colorSplitColorModeControl = enumControl(
        name = "Color mode",
        values = { ColorSplitMode.entries },
        selectedValue = { state.colorSplitColorMode },
        onValueChange = { state.colorSplitColorMode = it },
    )

    private val colorSplitXAmountControl = Control.Slider(
        name = "X amount",
        value = { state.colorSplitXAmount },
        onValueChange = { state.colorSplitXAmount = it },
        valueRange = { -1f..1f },
    )

    private val colorSplitYAmountControl = Control.Slider(
        name = "Y amount",
        value = { state.colorSplitYAmount },
        onValueChange = { state.colorSplitYAmount = it },
        valueRange = { -1f..1f },
    )

    private val noiseAmountControl = Control.Slider(
        name = "Amount",
        value = { state.noiseAmount },
        onValueChange = { state.noiseAmount = it },
        valueRange = { 0f..1f },
    )

    private val noiseColorModeControl = enumControl(
        name = "Color mode",
        values = { NoiseColorMode.entries },
        selectedValue = { state.noiseColorMode },
        onValueChange = { state.noiseColorMode = it },
    )

    private val pixelateSubdivisionsControl = Control.Slider(
        name = "Subdivisions",
        value = { state.pixelateSubdivisions.toFloat() },
        onValueChange = { state.pixelateSubdivisions = it.toInt() },
        valueRange = { 0f..100f },
    )

    private val warpAmountControl = Control.Slider(
        name = "Amount",
        value = { state.warpAmount },
        onValueChange = { state.warpAmount = it },
        valueRange = { -5f..5f },
    )

    private val warpRadiusControl = Control.Slider(
        name = "Radius",
        value = { state.warpRadius.value },
        onValueChange = { state.warpRadius = it.dp },
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
                    add(colorSplitXAmountControl)
                    add(colorSplitYAmountControl)
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
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        IndicationScreen(
            onNavigateUp = {},
        )
    }
}
