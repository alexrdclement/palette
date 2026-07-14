package com.alexrdclement.palette.theme.semantic

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.theme.primitive.LocalPrimitiveTokens
import com.alexrdclement.palette.theme.semantic.color.ColorScheme
import com.alexrdclement.palette.theme.semantic.color.ColorTokens
import com.alexrdclement.palette.theme.semantic.format.Formats
import com.alexrdclement.palette.theme.semantic.shape.ShapeScheme
import com.alexrdclement.palette.theme.semantic.spacing.Spacing
import com.alexrdclement.palette.theme.semantic.typography.Typography
import com.alexrdclement.palette.theme.semantic.typography.resolve

object Semantic {
    val color: ColorScheme
        @Composable
        get() {
            val colors = LocalSemanticTokens.current.colors
            return if (LocalIsDarkMode.current) colors.dark else colors.light
        }

    val typography: Typography
        @Composable
        get() {
            val primitive = LocalPrimitiveTokens.current
            val semantic = LocalSemanticTokens.current
            return remember(primitive, semantic.typography) {
                semantic.typography.resolve(primitive)
            }
        }

    val shape: ShapeScheme
        @Composable
        get() = LocalSemanticTokens.current.shapeScheme

    val spacing: Spacing
        @Composable
        get() = LocalSemanticTokens.current.spacing

    val indication: Indication
        @Composable
        get() = LocalSemanticTokens.current.indication

    val format: Formats
        @Composable
        get() = LocalSemanticTokens.current.formats
}

/**
 * Default used when no [com.alexrdclement.palette.theme.PaletteTheme] is applied: an unspecified
 * color scheme, so reads outside a theme surface as clearly missing rather than as real colors.
 */
private val UnspecifiedColorScheme = ColorScheme(
    primary = Color.Unspecified,
    onPrimary = Color.Unspecified,
    secondary = Color.Unspecified,
    onSecondary = Color.Unspecified,
    background = Color.Unspecified,
    onBackground = Color.Unspecified,
    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    outline = Color.Unspecified,
    disabledContainerAlpha = 1f,
    disabledContentAlpha = 1f,
)

val LocalSemanticTokens = staticCompositionLocalOf {
    SemanticTokens(
        colors = ColorTokens(
            light = UnspecifiedColorScheme,
            dark = UnspecifiedColorScheme,
        ),
    )
}

val LocalIsDarkMode = staticCompositionLocalOf { false }
