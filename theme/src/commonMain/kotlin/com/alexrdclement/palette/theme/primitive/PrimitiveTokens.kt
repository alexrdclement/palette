package com.alexrdclement.palette.theme.primitive

/**
 * Primitive-tier token inputs for [com.alexrdclement.palette.theme.PaletteTheme] — the unopinionated
 * literal building blocks. Editable and shared: semantic tokens reference these, so a primitive
 * change flows through every semantic token that references it.
 */
data class PrimitiveTokens(
    val typography: Typography = PalettePrimitiveTypography,
    val shape: ShapePrimitives = PaletteShapePrimitives,
)
