package com.alexrdclement.palette.components.demo.core

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.RangeSlider
import com.alexrdclement.palette.components.core.Slider
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.collections.immutable.persistentListOf
import kotlin.math.roundToInt

enum class SliderType {
    Continuous,
    Range,
}

@Composable
fun SliderDemo(
    state: SliderDemoState = rememberSliderDemoState(),
    control: SliderDemoControl = rememberSliderDemoControl(state),
    modifier: Modifier = Modifier,
) {
    Demo(
        controls = control.controls,
        modifier = modifier.fillMaxSize(),
    ) {
        SliderDemo(
            state = state,
            control = control,
        )
    }
}

@Composable
fun DemoScope.SliderDemo(
    modifier: Modifier = Modifier,
    state: SliderDemoState = rememberSliderDemoState(),
    control: SliderDemoControl = rememberSliderDemoControl(state),
) {
    val sliderModifier = modifier
        .fillMaxWidth()
        .align(Alignment.Center)
        .padding(PaletteTheme.spacing.medium)

    when (state.sliderType) {
        SliderType.Continuous -> Slider(
            value = state.value,
            onValueChange = { state.value = it },
            enabled = state.enabled,
            steps = state.steps,
            modifier = sliderModifier,
        )
        SliderType.Range -> RangeSlider(
            value = state.rangeStart..state.rangeEnd,
            onValueChange = {
                state.rangeStart = it.start
                state.rangeEnd = it.endInclusive
            },
            enabled = state.enabled,
            steps = state.steps,
            modifier = sliderModifier,
        )
    }
}

@Composable
fun rememberSliderDemoState(): SliderDemoState = rememberSaveable(
    saver = SliderDemoStateSaver,
) { SliderDemoState() }

@Stable
class SliderDemoState(
    sliderTypeInitial: SliderType = SliderType.Continuous,
    valueInitial: Float = 0.5f,
    rangeStartInitial: Float = 0.25f,
    rangeEndInitial: Float = 0.75f,
    stepsInitial: Int = 0,
    enabledInitial: Boolean = true,
) {
    var sliderType by mutableStateOf(sliderTypeInitial)
        internal set
    var value by mutableFloatStateOf(valueInitial)
        internal set
    var rangeStart by mutableFloatStateOf(rangeStartInitial)
        internal set
    var rangeEnd by mutableFloatStateOf(rangeEndInitial)
        internal set
    var steps by mutableIntStateOf(stepsInitial)
        internal set
    var enabled by mutableStateOf(enabledInitial)
        internal set
}

private const val sliderTypeKey = "sliderType"
private const val valueKey = "value"
private const val rangeStartKey = "rangeStart"
private const val rangeEndKey = "rangeEnd"
private const val stepsKey = "steps"
private const val enabledKey = "enabled"

val SliderDemoStateSaver = mapSaverSafe(
    save = { value ->
        mapOf(
            sliderTypeKey to value.sliderType.name,
            valueKey to value.value,
            rangeStartKey to value.rangeStart,
            rangeEndKey to value.rangeEnd,
            stepsKey to value.steps,
            enabledKey to value.enabled,
        )
    },
    restore = { map ->
        SliderDemoState(
            sliderTypeInitial = runCatching {
                SliderType.valueOf(map[sliderTypeKey] as String)
            }.getOrDefault(SliderType.Continuous),
            valueInitial = map[valueKey] as Float,
            rangeStartInitial = map[rangeStartKey] as Float,
            rangeEndInitial = map[rangeEndKey] as Float,
            stepsInitial = map[stepsKey] as Int,
            enabledInitial = map[enabledKey] as Boolean,
        )
    },
)

@Composable
fun rememberSliderDemoControl(
    state: SliderDemoState,
): SliderDemoControl = remember(state) { SliderDemoControl(state) }

@Stable
class SliderDemoControl(
    val state: SliderDemoState,
) {
    val typeControl = enumControl(
        name = "Type",
        values = { SliderType.entries },
        selectedValue = { state.sliderType },
        onValueChange = { state.sliderType = it },
    )

    val enabledControl = Control.Toggle(
        name = "Enabled",
        value = { state.enabled },
        onValueChange = { state.enabled = it },
    )

    val stepsControl = Control.Slider(
        name = "Steps",
        value = { state.steps.toFloat() },
        onValueChange = { state.steps = it.roundToInt() },
        valueRange = { 0f..9f },
        steps = 9,
    )

    val controls = persistentListOf(
        typeControl,
        enabledControl,
        stepsControl,
    )
}
