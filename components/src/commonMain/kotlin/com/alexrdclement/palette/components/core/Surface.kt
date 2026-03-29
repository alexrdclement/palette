package com.alexrdclement.palette.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.contentColorFor
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.Shape
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.border
import com.alexrdclement.palette.theme.toComposeShape
import kotlin.math.sqrt

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = PaletteTheme.shapeScheme.surface,
    color: Color = PaletteTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    borderStyle: BorderStyle? = null,
    content: @Composable (PaddingValues) -> Unit
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
            ShapeContent(shape, content)
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
    content: @Composable (PaddingValues) -> Unit
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
            ShapeContent(shape, content)
        }
    }
}

@Composable
private fun ShapeContent(
    shape: Shape,
    content: @Composable (PaddingValues) -> Unit,
) {
    when (shape) {
        Shape.Circle, Shape.Diamond -> {
            BoxWithConstraints(contentAlignment = Alignment.Center) {
                val inset: Dp = minOf(maxWidth, maxHeight) / 2 * (1f - (1f / sqrt(2f)))
                content(PaddingValues(inset))
            }
        }
        Shape.Triangle -> {
            BoxWithConstraints(contentAlignment = Alignment.Center) {
                // Largest axis-aligned rectangle inscribed in an equilateral triangle:
                // horizontal inset = width / 4 on each side, top inset = height / 3
                content(
                    PaddingValues(
                        start = maxWidth / 4,
                        end = maxWidth / 4,
                        top = maxHeight / 3,
                        bottom = 0.dp,
                    )
                )
            }
        }
        is Shape.Rectangle -> {
            if (shape.cornerRadius > 0.dp) {
                SubcomposeLayout { constraints ->
                    val maxWidthDp = if (constraints.hasBoundedWidth) constraints.maxWidth.toDp() else Dp.Unspecified
                    val maxHeightDp = if (constraints.hasBoundedHeight) constraints.maxHeight.toDp() else Dp.Unspecified
                    val actualRadius = if (maxWidthDp.isSpecified && maxHeightDp.isSpecified) {
                        minOf(shape.cornerRadius, minOf(maxWidthDp, maxHeightDp) / 2)
                    } else {
                        shape.cornerRadius
                    }
                    val inset = actualRadius * (1f - (1f / sqrt(2f)))
                    val measurables = subcompose(Unit) { content(PaddingValues(inset)) }
                    val placeables = measurables.map { it.measure(constraints) }
                    val width = placeables.maxOfOrNull { it.width } ?: 0
                    val height = placeables.maxOfOrNull { it.height } ?: 0
                    layout(width, height) {
                        placeables.forEach { placeable ->
                            placeable.placeRelative(
                                x = (width - placeable.width) / 2,
                                y = (height - placeable.height) / 2,
                            )
                        }
                    }
                }
            } else {
                content(PaddingValues(0.dp))
            }
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
