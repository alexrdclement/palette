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

@Composable
fun ColorToken.toColor(): androidx.compose.ui.graphics.Color {
    return when (this) {
        ColorToken.Primary -> PaletteTheme.colorScheme.primary
        ColorToken.OnPrimary -> PaletteTheme.colorScheme.onPrimary
        ColorToken.Secondary -> PaletteTheme.colorScheme.secondary
        ColorToken.OnSecondary -> PaletteTheme.colorScheme.onSecondary
        ColorToken.Background -> PaletteTheme.colorScheme.background
        ColorToken.OnBackground -> PaletteTheme.colorScheme.onBackground
        ColorToken.Surface -> PaletteTheme.colorScheme.surface
        ColorToken.OnSurface -> PaletteTheme.colorScheme.onSurface
        ColorToken.Outline -> PaletteTheme.colorScheme.outline
    }
}
