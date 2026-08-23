package com.alexrdclement.palette.theme.semantic.dimension

import com.alexrdclement.palette.theme.semantic.spacing.PaletteSpacing
import com.alexrdclement.palette.theme.semantic.spacing.Spacing

data class Dimension(
    val spacing: Spacing = PaletteSpacing,
    val padding: PaddingScheme = PalettePaddingScheme,
    val size: Size = PaletteSize,
)

val PaletteDimension = Dimension(
    spacing = PaletteSpacing,
    padding = PalettePaddingScheme,
    size = PaletteSize,
)
