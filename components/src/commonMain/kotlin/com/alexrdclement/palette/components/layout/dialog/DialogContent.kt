package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogContent(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onConfirm: (() -> Unit)? = null,
) {
    DialogContent(
        title = title,
        message = message,
        buttonRow = { onDismissRequest, modifier ->
            onConfirm?.let {
                ConfirmCancelButtonRow(
                    onConfirm = it,
                    onDismiss = onDismissRequest,
                    modifier = modifier,
                )
            } ?: run {
                ConfirmButtonRow(
                    onConfirm = onDismissRequest,
                    modifier = modifier,
                )
            }
        },
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    )
}

@Composable
fun ErrorDialogContent(
    title: String = "Error",
    message: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DialogContent(
        title = title,
        message = message,
        buttonRow = { onDismissRequest, modifier ->
            ConfirmButtonRow(
                onConfirm = onDismissRequest,
                modifier = modifier,
            )
        },
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    )
}

@Composable
fun DialogContent(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    buttonRow: @Composable (onDismissRequest: () -> Unit, modifier: Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    DialogContent(
        title = title,
        modifier = modifier,
    ) {
        Text(
            text = message,
            style = PaletteTheme.typography.bodyLarge.merge(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(bottom = PaletteTheme.spacing.large)
        )
        buttonRow(onDismissRequest, Modifier.align(Alignment.End))
    }
}

@Composable
fun DialogContent(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        borderStyle = PaletteTheme.styles.border.surface,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(PaletteTheme.spacing.large)
        ) {
            Text(
                text = title,
                style = PaletteTheme.typography.titleLarge.merge(
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(bottom = PaletteTheme.spacing.medium)
            )
            content()
        }
    }
}

@Preview
@Composable
private fun DialogContentPreview() {
    PaletteTheme {
        DialogContent(
            title = "Title",
            message = "Long message to show in the dialog content area.",
            onDismissRequest = {},
            onConfirm = {},
            modifier = Modifier
                .padding(PaletteTheme.spacing.medium)
        )
    }
}

@Preview
@Composable
private fun ErrorDialogContentPreview() {
    PaletteTheme {
        ErrorDialogContent(
            message = "An error occurred while processing your request.",
            onDismissRequest = {},
            modifier = Modifier
                .padding(PaletteTheme.spacing.medium)
        )
    }
}
