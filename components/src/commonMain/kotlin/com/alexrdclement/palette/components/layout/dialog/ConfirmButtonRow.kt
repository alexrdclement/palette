package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ConfirmButtonRow(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DialogContentSingleButtonRow(
        modifier = modifier,
    ) {
        ConfirmButton(
            onConfirm = onConfirm,
        )
    }
}

@Preview
@Composable
private fun ConfirmButtonRow() {
    PaletteTheme {
        ConfirmButtonRow(
            onConfirm = {},
        )
    }
}
