package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.components.core.BorderStyle
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.core.Surface as ComponentSurface

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = PaletteTheme.shapeScheme.surface,
    color: Color = PaletteTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    borderStyle: BorderStyle? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    ComponentSurface(
        modifier = modifier,
        shape = shape,
        color = color,
        contentColor = contentColor,
        borderStyle = borderStyle,
        content = content,
    )
}

@Composable
fun Surface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    hapticFeedbackEnabled: Boolean = true,
    enabled: Boolean = true,
    shape: Shape = PaletteTheme.shapeScheme.surface,
    color: Color = PaletteTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    borderStyle: BorderStyle? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable (PaddingValues) -> Unit,
) {
    ComponentSurface(
        onClick = onClick,
        modifier = modifier,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        enabled = enabled,
        shape = shape,
        color = color,
        contentColor = contentColor,
        borderStyle = borderStyle,
        indication = PaletteTheme.indication,
        interactionSource = interactionSource,
        content = content,
    )
}
