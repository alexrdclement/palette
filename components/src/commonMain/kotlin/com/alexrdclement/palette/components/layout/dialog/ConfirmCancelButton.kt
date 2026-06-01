package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.Text

@Composable
fun ConfirmButton(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle(),
) {
    Button(
        style = style,
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
    style: ButtonStyle = ButtonStyle(),
) {
    Button(
        style = style,
        onClick = onDismiss,
        modifier = modifier,
    ) {
        Text("Cancel")
    }
}

@Preview
@Composable
private fun ConfirmButtonPreview() {
    ConfirmButton(
        onConfirm = {},
    )
}

@Preview
@Composable
private fun CancelButtonPreview() {
    CancelButton(
        onDismiss = {},
    )
}
