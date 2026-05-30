package com.alexrdclement.palette.components.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.style.PaletteStyle
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme
import com.alexrdclement.palette.theme.styles.TextStyle
import com.alexrdclement.palette.theme.styles.toRenderStyle

// AuthButtonStyle is PaletteStyle — callers write PaletteStyle { background(...); contentColor(...) }
// or retrieve a pre-built style from AuthButtonDefaults. No separate scope is needed; the full
// PaletteStyleScope DSL is available and appropriate here.
//
// The explicit contentColor: Color bridge params on LogInButton/LogOutButton exist only for
// LocalContentColor (Canvas/Image draw code). They can be removed once those callers are gone.
typealias AuthButtonStyle = PaletteStyle

enum class AuthState {
    Loading,
    LoggedOut,
    LoggedIn,
}

// Convenience wrapper that switches between LogInButton and LogOutButton based on authState.
// For custom styling, use LogInButton / LogOutButton directly.
@Composable
fun AuthButton(
    authState: AuthState,
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
            textStyle = textStyle,
            onLogInClick = onLogInClick,
            modifier = modifier,
        )
        AuthState.LoggedIn -> LogOutButton(
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
    style: AuthButtonStyle = AuthButtonDefaults.logInStyle,
    contentColor: Color = PaletteTheme.colorScheme.onPrimary,
    textStyle: TextStyle = PaletteTheme.styles.text.labelLarge,
) {
    Button(
        style = style,
        contentColor = contentColor,
        onClick = onLogInClick,
        modifier = modifier,
    ) {
        AuthButtonText(authState = AuthState.LoggedOut, textStyle = textStyle)
    }
}

@Composable
fun LogOutButton(
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: AuthButtonStyle = AuthButtonDefaults.logOutStyle,
    contentColor: Color = PaletteTheme.colorScheme.secondary,
    textStyle: TextStyle = PaletteTheme.styles.text.labelSmall,
) {
    Button(
        style = style,
        contentColor = contentColor,
        onClick = onLogOutClick,
        modifier = modifier,
    ) {
        AuthButtonText(authState = AuthState.LoggedIn, textStyle = textStyle)
    }
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

object AuthButtonDefaults {
    val logInStyle: AuthButtonStyle get() = PaletteButtonStyleScheme.primary.toRenderStyle()
    val logOutStyle: AuthButtonStyle get() = PaletteButtonStyleScheme.secondary.toRenderStyle()
}

internal class AuthStatePreviewParameterProvider : PreviewParameterProvider<AuthState> {
    override val values = sequenceOf(AuthState.Loading, AuthState.LoggedOut, AuthState.LoggedIn)
}

@Preview
@Composable
private fun PreviewAuthButton(
    @PreviewParameter(AuthStatePreviewParameterProvider::class) authState: AuthState,
) {
    PaletteTheme {
        AuthButton(
            authState = authState,
            onLogInClick = {},
            onLogOutClick = {},
        )
    }
}
