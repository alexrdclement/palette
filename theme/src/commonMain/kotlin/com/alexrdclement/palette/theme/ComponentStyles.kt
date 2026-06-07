package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.demo.DemoStyle

/**
 * Entry point for theme-resolved [androidx.compose.runtime.Composable] styles for
 * [com.alexrdclement.palette.components] widgets, grouped by component package. Clients pass these
 * explicitly, e.g. `Surface(style = PaletteTheme.components.core.surface)`.
 *
 * Each group ([core], [layout], …) is the per-package object where the styles are actually defined,
 * so a style lives in exactly one place. [demo] is kept flat since it is the single whole-framework
 * aggregate. Styles are built from the current [PaletteTheme]; composite styles reuse their
 * children's resolved styles.
 */
object PaletteComponentStyles {
    val core get() = CoreComponentStyles
    val auth get() = AuthComponentStyles
    val color get() = ColorComponentStyles
    val layout get() = LayoutComponentStyles
    val media get() = MediaComponentStyles
    val menu get() = MenuComponentStyles
    val money get() = MoneyComponentStyles
    val navigation get() = NavigationComponentStyles

    val demo: DemoStyle
        @Composable get() = DemoComponentStyles.demo
}

val PaletteTheme.components: PaletteComponentStyles
    get() = PaletteComponentStyles
