package com.alexrdclement.palette.theme.component.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.semantic.spacing.SpacingToken
import com.alexrdclement.palette.theme.semantic.spacing.toSpacing

data class PaddingValuesTokenSet(
    val start: SpacingToken,
    val top: SpacingToken,
    val end: SpacingToken,
    val bottom: SpacingToken,
)

@Composable
fun PaddingValuesTokenSet.toPaddingValues(): PaddingValues = PaddingValues(
    start = start.toSpacing(),
    top = top.toSpacing(),
    end = end.toSpacing(),
    bottom = bottom.toSpacing(),
)
