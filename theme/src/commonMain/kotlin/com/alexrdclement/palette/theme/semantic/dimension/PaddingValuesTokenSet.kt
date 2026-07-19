package com.alexrdclement.palette.theme.semantic.dimension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing
import com.alexrdclement.palette.theme.semantic.spacing.SpacingToken
import com.alexrdclement.palette.theme.semantic.spacing.toSpacing

data class PaddingValuesTokenSet(
    val start: SpacingToken,
    val top: SpacingToken,
    val end: SpacingToken,
    val bottom: SpacingToken,
)

fun PaddingValuesTokenSet(all: SpacingToken) = PaddingValuesTokenSet(
    start = all,
    top = all,
    end = all,
    bottom = all,
)

/**
 * A named inset shape: wider horizontally than vertically. The standard content padding for
 * labeled/interactive elements (buttons). Uniform insets need no such constant — they are a single
 * [SpacingToken] applied on all edges (`PaddingValues(spacing.medium)`); a named [PaddingValuesTokenSet]
 * earns its place only when the edges differ.
 */
val Wide = PaddingValuesTokenSet(
    start = SpacingToken.Large,
    top = SpacingToken.Small,
    end = SpacingToken.Large,
    bottom = SpacingToken.Small,
)

fun PaddingValuesTokenSet.toPaddingValues(spacing: Spacing): PaddingValues = PaddingValues(
    start = start.toSpacing(spacing),
    top = top.toSpacing(spacing),
    end = end.toSpacing(spacing),
    bottom = bottom.toSpacing(spacing),
)

@Composable
fun PaddingValuesTokenSet.toPaddingValues(): PaddingValues =
    toPaddingValues(PaletteTheme.semantic.dimension.spacing)
