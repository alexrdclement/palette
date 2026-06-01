package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmCancelButtonRow(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    spacing: Dp = 16.dp,
) {
    DialogContentButtonRow(
        modifier = modifier,
    ) {
        CancelButton(
            onDismiss = onDismiss,
        )
        ConfirmButton(
            onConfirm = onConfirm,
            modifier = Modifier
                .padding(start = spacing)
        )
    }
}

@Preview
@Composable
fun ConfirmCancelButtonRowPreview() {
    ConfirmCancelButtonRow(
        onConfirm = {},
        onDismiss = {},
    )
}
