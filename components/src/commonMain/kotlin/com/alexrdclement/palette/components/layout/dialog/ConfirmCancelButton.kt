package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle

data class ConfirmButtonStyle(
    val buttonStyle: ButtonStyle = ButtonStyle.Default(),
    val textStyle: TextStyle = TextStyle(),
)

@Composable
fun ConfirmButton(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    style: ConfirmButtonStyle = ConfirmButtonStyle(),
) {
    Button(
        style = style.buttonStyle,
        onClick = onConfirm,
        modifier = modifier,
    ) {
        Text("OK", style = style.textStyle)
    }
}

@Composable
fun CancelButton(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    style: ConfirmButtonStyle = ConfirmButtonStyle(),
) {
    Button(
        style = style.buttonStyle,
        onClick = onDismiss,
        modifier = modifier,
    ) {
        Text("Cancel", style = style.textStyle)
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
