package com.alexrdclement.palette.theme.semantic.dimensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing

enum class PaddingValueToken {
    Default,
    Compact,
}

fun PaddingValueToken.tokenSet(scheme: PaddingScheme): PaddingValueTokenSet {
    return when (this) {
        PaddingValueToken.Default -> scheme.default
        PaddingValueToken.Compact -> scheme.compact
    }
}

fun PaddingValueToken.toPaddingValues(
    scheme: PaddingScheme,
    spacing: Spacing,
): PaddingValues = tokenSet(scheme).toPaddingValues(spacing)

@Composable
fun PaddingValueToken.toPaddingValues(): PaddingValues = toPaddingValues(
    scheme = PaletteTheme.semantic.dimensions.padding,
    spacing = PaletteTheme.semantic.dimensions.spacing,
)
