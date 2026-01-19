package com.alexrdclement.palette.theme.control

import androidx.compose.foundation.Indication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alexrdclement.palette.theme.ColorScheme
import com.alexrdclement.palette.theme.PaletteDarkColorScheme
import com.alexrdclement.palette.theme.PaletteIndication
import com.alexrdclement.palette.theme.PaletteLightColorScheme
import com.alexrdclement.palette.theme.PaletteShapeScheme
import com.alexrdclement.palette.theme.PaletteSpacing
import com.alexrdclement.palette.theme.PaletteStyles
import com.alexrdclement.palette.theme.PaletteTypography
import com.alexrdclement.palette.theme.ShapeScheme
import com.alexrdclement.palette.theme.Spacing
import com.alexrdclement.palette.theme.Styles
import com.alexrdclement.palette.theme.Typography
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.PaletteFormats

interface ThemeState {
    val typography: Typography
    val indication: Indication
    val lightColorScheme: ColorScheme
    val darkColorScheme: ColorScheme
    val isDarkMode: Boolean
    val shapeScheme: ShapeScheme
    val spacing: Spacing
    val formats: Formats
    val styles: Styles

    val colorScheme: ColorScheme
        get() = if (isDarkMode) darkColorScheme else lightColorScheme
}

internal class ThemeStateImpl(
    isDarkModeInitial: Boolean,
    lightColorSchemeInitial: ColorScheme = PaletteLightColorScheme,
    darkColorSchemeInitial: ColorScheme = PaletteDarkColorScheme,
    typographyInitial: Typography = PaletteTypography,
    shapeSchemeInitial: ShapeScheme = PaletteShapeScheme,
    indicationInitial: Indication = PaletteIndication,
    spacingInitial: Spacing = PaletteSpacing,
    formatsInitial: Formats = PaletteFormats,
    stylesInitial: Styles = PaletteStyles,
) : ThemeState {
    override var typography by mutableStateOf(typographyInitial)
    override var shapeScheme by mutableStateOf(shapeSchemeInitial)
    override var indication by mutableStateOf(indicationInitial)
    override var lightColorScheme by mutableStateOf(lightColorSchemeInitial)
    override var darkColorScheme by mutableStateOf(darkColorSchemeInitial)
    override var isDarkMode by mutableStateOf(isDarkModeInitial)
    override var spacing by mutableStateOf(spacingInitial)
    override var formats by mutableStateOf(formatsInitial)
    override var styles by mutableStateOf(stylesInitial)
}

@Composable
internal fun rememberThemeState(
    isDarkMode: Boolean = isSystemInDarkTheme(),
): ThemeStateImpl {
    return remember(isDarkMode) {
        ThemeStateImpl(
            isDarkModeInitial = isDarkMode,
        )
    }
}
