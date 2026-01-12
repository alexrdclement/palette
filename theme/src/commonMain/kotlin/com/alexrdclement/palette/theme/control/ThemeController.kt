package com.alexrdclement.palette.theme.control

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorScheme
import com.alexrdclement.palette.theme.Formats
import com.alexrdclement.palette.theme.ShapeScheme
import com.alexrdclement.palette.theme.Spacing
import com.alexrdclement.palette.theme.Styles
import com.alexrdclement.palette.theme.Typography

class ThemeController internal constructor(
    private val state: ThemeStateImpl
): ThemeState by state {

    fun setTypography(typography: Typography): Boolean {
        state.typography = typography
        return true
    }

    fun setIndication(indication: Indication): Boolean {
        state.indication = indication
        return true
    }

    fun setLightColorScheme(colorScheme: ColorScheme): Boolean {
        state.lightColorScheme = colorScheme
        return true
    }

    fun setDarkColorScheme(colorScheme: ColorScheme): Boolean {
        state.darkColorScheme = colorScheme
        return true
    }

    fun setIsDarkMode(isDarkMode: Boolean): Boolean {
        state.isDarkMode = isDarkMode
        return true
    }

    fun setShapeScheme(shapeScheme: ShapeScheme): Boolean {
        state.shapeScheme = shapeScheme
        return true
    }

    fun setSpacing(spacing: Spacing): Boolean {
        state.spacing = spacing
        return true
    }

    fun setStyles(styles: Styles): Boolean {
        state.styles = styles
        return true
    }

    fun setFormats(formats: Formats): Boolean {
        state.formats = formats
        return true
    }
}

@Composable
fun rememberThemeController(): ThemeController {
    val state: ThemeStateImpl = rememberThemeState()
    return ThemeController(state)
}
