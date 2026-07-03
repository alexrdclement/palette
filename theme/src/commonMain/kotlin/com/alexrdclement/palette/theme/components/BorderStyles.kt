package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.tokenSet

object BorderStyles {
    val surface: BorderStyle @Composable get() = BorderStyleToken.Surface.tokenSet()
    val primary: BorderStyle @Composable get() = BorderStyleToken.Primary.tokenSet()
    val secondary: BorderStyle @Composable get() = BorderStyleToken.Secondary.tokenSet()
    val tertiary: BorderStyle @Composable get() = BorderStyleToken.Tertiary.tokenSet()

    @Composable
    operator fun get(token: BorderStyleToken): BorderStyle = token.tokenSet()
}
