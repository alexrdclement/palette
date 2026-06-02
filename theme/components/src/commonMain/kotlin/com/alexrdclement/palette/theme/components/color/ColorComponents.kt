package com.alexrdclement.palette.theme.components.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.toComponentStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.components.color.ColorDisplay as ComponentColorDisplay
import com.alexrdclement.palette.components.color.ColorPicker as ComponentColorPicker

@Composable
fun ColorDisplay(
    color: Color,
    modifier: Modifier = Modifier,
) {
    ComponentColorDisplay(
        color = color,
        modifier = modifier,
        shape = PaletteTheme.shapeScheme.primary,
        borderStyle = BorderStyleToken.Primary.toComponentStyle(),
    )
}

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
