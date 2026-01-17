package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ConfirmCancelButtonRow(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
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
                .padding(start = PaletteTheme.spacing.medium)
        )
    }
}

@Preview
@Composable
fun ConfirmCancelButtonRowPreview() {
    PaletteTheme {
        ConfirmCancelButtonRow(
            onConfirm = {},
            onDismiss = {},
        )
    }
}
