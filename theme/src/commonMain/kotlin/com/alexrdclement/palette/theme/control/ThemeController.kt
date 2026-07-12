package com.alexrdclement.palette.theme.control

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.semantic.ColorScheme
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.ShapeScheme
import com.alexrdclement.palette.theme.semantic.Spacing
import com.alexrdclement.palette.theme.component.core.Styles
import com.alexrdclement.palette.theme.primitive.Typography as PrimitiveTypography
import com.alexrdclement.palette.theme.semantic.Typography
import com.alexrdclement.palette.theme.semantic.makePaletteTypography

class ThemeController internal constructor(
    private val state: ThemeStateImpl
): ThemeState by state {

    /**
     * Sets the primitive typography (base font family/weight) and rebuilds the semantic typography
     * ramp from it, so primitive changes propagate through the whole ramp. Note this regenerates the
     * ramp, resetting any per-token semantic typography overrides.
     */
    fun setPrimitiveTypography(primitiveTypography: PrimitiveTypography): Boolean {
        state.primitiveTypography = primitiveTypography
        state.typography = makePaletteTypography(primitiveTypography = primitiveTypography)
        return true
    }

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
