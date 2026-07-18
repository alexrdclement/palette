package com.alexrdclement.palette.theme.semantic.dimensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing

enum class PaddingValuesToken {
    Default,
    Compact,
}

fun PaddingValuesToken.tokenSet(scheme: PaddingScheme): PaddingValuesTokenSet {
    return when (this) {
        PaddingValuesToken.Default -> scheme.default
        PaddingValuesToken.Compact -> scheme.compact
    }
}

fun PaddingValuesToken.toPaddingValues(
    scheme: PaddingScheme,
    spacing: Spacing,
): PaddingValues = tokenSet(scheme).toPaddingValues(spacing)

@Composable
fun PaddingValuesToken.toPaddingValues(): PaddingValues = toPaddingValues(
    scheme = PaletteTheme.semantic.dimensions.padding,
    spacing = PaletteTheme.semantic.dimensions.spacing,
)
