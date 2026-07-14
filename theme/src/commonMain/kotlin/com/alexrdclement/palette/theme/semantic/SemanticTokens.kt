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

data class SemanticTokens(
    val colors: ColorTokens = ColorTokens(),
    val typography: SemanticTypography = SemanticTypography(),
    val shapeScheme: ShapeScheme = PaletteShapeScheme,
    val spacing: Spacing = PaletteSpacing,
    val interaction: InteractionScheme = PaletteInteractionScheme,
    val formats: Formats = PaletteFormats,
)
