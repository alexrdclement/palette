package com.alexrdclement.palette.theme.primitive

/**
 * Primitive-tier token inputs for [com.alexrdclement.palette.theme.PaletteTheme] — the unopinionated
 * literal building blocks. Raw shapes are static ([PaletteShapePrimitives]) and so are not part of
 * the configurable inputs.
 */
data class PrimitiveTokens(
    val typography: Typography = PalettePrimitiveTypography,
)
