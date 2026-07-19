package com.alexrdclement.palette.theme.semantic.dimension

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing

/**
 * Named internal-inset (padding) tokens. A `PaddingValuesToken` exists only for insets whose edges
 * differ — asymmetric (e.g. [Default]) or directional (some edges [SpacingToken.None]). A *uniform*
 * inset carries no per-edge information, so it is expressed directly from a single spacing step
 * (`PaddingValues(spacing.medium)`) rather than a token.
 */
enum class PaddingValuesToken {
    Default,
}

fun PaddingValuesToken.tokenSet(scheme: PaddingScheme): PaddingValuesTokenSet {
    return when (this) {
        PaddingValuesToken.Default -> scheme.default
    }
}

fun PaddingValuesToken.toPaddingValues(
    scheme: PaddingScheme,
    spacing: Spacing,
): PaddingValues = tokenSet(scheme).toPaddingValues(spacing)

@Composable
fun PaddingValuesToken.toPaddingValues(): PaddingValues = toPaddingValues(
    scheme = PaletteTheme.semantic.dimension.padding,
    spacing = PaletteTheme.semantic.dimension.spacing,
)
