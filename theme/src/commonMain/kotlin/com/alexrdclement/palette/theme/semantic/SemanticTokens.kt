package com.alexrdclement.palette.theme.semantic

import androidx.compose.foundation.Indication
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.PaletteFormats

/**
 * Semantic-tier token inputs for [com.alexrdclement.palette.theme.PaletteTheme] — design-intent
 * tokens that reference primitives. [colorScheme] is the already-resolved scheme; light/dark
 * selection is the caller's responsibility.
 */
data class SemanticTokens(
    val colorScheme: ColorScheme = PaletteLightColorScheme,
    val typography: Typography = PaletteTypography,
    val shapeScheme: ShapeScheme = PaletteShapeScheme,
    val spacing: Spacing = PaletteSpacing,
    val indication: Indication = PaletteIndication,
    val formats: Formats = PaletteFormats,
)
