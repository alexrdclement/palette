package com.alexrdclement.palette.components.auth

import androidx.compose.foundation.style.Style
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme
import com.alexrdclement.palette.theme.styles.toRenderStyle
import com.alexrdclement.palette.theme.toColor

enum class AuthState {
    Loading,
    LoggedOut,
    LoggedIn,
}

@Composable
fun AuthButton(
    authState: AuthState,
    style: Style,
    contentColor: Color,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (authState) {
        AuthState.Loading -> AuthButtonText(
            authState = authState,
            modifier = modifier,
        )
        AuthState.LoggedOut -> LogInButton(
            style = style,
            contentColor = contentColor,
            onLogInClick = onLogInClick,
            modifier = modifier,
        )
        AuthState.LoggedIn -> LogOutButton(
            style = style,
            contentColor = contentColor,
            onLogOutClick = onLogOutClick,
            modifier = modifier,
        )
    }
}

@Composable
fun LogInButton(
    onLogInClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: Style = AuthButtonDefaults.logInStyle,
    contentColor: Color = PaletteTheme.colorScheme.onPrimary,
) {
    AuthButton(
        authState = AuthState.LoggedOut,
        style = style,
        contentColor = contentColor,
        onClick = onLogInClick,
        modifier = modifier,
    )
}

@Composable
fun LogOutButton(
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: Style = AuthButtonDefaults.logOutStyle,
    contentColor: Color = PaletteTheme.colorScheme.secondary,
) {
    AuthButton(
        authState = AuthState.LoggedIn,
        style = style,
        contentColor = contentColor,
        onClick = onLogOutClick,
        modifier = modifier,
    )
}

@Composable
fun AuthButton(
    authState: AuthState,
    style: Style,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        style = style,
        contentColor = contentColor,
        onClick = onClick,
        modifier = modifier,
    ) {
        AuthButtonText(authState = authState)
    }
}

@Composable
fun AuthButtonText(
    authState: AuthState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = when (authState) {
            AuthState.Loading -> "..."
            AuthState.LoggedIn -> "Log out"
            AuthState.LoggedOut -> "Log in"
        },
        style = PaletteTheme.styles.text.labelLarge,
        modifier = modifier,
    )
}

object AuthButtonDefaults {
    val logInStyle: Style get() = PaletteButtonStyleScheme.primary.toRenderStyle()
    val logOutStyle: Style get() = PaletteButtonStyleScheme.secondary.toRenderStyle()
}

internal class AuthStatePreviewParameterProvider : PreviewParameterProvider<AuthState> {
    override val values = sequenceOf(AuthState.Loading, AuthState.LoggedOut, AuthState.LoggedIn)
}

@Preview
@Composable
fun PreviewLogInButton(
    @PreviewParameter(AuthStatePreviewParameterProvider::class) authState: AuthState,
) {
    PaletteTheme {
        AuthButton(
            authState = authState,
            style = AuthButtonDefaults.logInStyle,
            contentColor = PaletteTheme.colorScheme.onPrimary,
            onLogInClick = {},
            onLogOutClick = {},
        )
    }
}

@Preview
@Composable
fun PreviewLogOutButton(
    @PreviewParameter(AuthStatePreviewParameterProvider::class) authState: AuthState,
) {
    PaletteTheme {
        AuthButton(
            authState = authState,
            style = AuthButtonDefaults.logOutStyle,
            contentColor = PaletteTheme.colorScheme.secondary,
            onLogInClick = {},
            onLogOutClick = {},
        )
    }
}
