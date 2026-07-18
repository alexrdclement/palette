package com.alexrdclement.palette.theme.semantic.dimensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing
import com.alexrdclement.palette.theme.semantic.spacing.SpacingToken
import com.alexrdclement.palette.theme.semantic.spacing.toSpacing

data class PaddingValueTokenSet(
    val start: SpacingToken,
    val top: SpacingToken,
    val end: SpacingToken,
    val bottom: SpacingToken,
)

fun PaddingValueTokenSet(all: SpacingToken) = PaddingValueTokenSet(
    start = all,
    top = all,
    end = all,
    bottom = all,
)

fun PaddingValueTokenSet.toPaddingValues(spacing: Spacing): PaddingValues = PaddingValues(
    start = start.toSpacing(spacing),
    top = top.toSpacing(spacing),
    end = end.toSpacing(spacing),
    bottom = bottom.toSpacing(spacing),
)

@Composable
fun PaddingValueTokenSet.toPaddingValues(): PaddingValues =
    toPaddingValues(PaletteTheme.semantic.dimensions.spacing)
