package com.alexrdclement.palette.components.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle

enum class AuthState {
    Loading,
    LoggedOut,
    LoggedIn,
}

@Composable
fun AuthButton(
    authState: AuthState,
    onLogInClick: () -> Unit,
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonStyle: ButtonStyle = ButtonStyle(),
    textStyle: TextStyle = TextStyle(),
) {
    when (authState) {
        AuthState.Loading -> AuthButtonText(
            authState = authState,
            textStyle = textStyle,
            modifier = modifier,
        )
        AuthState.LoggedOut -> LogInButton(
            buttonStyle = buttonStyle,
            textStyle = textStyle,
            onLogInClick = onLogInClick,
            modifier = modifier,
        )
        AuthState.LoggedIn -> LogOutButton(
            buttonStyle = buttonStyle,
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
    buttonStyle: ButtonStyle = ButtonStyle(),
    textStyle: TextStyle = TextStyle(),
) {
    AuthButton(
        authState = AuthState.LoggedOut,
        buttonStyle = buttonStyle,
        textStyle = textStyle,
        onClick = onLogInClick,
        modifier = modifier,
    )
}

@Composable
fun LogOutButton(
    onLogOutClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonStyle: ButtonStyle = ButtonStyle(),
    textStyle: TextStyle = TextStyle(),
) {
    AuthButton(
        authState = AuthState.LoggedIn,
        buttonStyle = buttonStyle,
        textStyle = textStyle,
        onClick = onLogOutClick,
        modifier = modifier,
    )
}

@Composable
fun AuthButton(
    authState: AuthState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonStyle: ButtonStyle = ButtonStyle(),
    textStyle: TextStyle = TextStyle(),
) {
    Button(
        style = buttonStyle,
        onClick = onClick,
        modifier = modifier,
    ) {
        AuthButtonText(
            authState = authState,
            textStyle = textStyle,
        )
    }
}

@Composable
fun AuthButtonText(
    authState: AuthState,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
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

@Preview
@Composable
fun PreviewAuthButton(
    @PreviewParameter(AuthStatePreviewParameterProvider::class) authState: AuthState,
) {
    Surface {
        AuthButton(
            authState = authState,
            onLogInClick = {},
            onLogOutClick = {},
        )
    }
}
