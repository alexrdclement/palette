package com.alexrdclement.palette.theme.semantic.color

/**
 * Semantic color tokens: the light and dark [ColorScheme]s. Both are tokens; which one is active is a
 * runtime concern (dark mode) resolved by [com.alexrdclement.palette.theme.PaletteTheme], not a token.
 */
data class ColorTokens(
    val light: ColorScheme = PaletteLightColorScheme,
    val dark: ColorScheme = PaletteDarkColorScheme,
)
