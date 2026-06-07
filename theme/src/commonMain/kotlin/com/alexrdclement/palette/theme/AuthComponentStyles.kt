package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.auth.AuthButtonStyle
import com.alexrdclement.palette.theme.styles.AuthButtonStyleToken
import com.alexrdclement.palette.theme.styles.toButtonStyleToken

/** Resolved per-variant [AuthButtonStyle]s, mirroring [com.alexrdclement.palette.theme.styles.ResolvedButtonStyleScheme]. */
data class ResolvedAuthButtonStyleScheme(
    val primary: AuthButtonStyle,
    val secondary: AuthButtonStyle,
    val tertiary: AuthButtonStyle,
) {
    operator fun get(token: AuthButtonStyleToken): AuthButtonStyle = when (token) {
        AuthButtonStyleToken.Primary -> primary
        AuthButtonStyleToken.Secondary -> secondary
        AuthButtonStyleToken.Tertiary -> tertiary
    }
}

/** Resolved styles for [com.alexrdclement.palette.components.auth]; surfaced via [PaletteTheme.components]. */
object AuthComponentStyles {

    /** Resolved auth button styles per variant, e.g. `authButton.secondary`; index by token with `authButton[token]`. */
    val authButton: ResolvedAuthButtonStyleScheme
        @Composable get() = ResolvedAuthButtonStyleScheme(
            primary = authButtonStyle(AuthButtonStyleToken.Primary),
            secondary = authButtonStyle(AuthButtonStyleToken.Secondary),
            tertiary = authButtonStyle(AuthButtonStyleToken.Tertiary),
        )

    @Composable
    private fun authButtonStyle(token: AuthButtonStyleToken): AuthButtonStyle =
        AuthButtonStyle(
            buttonStyle = CoreComponentStyles.button[token.toButtonStyleToken()],
            textStyle = when (token) {
                AuthButtonStyleToken.Primary -> PaletteTheme.styles.text.labelLarge
                AuthButtonStyleToken.Secondary -> PaletteTheme.styles.text.labelSmall
                AuthButtonStyleToken.Tertiary -> PaletteTheme.styles.text.labelSmall
            },
        )
}
