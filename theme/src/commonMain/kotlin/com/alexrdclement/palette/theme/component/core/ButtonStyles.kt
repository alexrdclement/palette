package com.alexrdclement.palette.theme.component.core

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle

object ButtonStyles {
    val primary: ButtonStyle @Composable get() = ButtonStyleToken.Primary.resolve()
    val secondary: ButtonStyle @Composable get() = ButtonStyleToken.Secondary.resolve()
    val tertiary: ButtonStyle @Composable get() = ButtonStyleToken.Tertiary.resolve()

    @Composable
    operator fun get(token: ButtonStyleToken): ButtonStyle = token.resolve()
}
