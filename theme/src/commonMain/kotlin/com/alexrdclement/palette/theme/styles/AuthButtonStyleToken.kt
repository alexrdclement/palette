package com.alexrdclement.palette.theme.styles

enum class AuthButtonStyleToken {
    Primary,
    Secondary,
    Tertiary,
}

/** The [ButtonStyleToken] variant backing each [AuthButtonStyleToken]. */
fun AuthButtonStyleToken.toButtonStyleToken(): ButtonStyleToken = when (this) {
    AuthButtonStyleToken.Primary -> ButtonStyleToken.Primary
    AuthButtonStyleToken.Secondary -> ButtonStyleToken.Secondary
    AuthButtonStyleToken.Tertiary -> ButtonStyleToken.Tertiary
}
