package com.alexrdclement.palette.theme.semantic

import androidx.compose.foundation.Indication
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.PaletteFormats

/**
 * Semantic-tier token inputs for [com.alexrdclement.palette.theme.PaletteTheme] — design-intent
 * tokens that reference primitives. These are the editable inputs; the values components consume
 * (the active color scheme, the resolved typography ramp) are derived by `PaletteTheme` from these
 * plus the primitive tokens and the dark-mode flag.
 */
data class SemanticTokens(
    val colors: ColorTokens = ColorTokens(),
    val typography: SemanticTypography = SemanticTypography(),
    val shapeScheme: ShapeScheme = PaletteShapeScheme,
    val spacing: Spacing = PaletteSpacing,
    val indication: Indication = PaletteIndication,
    val formats: Formats = PaletteFormats,
)
