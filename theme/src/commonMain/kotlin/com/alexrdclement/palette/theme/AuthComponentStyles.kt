package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.auth.AuthButtonStyle
import com.alexrdclement.palette.theme.styles.AuthButtonStyleToken
import com.alexrdclement.palette.theme.styles.toButtonStyleToken

/** Resolved styles for [com.alexrdclement.palette.components.auth]; surfaced via [PaletteTheme.components]. */
object AuthComponentStyles {

    @Composable
    fun authButton(token: AuthButtonStyleToken = AuthButtonStyleToken.Secondary): AuthButtonStyle =
        AuthButtonStyle(
            buttonStyle = CoreComponentStyles.button(token.toButtonStyleToken()),
            textStyle = when (token) {
                AuthButtonStyleToken.Primary -> PaletteTheme.styles.text.labelLarge
                AuthButtonStyleToken.Secondary -> PaletteTheme.styles.text.labelSmall
                AuthButtonStyleToken.Tertiary -> PaletteTheme.styles.text.labelSmall
            },
        )
}
