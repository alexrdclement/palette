package com.alexrdclement.palette.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DeleteConfirmationDialogContent(
    contentTitle: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DialogContent(
        title = "Delete \"$contentTitle\"?",
        message = "This action cannot be undone.",
        onConfirm = onConfirm,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    )
}

@Preview
@Composable
fun DeleteConfirmationDialogContentPreview() {
    PaletteTheme {
        DeleteConfirmationDialogContent(
            contentTitle = "Log entry",
            onConfirm = {},
            onDismissRequest = {},
        )
    }
}
