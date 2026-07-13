package com.alexrdclement.palette.theme.control

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alexrdclement.palette.theme.component.ComponentTokens
import com.alexrdclement.palette.theme.primitive.PrimitiveTokens
import com.alexrdclement.palette.theme.semantic.SemanticTokens
import com.alexrdclement.palette.theme.semantic.color.ColorScheme

interface ThemeState {
    val primitive: PrimitiveTokens
    val semantic: SemanticTokens
    val component: ComponentTokens
    val isDarkMode: Boolean
    val colorScheme: ColorScheme
        get() = if (isDarkMode) semantic.colors.dark else semantic.colors.light
}

internal class ThemeStateImpl(
    isDarkModeInitial: Boolean,
    primitiveInitial: PrimitiveTokens = PrimitiveTokens(),
    semanticInitial: SemanticTokens = SemanticTokens(),
    componentInitial: ComponentTokens = ComponentTokens(),
) : ThemeState {
    override var primitive by mutableStateOf(primitiveInitial)
    override var semantic by mutableStateOf(semanticInitial)
    override var component by mutableStateOf(componentInitial)
    override var isDarkMode by mutableStateOf(isDarkModeInitial)
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
