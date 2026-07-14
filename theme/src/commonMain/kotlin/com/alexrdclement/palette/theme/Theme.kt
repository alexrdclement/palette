package com.alexrdclement.palette.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.alexrdclement.palette.theme.component.Component
import com.alexrdclement.palette.theme.component.ComponentTokens
import com.alexrdclement.palette.theme.component.LocalComponentTokens
import com.alexrdclement.palette.theme.primitive.LocalPrimitiveTokens
import com.alexrdclement.palette.theme.primitive.Primitive
import com.alexrdclement.palette.theme.primitive.PrimitiveTokens
import com.alexrdclement.palette.theme.semantic.LocalPaletteColorScheme
import com.alexrdclement.palette.theme.semantic.LocalPaletteFormats
import com.alexrdclement.palette.theme.semantic.LocalPaletteIndication
import com.alexrdclement.palette.theme.semantic.LocalPaletteShapes
import com.alexrdclement.palette.theme.semantic.LocalPaletteSpacing
import com.alexrdclement.palette.theme.semantic.LocalPaletteTypography
import com.alexrdclement.palette.theme.semantic.Semantic
import com.alexrdclement.palette.theme.semantic.SemanticTokens
import com.alexrdclement.palette.theme.semantic.typography.resolve

@Composable
fun PaletteTheme(
    primitive: PrimitiveTokens = PrimitiveTokens(),
    semantic: SemanticTokens = SemanticTokens(),
    component: ComponentTokens = ComponentTokens(),
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkMode) semantic.colors.dark else semantic.colors.light
    val typography = remember(primitive, semantic.typography) {
        semantic.typography.resolve(primitive)
    }
    CompositionLocalProvider(
        LocalPrimitiveTokens provides primitive,
        LocalPaletteColorScheme provides colorScheme,
        LocalPaletteTypography provides typography,
        LocalPaletteShapes provides semantic.shapeScheme,
        LocalPaletteIndication provides semantic.indication,
        LocalPaletteSpacing provides semantic.spacing,
        LocalComponentTokens provides component,
        LocalPaletteFormats provides semantic.formats,
        content = content,
    )
}

object PaletteTheme {
    val primitive get() = Primitive
    val semantic get() = Semantic
    val component get() = Component
}
