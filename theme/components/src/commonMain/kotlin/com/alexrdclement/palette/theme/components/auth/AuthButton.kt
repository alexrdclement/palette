package com.alexrdclement.palette.theme.components.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.auth.AuthState
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.components.toComponentStyle
import com.alexrdclement.palette.components.auth.AuthButton as ComponentAuthButton
import com.alexrdclement.palette.components.auth.AuthButtonText as ComponentAuthButtonText

enum class AuthButtonStyle {
    Primary,
    Secondary,
    Tertiary,
}

private val AuthButtonStyle.buttonStyleToken: ButtonStyleToken
    get() = when (this) {
        AuthButtonStyle.Primary -> ButtonStyleToken.Primary
        AuthButtonStyle.Secondary -> ButtonStyleToken.Secondary
        AuthButtonStyle.Tertiary -> ButtonStyleToken.Tertiary
    }

private val AuthButtonStyle.textStyle: TextStyle
    @Composable
    get() = when (this) {
        AuthButtonStyle.Primary -> PaletteTheme.styles.text.labelLarge
        AuthButtonStyle.Secondary -> PaletteTheme.styles.text.labelSmall
        AuthButtonStyle.Tertiary -> PaletteTheme.styles.text.labelSmall
    }

@Composable
fun AuthButton(
    authState: AuthState,
    style: AuthButtonStyle,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentAuthButton(
        authState = authState,
        onLogInClick = onLogInClick,
        onLogOutClick = onLogOutClick,
        modifier = modifier,
        buttonStyle = style.buttonStyleToken.toComponentStyle(),
        textStyle = style.textStyle,
    )
}

@Composable
fun LogInButton(
    style: AuthButtonStyle,
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentAuthButton(
        authState = AuthState.LoggedOut,
        onClick = onLogInClick,
        modifier = modifier,
        buttonStyle = style.buttonStyleToken.toComponentStyle(),
        textStyle = style.textStyle,
    )
}

@Composable
fun LogOutButton(
    style: AuthButtonStyle,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentAuthButton(
        authState = AuthState.LoggedIn,
        onClick = onLogOutClick,
        modifier = modifier,
        buttonStyle = style.buttonStyleToken.toComponentStyle(),
        textStyle = style.textStyle,
    )
}

@Composable
fun AuthButton(
    authState: AuthState,
    style: AuthButtonStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentAuthButton(
        authState = authState,
        onClick = onClick,
        modifier = modifier,
        buttonStyle = style.buttonStyleToken.toComponentStyle(),
        textStyle = style.textStyle,
    )
}

@Composable
fun AuthButtonText(
    authState: AuthState,
    style: AuthButtonStyle,
    modifier: Modifier = Modifier,
) {
    ComponentAuthButtonText(
        authState = authState,
        modifier = modifier,
        textStyle = style.textStyle,
    )
}
