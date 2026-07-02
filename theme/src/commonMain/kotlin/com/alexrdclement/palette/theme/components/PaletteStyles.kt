package com.alexrdclement.palette.theme.components

import com.alexrdclement.palette.theme.PaletteTheme

/**
 * Entry point for theme-resolved styles for [com.alexrdclement.palette.components] widgets, grouped
 * by component package. Clients pass these explicitly, e.g.
 * `Surface(style = PaletteTheme.styles.core.surface.default)`.
 *
 * Each group ([core], [layout], …) is the per-package object where the styles are actually defined,
 * so a style lives in exactly one place. Styles are built from the current [PaletteTheme]; composite
 * styles reuse their children's resolved styles.
 */
object PaletteStyles {
    val core get() = CoreStyles
    val auth get() = AuthStyles
    val color get() = ColorStyles
    val layout get() = LayoutStyles
    val media get() = MediaStyles
    val menu get() = MenuStyles
    val money get() = MoneyStyles
    val navigation get() = NavigationStyles
    val demo get() = DemoStyles
}
