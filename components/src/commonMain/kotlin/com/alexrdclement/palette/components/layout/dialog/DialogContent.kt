package com.alexrdclement.palette.components.layout.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.BorderStyle
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.components.core.copy

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
    messageStyle: TextStyle = TextStyle(),
    messageBottomPadding: Dp = 24.dp,
) {
    DialogContent(
        title = title,
        modifier = modifier,
    ) {
        Text(
            text = message,
            style = messageStyle.copy(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(bottom = messageBottomPadding)
        )
        buttonRow(onDismissRequest, Modifier.align(Alignment.End))
    }
}

@Composable
fun DialogContent(
    title: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = TextStyle(),
    borderStyle: BorderStyle? = null,
    spacing: Dp = 16.dp,
    padding: Dp = 24.dp,
    titleBottomPadding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        style = SurfaceStyle(borderStyle = borderStyle),
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
        ) {
            Text(
                text = title,
                style = titleStyle.copy(
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(bottom = titleBottomPadding)
            )
            content()
        }
    }
}

@Preview
@Composable
private fun DialogContentPreview() {
    DialogContent(
        title = "Title",
        message = "Long message to show in the dialog content area.",
        onDismissRequest = {},
        onConfirm = {},
        modifier = Modifier
            .padding(16.dp)
    )
}

@Preview
@Composable
private fun ErrorDialogContentPreview() {
    ErrorDialogContent(
        message = "An error occurred while processing your request.",
        onDismissRequest = {},
        modifier = Modifier
            .padding(16.dp)
    )
}
