package com.alexrdclement.palette.components.demo.control

import com.alexrdclement.palette.components.demo.LocalDemoStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.alexrdclement.palette.components.color.ColorDisplay
import com.alexrdclement.palette.components.color.ColorPicker
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRow
import com.alexrdclement.palette.components.layout.dialog.ConfirmButtonStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRowStyle
import androidx.compose.ui.unit.Dp

data class ColorControlStyle(
    val spacing: Dp = 16.dp,
    val contentSpacing: Dp = 8.dp,
    val dialogPadding: Dp = 24.dp,
)

@Composable
fun ColorControl(
    control: Control.Color,
    modifier: Modifier = Modifier,
) {
    val style = LocalDemoStyle.current.colorControl
    val color by rememberUpdatedState(control.color())
    var showDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(style.spacing),
        modifier = modifier,
    ) {
        Text(
            text = control.name,
            style = LocalDemoStyle.current.labelStyle,
        )
        Button(
            style = LocalDemoStyle.current.buttonStyle,
            onClick = { showDialog = true },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(style.contentSpacing),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            ) {
                ColorDisplay(
                    color = color,
                    modifier = Modifier
                        .fillMaxHeight()
                )
                Text(color.toString(), style = LocalDemoStyle.current.labelStyle)
            }
        }
    }

    if (showDialog) {
        ColorPickerDialog(
            color = color,
            onColorSelected = control.onColorChange,
            onDismissRequest = { showDialog = false },
        )
    }
}

@Composable
private fun ColorPickerDialog(
    color: Color,
    onColorSelected: (Color) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val style = LocalDemoStyle.current.colorControl
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(style = LocalDemoStyle.current.surfaceStyle) {
            ColorPickerDialogContent(
                color = color,
                onColorSelected = onColorSelected,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .padding(style.spacing)
            )
        }
    }
}

@Composable
private fun ColorPickerDialogContent(
    color: Color,
    onColorSelected: (Color) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val style = LocalDemoStyle.current.colorControl
    var currentColor by remember { mutableStateOf(color) }

    Column(
        verticalArrangement = Arrangement.spacedBy(style.spacing),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(style.dialogPadding)
    ) {
        ColorPicker(
            style = LocalDemoStyle.current.colorPickerStyle,
            color = currentColor,
            onColorChange = { currentColor = it },
            modifier = Modifier
                .weight(1f, fill = false)
        )
        ConfirmCancelButtonRow(
            style = ConfirmCancelButtonRowStyle(
                buttonStyle = ConfirmButtonStyle(
                    buttonStyle = LocalDemoStyle.current.buttonStyle,
                    textStyle = LocalDemoStyle.current.labelStyle,
                ),
            ),
            onConfirm = {
                onColorSelected(currentColor)
                onDismissRequest()
            },
            onDismiss = onDismissRequest,
            modifier = Modifier
                .padding(top = style.dialogPadding)
        )
    }
}
