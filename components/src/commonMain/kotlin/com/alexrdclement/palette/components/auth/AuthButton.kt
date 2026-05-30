package com.alexrdclement.palette.components.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.TextStyle

enum class AuthState {
    Loading,
    LoggedOut,
    LoggedIn,
}

enum class AuthButtonStyle {
    Primary,
    Secondary,
    Tertiary,
}

@Composable
fun AuthButton(
    authState: AuthState,
    style: AuthButtonStyle,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = PaletteTheme.styles.text.labelLarge,
) {
    when (authState) {
        AuthState.Loading -> AuthButtonText(
            authState = authState,
            textStyle = textStyle,
            modifier = modifier,
        )
        AuthState.LoggedOut -> LogInButton(
            style = style,
            textStyle = textStyle,
            onLogInClick = onLogInClick,
            modifier = modifier,
        )
        AuthState.LoggedIn -> LogOutButton(
            style = style,
            textStyle = textStyle,
            onLogOutClick = onLogOutClick,
            modifier = modifier,
        )
    }
}

@Composable
fun LogInButton(
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: AuthButtonStyle = AuthButtonStyle.Primary,
    textStyle: TextStyle = PaletteTheme.styles.text.labelLarge,
) {
    AuthButtonImpl(
        authState = AuthState.LoggedOut,
        style = style,
        textStyle = textStyle,
        onClick = onLogInClick,
        modifier = modifier,
    )
}

@Composable
fun LogOutButton(
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: AuthButtonStyle = AuthButtonStyle.Secondary,
    textStyle: TextStyle = PaletteTheme.styles.text.labelSmall,
) {
    AuthButtonImpl(
        authState = AuthState.LoggedIn,
        style = style,
        textStyle = textStyle,
        onClick = onLogOutClick,
        modifier = modifier,
    )
}

@Composable
private fun AuthButtonImpl(
    authState: AuthState,
    style: AuthButtonStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = PaletteTheme.styles.text.labelLarge,
) {
    Button(
        style = style.toButtonStyleToken(),
        onClick = onClick,
        modifier = modifier,
    ) {
        AuthButtonText(authState = authState, textStyle = textStyle)
    }
}

private fun AuthButtonStyle.toButtonStyleToken(): ButtonStyleToken = when (this) {
    AuthButtonStyle.Primary -> ButtonStyleToken.Primary
    AuthButtonStyle.Secondary -> ButtonStyleToken.Secondary
    AuthButtonStyle.Tertiary -> ButtonStyleToken.Tertiary
}

@Composable
fun AuthButtonText(
    authState: AuthState,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = PaletteTheme.styles.text.labelLarge,
) {
    Text(
        text = when (authState) {
            AuthState.Loading -> "..."
            AuthState.LoggedIn -> "Log out"
            AuthState.LoggedOut -> "Log in"
        },
        style = textStyle,
        modifier = modifier,
    )
}

internal class AuthStatePreviewParameterProvider : PreviewParameterProvider<AuthState> {
    override val values = sequenceOf(AuthState.Loading, AuthState.LoggedOut, AuthState.LoggedIn)
}

internal class AuthButtonStylePreviewParameterProvider : PreviewParameterProvider<AuthButtonStyle> {
    override val values = sequenceOf(AuthButtonStyle.Primary, AuthButtonStyle.Secondary)
}

@Preview
@Composable
fun PreviewAuthButton(
    @PreviewParameter(AuthStatePreviewParameterProvider::class) authState: AuthState,
    @PreviewParameter(AuthButtonStylePreviewParameterProvider::class) buttonStyle: AuthButtonStyle,
) {
    PaletteTheme {
        AuthButton(
            authState = authState,
            style = buttonStyle,
            onLogInClick = {},
            onLogOutClick = {},
        )
    }
}
