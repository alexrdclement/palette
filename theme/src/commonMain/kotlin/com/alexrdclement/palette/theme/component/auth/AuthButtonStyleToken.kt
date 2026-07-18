package com.alexrdclement.palette.theme.component.auth

import com.alexrdclement.palette.theme.component.core.ButtonStyleToken

enum class AuthButtonStyleToken {
    Primary,
    Secondary,
    Tertiary,
}

fun AuthButtonStyleToken.toButtonStyleToken(): ButtonStyleToken = when (this) {
    AuthButtonStyleToken.Primary -> ButtonStyleToken.Primary
    AuthButtonStyleToken.Secondary -> ButtonStyleToken.Secondary
    AuthButtonStyleToken.Tertiary -> ButtonStyleToken.Tertiary
}
