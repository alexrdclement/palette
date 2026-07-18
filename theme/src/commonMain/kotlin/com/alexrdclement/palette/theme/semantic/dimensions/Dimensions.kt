package com.alexrdclement.palette.theme.semantic.dimensions

import com.alexrdclement.palette.theme.semantic.spacing.PaletteSpacing
import com.alexrdclement.palette.theme.semantic.spacing.Spacing

data class Dimensions(
    val spacing: Spacing = PaletteSpacing,
    val padding: PaddingScheme = PalettePaddingScheme,
)

val PaletteDimensions = Dimensions(
    spacing = PaletteSpacing,
    padding = PalettePaddingScheme,
)
