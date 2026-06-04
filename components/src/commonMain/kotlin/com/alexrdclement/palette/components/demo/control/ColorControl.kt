package com.alexrdclement.palette.components.demo.control

import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.TextStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.alexrdclement.palette.components.color.ColorDisplay
import com.alexrdclement.palette.components.color.ColorPicker
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRow

@Composable
fun ColorControl(
    control: Control.Color,
    modifier: Modifier = Modifier,
) {
    val color by rememberUpdatedState(control.color())
    var showDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        Text(
            text = control.name,
            style = TextStyle(),
        )
        Button(
            style = ButtonStyle(),
            onClick = { showDialog = true },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            ) {
                ColorDisplay(
                    color = color,
                    modifier = Modifier
                        .fillMaxHeight()
                )
                Text(color.toString(), style = TextStyle())
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
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface {
            ColorPickerDialogContent(
                color = color,
                onColorSelected = onColorSelected,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .padding(16.dp)
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
    var currentColor by remember { mutableStateOf(color) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
    ) {
        ColorPicker(
            color = currentColor,
            onColorChange = { currentColor = it },
            modifier = Modifier
                .weight(1f, fill = false)
        )
        ConfirmCancelButtonRow(
            onConfirm = {
                onColorSelected(currentColor)
                onDismissRequest()
            },
            onDismiss = onDismissRequest,
            modifier = Modifier
                .padding(top = 24.dp)
        )
    }
}
