package com.alexrdclement.palette.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ConfirmButton(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        style = ButtonStyleToken.Secondary,
        onClick = onConfirm,
        modifier = modifier,
    ) {
        Text("OK")
    }
}

@Composable
fun CancelButton(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        style = ButtonStyleToken.Secondary,
        onClick = onDismiss,
        modifier = modifier,
    ) {
        Text("Cancel")
    }
}

@Preview
@Composable
private fun ConfirmButtonPreview() {
    PaletteTheme {
        ConfirmButton(
            onConfirm = {},
        )
    }
}

@Preview
@Composable
private fun CancelButtonPreview() {
    PaletteTheme {
        CancelButton(
            onDismiss = {},
        )
    }
}
