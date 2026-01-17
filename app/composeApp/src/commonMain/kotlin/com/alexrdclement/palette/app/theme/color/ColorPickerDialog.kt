package com.alexrdclement.palette.app.theme.color

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.alexrdclement.palette.components.color.ColorPicker
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRow
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.toColor

@Composable
fun ColorPickerDialog(
    colorToken: ColorToken,
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
                colorToken = colorToken,
                onColorSelected = onColorSelected,
                onDismissRequest = onDismissRequest,
                modifier = Modifier
                    .padding(PaletteTheme.spacing.medium)
            )
        }
    }
}

@Composable
fun ColorPickerDialogContent(
    colorToken: ColorToken,
    onColorSelected: (Color) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val initialColor = colorToken.toColor()
    var color by remember { mutableStateOf(initialColor) }

    Column(
        verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(PaletteTheme.spacing.large)
    ) {
        ColorPicker(
            color = color,
            onColorChange = { color = it },
            modifier = Modifier
                .weight(1f, fill = false)
        )
        ConfirmCancelButtonRow(
            onConfirm = {
                onColorSelected(color)
                onDismissRequest()
            },
            onDismiss = onDismissRequest,
            modifier = Modifier
                .padding(top = PaletteTheme.spacing.large)
        )
    }
}

@Preview
@Composable
fun ColorPickerDialogPreview() {
    PaletteTheme {
        ColorPickerDialogContent(
            colorToken = ColorToken.Primary,
            onColorSelected = {},
            onDismissRequest = {}
        )
    }
}
