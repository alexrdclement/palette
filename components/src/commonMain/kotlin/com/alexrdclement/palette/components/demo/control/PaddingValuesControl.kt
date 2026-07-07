package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.util.copy
import kotlinx.collections.immutable.persistentListOf

/**
 * A collapsed column of raw dp sliders for each edge of a [PaddingValues]. Edges are read and
 * written in [LayoutDirection.Ltr] so start/end map to left/right consistently.
 */
fun paddingValuesControls(
    value: () -> PaddingValues,
    onValueChange: (PaddingValues) -> Unit,
    name: String = "Padding",
    valueRange: () -> ClosedFloatingPointRange<Float> = { 0f..80f },
    stepIncrement: Float? = 1f,
    expandedInitial: Boolean = false,
): Control {
    fun edgeControl(
        edgeName: String,
        edgeValue: (PaddingValues) -> Float,
        withEdge: (PaddingValues, Float) -> PaddingValues,
    ) = Control.Slider(
        name = edgeName,
        value = { edgeValue(value()) },
        onValueChange = { onValueChange(withEdge(value(), it)) },
        valueRange = valueRange,
        stepIncrement = stepIncrement,
    )

    return Control.ControlColumn(
        name = name,
        indent = true,
        expandedInitial = expandedInitial,
        controls = {
            persistentListOf(
                edgeControl(
                    edgeName = "Start",
                    edgeValue = { it.calculateStartPadding(LayoutDirection.Ltr).value },
                    withEdge = { pv, v -> pv.copy(start = v.dp, layoutDirection = LayoutDirection.Ltr) },
                ),
                edgeControl(
                    edgeName = "Top",
                    edgeValue = { it.calculateTopPadding().value },
                    withEdge = { pv, v -> pv.copy(top = v.dp, layoutDirection = LayoutDirection.Ltr) },
                ),
                edgeControl(
                    edgeName = "End",
                    edgeValue = { it.calculateEndPadding(LayoutDirection.Ltr).value },
                    withEdge = { pv, v -> pv.copy(end = v.dp, layoutDirection = LayoutDirection.Ltr) },
                ),
                edgeControl(
                    edgeName = "Bottom",
                    edgeValue = { it.calculateBottomPadding().value },
                    withEdge = { pv, v -> pv.copy(bottom = v.dp, layoutDirection = LayoutDirection.Ltr) },
                ),
            )
        },
    )
}
