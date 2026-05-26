package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable

enum class ColorToken {
    Primary,
    OnPrimary,
    Secondary,
    OnSecondary,
    Background,
    OnBackground,
    Surface,
    OnSurface,
    Outline,
}

fun ColorToken.toColor(scheme: ColorScheme): androidx.compose.ui.graphics.Color {
    return when (this) {
        ColorToken.Primary -> scheme.primary
        ColorToken.OnPrimary -> scheme.onPrimary
        ColorToken.Secondary -> scheme.secondary
        ColorToken.OnSecondary -> scheme.onSecondary
        ColorToken.Background -> scheme.background
        ColorToken.OnBackground -> scheme.onBackground
        ColorToken.Surface -> scheme.surface
        ColorToken.OnSurface -> scheme.onSurface
        ColorToken.Outline -> scheme.outline
    }
}

@Composable
fun ColorToken.toColor(): androidx.compose.ui.graphics.Color = toColor(PaletteTheme.colorScheme)
