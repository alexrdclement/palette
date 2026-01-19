package com.alexrdclement.palette.components.demo.control

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
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRow
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

@Composable
fun ColorControl(
    control: Control.Color,
    modifier: Modifier = Modifier,
) {
    val color by rememberUpdatedState(control.color())
    var showDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
        modifier = modifier,
    ) {
        Text(
            text = control.name,
            style = PaletteTheme.styles.text.labelLarge,
        )
        Button(
            style = ButtonStyleToken.Secondary,
            onClick = { showDialog = true },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            ) {
                ColorDisplay(
                    color = color,
                    modifier = Modifier
                        .fillMaxHeight()
                )
                Text(color.toString(), style = PaletteTheme.styles.text.labelLarge)
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
        Surface(
            borderStyle = PaletteTheme.styles.border.surface,
        ) {
            ColorPickerDialogContent(
                color = color,
                onColorSelected = onColorSelected,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .padding(PaletteTheme.spacing.medium)
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
        verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(PaletteTheme.spacing.large)
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
                .padding(top = PaletteTheme.spacing.large)
        )
    }
}
