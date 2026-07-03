package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.resolve

object ButtonStyles {
    val primary: ButtonStyle @Composable get() = ButtonStyleToken.Primary.resolve()
    val secondary: ButtonStyle @Composable get() = ButtonStyleToken.Secondary.resolve()
    val tertiary: ButtonStyle @Composable get() = ButtonStyleToken.Tertiary.resolve()

    @Composable
    operator fun get(token: ButtonStyleToken): ButtonStyle = token.resolve()
}
