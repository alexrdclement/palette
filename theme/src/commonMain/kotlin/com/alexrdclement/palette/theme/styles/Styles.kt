package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

/**
 * The single in-memory source of truth for the theme's editable token styles: the current token set
 * for every token, seeded from each token's `default`. Edited through
 * [com.alexrdclement.palette.theme.control.ThemeController] and provided via [LocalStyles]; surfaced
 * as resolved styles through [com.alexrdclement.palette.theme.PaletteTheme.styles].
 *
 * The maps are complete (every token present), so resolution reads them with [Map.getValue].
 */
data class Styles(
    val text: Map<TextStyleToken, TextStyleTokenSet> =
        TextStyleToken.entries.associateWith { it.default },
    val button: Map<ButtonStyleToken, ButtonStyleTokenSet> =
        ButtonStyleToken.entries.associateWith { it.default },
    val border: Map<BorderStyleToken, BorderStyle> =
        BorderStyleToken.entries.associateWith { it.default },
)

val LocalStyles = staticCompositionLocalOf { Styles() }

/** The current border token set from the theme's [LocalStyles]. */
@Composable
fun BorderStyleToken.tokenSet(): BorderStyle = LocalStyles.current.border.getValue(this)
