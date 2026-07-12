package com.alexrdclement.palette.theme.control

import androidx.compose.foundation.Indication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alexrdclement.palette.theme.semantic.ColorScheme
import com.alexrdclement.palette.theme.semantic.PaletteDarkColorScheme
import com.alexrdclement.palette.theme.semantic.PaletteIndication
import com.alexrdclement.palette.theme.semantic.PaletteLightColorScheme
import com.alexrdclement.palette.theme.semantic.PaletteShapeScheme
import com.alexrdclement.palette.theme.semantic.PaletteSpacing
import com.alexrdclement.palette.theme.semantic.PaletteTypography
import com.alexrdclement.palette.theme.semantic.ShapeScheme
import com.alexrdclement.palette.theme.semantic.Spacing
import com.alexrdclement.palette.theme.component.core.Styles
import com.alexrdclement.palette.theme.primitive.PalettePrimitiveTypography
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography
import com.alexrdclement.palette.theme.semantic.Typography
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.format.PaletteFormats

interface ThemeState {
    val primitiveTypography: PrimitiveTypography
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
    primitiveTypographyInitial: PrimitiveTypography = PalettePrimitiveTypography,
    lightColorSchemeInitial: ColorScheme = PaletteLightColorScheme,
    darkColorSchemeInitial: ColorScheme = PaletteDarkColorScheme,
    typographyInitial: Typography = PaletteTypography,
    shapeSchemeInitial: ShapeScheme = PaletteShapeScheme,
    indicationInitial: Indication = PaletteIndication,
    spacingInitial: Spacing = PaletteSpacing,
    formatsInitial: Formats = PaletteFormats,
    stylesInitial: Styles = Styles(),
) : ThemeState {
    override var primitiveTypography by mutableStateOf(primitiveTypographyInitial)
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
