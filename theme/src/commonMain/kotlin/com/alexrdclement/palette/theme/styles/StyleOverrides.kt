package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

/**
 * Per-token style overrides layered on top of each token's [default]. Sparse: a token absent from a
 * map resolves to its default. This is the single editable source of style state for the theme,
 * provided via [LocalStyleOverrides] and surfaced as resolved styles through [PaletteTheme.styles].
 */
data class StyleOverrides(
    val text: Map<TextStyleToken, TextStyleTokenSet> = emptyMap(),
    val button: Map<ButtonStyleToken, ButtonStyleTokenSet> = emptyMap(),
    val border: Map<BorderStyleToken, BorderStyle> = emptyMap(),
)

val LocalStyleOverrides = staticCompositionLocalOf { StyleOverrides() }

/** The current border token set — a theme override if present, else the [BorderStyleToken.default]. */
@Composable
fun BorderStyleToken.tokenSet(): BorderStyle =
    LocalStyleOverrides.current.border[this] ?: default
