package com.alexrdclement.palette.theme.components.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.color.ColorPicker as ComponentColorPicker

@Composable
fun ColorPicker(
    color: Color,
    onColorChange: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentColorPicker(
        color = color,
        onColorChange = onColorChange,
        modifier = modifier,
        spacing = PaletteTheme.spacing.medium,
        labelStyle = PaletteTheme.styles.text.labelLarge,
    )
}
