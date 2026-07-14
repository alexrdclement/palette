package com.alexrdclement.palette.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.alexrdclement.palette.theme.component.Component
import com.alexrdclement.palette.theme.component.ComponentTokens
import com.alexrdclement.palette.theme.component.LocalComponentTokens
import com.alexrdclement.palette.theme.primitive.LocalPrimitiveTokens
import com.alexrdclement.palette.theme.primitive.Primitive
import com.alexrdclement.palette.theme.primitive.PrimitiveTokens
import com.alexrdclement.palette.theme.semantic.LocalIsDarkMode
import com.alexrdclement.palette.theme.semantic.LocalSemanticTokens
import com.alexrdclement.palette.theme.semantic.Semantic
import com.alexrdclement.palette.theme.semantic.SemanticTokens

@Composable
fun PaletteTheme(
    primitive: PrimitiveTokens = PrimitiveTokens(),
    semantic: SemanticTokens = SemanticTokens(),
    component: ComponentTokens = ComponentTokens(),
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPrimitiveTokens provides primitive,
        LocalSemanticTokens provides semantic,
        LocalIsDarkMode provides isDarkMode,
        LocalComponentTokens provides component,
        content = content,
    )
}

object PaletteTheme {
    val primitive get() = Primitive
    val semantic get() = Semantic
    val component get() = Component
}
