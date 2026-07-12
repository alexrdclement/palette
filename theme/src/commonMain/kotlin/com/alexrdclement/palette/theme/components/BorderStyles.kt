package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.BorderStyle
import com.alexrdclement.palette.theme.styles.BorderStyleToken
import com.alexrdclement.palette.theme.styles.resolve

object BorderStyles {
    val surface: BorderStyle @Composable get() = BorderStyleToken.Surface.resolve()
    val primary: BorderStyle @Composable get() = BorderStyleToken.Primary.resolve()
    val secondary: BorderStyle @Composable get() = BorderStyleToken.Secondary.resolve()
    val tertiary: BorderStyle @Composable get() = BorderStyleToken.Tertiary.resolve()

    @Composable
    operator fun get(token: BorderStyleToken): BorderStyle = token.resolve()
}
