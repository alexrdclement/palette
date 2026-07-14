package com.alexrdclement.palette.theme.semantic

import com.alexrdclement.palette.theme.semantic.color.ColorTokens
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.PaletteFormats
import com.alexrdclement.palette.theme.semantic.interaction.InteractionScheme
import com.alexrdclement.palette.theme.semantic.interaction.PaletteInteractionScheme
import com.alexrdclement.palette.theme.semantic.shape.PaletteShapeScheme
import com.alexrdclement.palette.theme.semantic.shape.ShapeScheme
import com.alexrdclement.palette.theme.semantic.spacing.PaletteSpacing
import com.alexrdclement.palette.theme.semantic.spacing.Spacing
import com.alexrdclement.palette.theme.semantic.typography.SemanticTypography

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
    val interaction: InteractionScheme = PaletteInteractionScheme,
    val formats: Formats = PaletteFormats,
)
