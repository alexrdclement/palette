package com.alexrdclement.palette.theme.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.layout.BoxWithLabel as ComponentBoxWithLabel

@Composable
fun BoxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ComponentBoxWithLabel(
        label = label,
        modifier = modifier,
        spacing = PaletteTheme.spacing.small,
        labelPadding = PaletteTheme.spacing.xs,
        labelStyle = PaletteTheme.styles.text.labelSmall,
        borderColor = PaletteTheme.colorScheme.outline,
        content = content,
    )
}
