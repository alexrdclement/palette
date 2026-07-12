package com.alexrdclement.palette.theme.primitive

/**
 * Primitive typography tokens: the unopinionated, literal building blocks (font family, font
 * weight) from which the semantic [com.alexrdclement.palette.theme.semantic.Typography] ramp is
 * composed. Held as state so these can eventually be made editable.
 */
data class Typography(
    val fontFamily: FontFamily = FontFamily.Monospace,
    val fontWeight: FontWeight = FontWeight.Normal,
)

val PalettePrimitiveTypography = Typography()
