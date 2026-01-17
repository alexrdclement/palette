package com.alexrdclement.palette.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.contentColorFor
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.Shape
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.border
import com.alexrdclement.palette.theme.toComposeShape

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = PaletteTheme.shapeScheme.surface,
    color: Color = PaletteTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    borderStyle: BorderStyle? = null,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier = modifier
                .shapeLayout(shape)
                .surface(
                    composeShape = shape.toComposeShape(),
                    backgroundColor = color,
                    borderStyle = borderStyle,
                )
                .semantics(mergeDescendants = false) {
                    isTraversalGroup = true
                }
                .pointerInput(Unit) {},
            propagateMinConstraints = true
        ) {
            content()
        }
    }
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
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            propagateMinConstraints = true,
            modifier = modifier
                .shapeLayout(shape)
                .shapeClickable(
                    shape = shape,
                    interactionSource = interactionSource,
                    indication = PaletteTheme.indication,
                    enabled = enabled,
                    onClick = onClick,
                    onLongClickLabel = onLongClickLabel,
                    onLongClick = onLongClick,
                    onDoubleClick = onDoubleClick,
                    hapticFeedbackEnabled = hapticFeedbackEnabled,
                )
                .surface(
                    composeShape = shape.toComposeShape(),
                    backgroundColor = color,
                    borderStyle = borderStyle,
                )
        ) {
            content()
        }
    }
}

@Composable
private fun Modifier.surface(
    composeShape: androidx.compose.ui.graphics.Shape,
    backgroundColor: Color,
    borderStyle: BorderStyle?,
) = this
    .graphicsLayer(shape = composeShape, clip = true)
    .then(if (borderStyle != null) Modifier.border(style = borderStyle) else Modifier)
    .background(color = backgroundColor, shape = composeShape)

@Preview
@Composable
private fun Preview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isDarkTheme: Boolean,
) {
    PaletteTheme(isDarkMode = isDarkTheme) {
        Surface {
            Text("Hello world")
        }
    }
}

@Preview
@Composable
private fun PreviewClickable(
    @PreviewParameter(BoolPreviewParameterProvider::class) isDarkTheme: Boolean,
    @PreviewParameter(BoolPreviewParameterProvider::class) enabled: Boolean,
) {
    PaletteTheme(isDarkMode = isDarkTheme) {
        Surface(
            onClick = {},
            enabled = enabled,
        ) {
            Text("Hello world")
        }
    }
}
