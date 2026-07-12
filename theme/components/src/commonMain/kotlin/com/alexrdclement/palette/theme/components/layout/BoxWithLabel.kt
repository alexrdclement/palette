package com.alexrdclement.palette.theme.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.layout.BoxWithLabel as BaseBoxWithLabel

@Composable
fun BoxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    style: BoxWithLabelStyle = PaletteTheme.styles.layout.boxWithLabel,
    content: @Composable () -> Unit,
) = BaseBoxWithLabel(
    label = label,
    modifier = modifier,
    style = style,
    content = content,
)
