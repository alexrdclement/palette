package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.auth.AuthButtonStyle
import com.alexrdclement.palette.components.core.copy
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.AuthButtonStyleToken
import com.alexrdclement.palette.theme.styles.resolve
import com.alexrdclement.palette.theme.styles.toButtonStyleToken

object AuthButtonStyles {
    val primary: AuthButtonStyle @Composable get() = authButtonStyle(AuthButtonStyleToken.Primary)
    val secondary: AuthButtonStyle @Composable get() = authButtonStyle(AuthButtonStyleToken.Secondary)
    val tertiary: AuthButtonStyle @Composable get() = authButtonStyle(AuthButtonStyleToken.Tertiary)

    @Composable
    operator fun get(token: AuthButtonStyleToken): AuthButtonStyle = authButtonStyle(token)

    @Composable
    private fun authButtonStyle(token: AuthButtonStyleToken): AuthButtonStyle = AuthButtonStyle(
        buttonStyle = token.toButtonStyleToken().resolve(),
        textStyle = when (token) {
            AuthButtonStyleToken.Primary -> TextStyles.labelLarge.copy(
                color = PaletteTheme.colorScheme.onPrimary,
            )
            AuthButtonStyleToken.Secondary -> TextStyles.labelSmall
            AuthButtonStyleToken.Tertiary -> TextStyles.labelSmall
        },
    )
}

object AuthStyles {
    val authButton get() = AuthButtonStyles
}
