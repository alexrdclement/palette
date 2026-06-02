package com.alexrdclement.palette.theme.components.layout.dialog

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.components.toComponentStyle
import com.alexrdclement.palette.components.layout.dialog.CancelButton as ComponentCancelButton
import com.alexrdclement.palette.components.layout.dialog.ConfirmButton as ComponentConfirmButton
import com.alexrdclement.palette.components.layout.dialog.ConfirmButtonRow as ComponentConfirmButtonRow
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRow as ComponentConfirmCancelButtonRow
import com.alexrdclement.palette.components.layout.dialog.DialogContent as ComponentDialogContent
import com.alexrdclement.palette.components.layout.dialog.IndeterminateProgressDialogContent as ComponentIndeterminateProgressDialogContent

@Composable
private fun dialogButtonStyle(): ButtonStyle = ButtonStyleToken.Secondary.toComponentStyle()

@Composable
fun ConfirmButton(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentConfirmButton(
        onConfirm = onConfirm,
        modifier = modifier,
        style = dialogButtonStyle(),
    )
}

@Composable
fun CancelButton(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentCancelButton(
        onDismiss = onDismiss,
        modifier = modifier,
        style = dialogButtonStyle(),
    )
}

@Composable
fun ConfirmButtonRow(
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentConfirmButtonRow(
        onConfirm = onConfirm,
        modifier = modifier,
    )
}

@Composable
fun ConfirmCancelButtonRow(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentConfirmCancelButtonRow(
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        modifier = modifier,
        spacing = PaletteTheme.spacing.medium,
    )
}

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
        buttonRow = { onDismiss, rowModifier ->
            if (onConfirm != null) {
                ConfirmCancelButtonRow(
                    onConfirm = onConfirm,
                    onDismiss = onDismiss,
                    modifier = rowModifier,
                )
            } else {
                ConfirmButtonRow(
                    onConfirm = onDismiss,
                    modifier = rowModifier,
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
        buttonRow = { onDismiss, rowModifier ->
            ConfirmButtonRow(
                onConfirm = onDismiss,
                modifier = rowModifier,
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
    ComponentDialogContent(
        title = title,
        message = message,
        onDismissRequest = onDismissRequest,
        buttonRow = buttonRow,
        modifier = modifier,
        messageStyle = PaletteTheme.styles.text.bodyLarge,
        messageBottomPadding = PaletteTheme.spacing.large,
    )
}

@Composable
fun DialogContent(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    ComponentDialogContent(
        title = title,
        modifier = modifier,
        titleStyle = PaletteTheme.styles.text.titleLarge,
        borderStyle = BorderStyleToken.Surface.toComponentStyle(),
        spacing = PaletteTheme.spacing.medium,
        padding = PaletteTheme.spacing.large,
        titleBottomPadding = PaletteTheme.spacing.medium,
        content = content,
    )
}

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

@Composable
fun IndeterminateProgressDialogContent(
    title: String = "",
    modifier: Modifier = Modifier,
) {
    DialogContent(
        title = title,
        modifier = modifier,
    ) {
        ComponentIndeterminateProgressDialogContent(
            title = title,
            modifier = Modifier,
        )
    }
}
