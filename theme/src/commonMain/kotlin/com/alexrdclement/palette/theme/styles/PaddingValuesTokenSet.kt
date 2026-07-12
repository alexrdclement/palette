package com.alexrdclement.palette.theme.styles

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.SpacingToken
import com.alexrdclement.palette.theme.toSpacing

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
