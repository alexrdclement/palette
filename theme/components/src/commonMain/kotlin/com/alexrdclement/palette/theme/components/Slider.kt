package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.SliderColors
import com.alexrdclement.palette.components.core.SliderState
import com.alexrdclement.palette.components.core.RangeSliderState
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.core.RangeSlider as ComponentRangeSlider
import com.alexrdclement.palette.components.core.Slider as ComponentSlider

@Composable
fun rememberSliderColors(): SliderColors {
    val colorScheme = PaletteTheme.colorScheme
    return remember(colorScheme) {
        SliderColors(
            trackColor = colorScheme.primary,
            thumbColor = colorScheme.primary,
            thumbPointColor = colorScheme.primary,
            thumbBackgroundColor = colorScheme.surface,
        )
    }
}

@Composable
fun Slider(
    value: Float,
    onValueChange: ((Float) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    stepIncrement: Float,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ComponentSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        stepIncrement = stepIncrement,
        onValueChangeFinished = onValueChangeFinished,
        colors = rememberSliderColors(),
        interactionSource = interactionSource,
    )
}

@Composable
fun Slider(
    value: Float,
    onValueChange: ((Float) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ComponentSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        colors = rememberSliderColors(),
        interactionSource = interactionSource,
    )
}

@Composable
fun Slider(
    value: Float,
    onValueChange: ((Float) -> Unit)?,
    snapValues: List<Float>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ComponentSlider(
        value = value,
        onValueChange = onValueChange,
        snapValues = snapValues,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        onValueChangeFinished = onValueChangeFinished,
        colors = rememberSliderColors(),
        interactionSource = interactionSource,
    )
}

@Composable
fun Slider(
    state: SliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ComponentSlider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        colors = rememberSliderColors(),
        interactionSource = interactionSource,
    )
}

@Composable
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: ((ClosedFloatingPointRange<Float>) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    stepIncrement: Float,
    onValueChangeFinished: (() -> Unit)? = null,
) {
    ComponentRangeSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        stepIncrement = stepIncrement,
        onValueChangeFinished = onValueChangeFinished,
        colors = rememberSliderColors(),
    )
}

@Composable
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: ((ClosedFloatingPointRange<Float>) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
) {
    ComponentRangeSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        colors = rememberSliderColors(),
    )
}

@Composable
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: ((ClosedFloatingPointRange<Float>) -> Unit)?,
    snapValues: List<Float>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChangeFinished: (() -> Unit)? = null,
) {
    ComponentRangeSlider(
        value = value,
        onValueChange = onValueChange,
        snapValues = snapValues,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        onValueChangeFinished = onValueChangeFinished,
        colors = rememberSliderColors(),
    )
}

@Composable
fun RangeSlider(
    state: RangeSliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    ComponentRangeSlider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        colors = rememberSliderColors(),
    )
}
