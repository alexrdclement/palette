package com.alexrdclement.palette.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ChevronDirection {
    Up,
    Down,
    Left,
    Right,
}

data class ChevronButtonStyle(
    val buttonStyle: ButtonStyle = ButtonStyle(contentPadding = PaddingValues(16.dp)),
    val iconStyle: IconStyle = IconStyle(size = IconSize.Fill),
)

@Composable
fun ChevronButton(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
    style: ChevronButtonStyle = ChevronButtonStyle(),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        style = style.buttonStyle,
        modifier = modifier
    ) {
        ChevronIcon(
            direction = direction,
            style = style.iconStyle,
        )
    }
}

/**
 * A directional chevron glyph. It is a square glyph referenced to its height (it typically sits in
 * a width-unbounded row), so [IconStyle.size] is mapped onto the height axis and a square aspect is
 * kept: [IconSize.Fill] fills the height, [IconSize.Scale] takes a fraction of it, [IconSize.Fixed]
 * pins it. The general [Modifier.iconSize] fills both axes, which would reserve width beside the
 * glyph, so the mapping is done here. Rendering routes through [Icon] so the slot and color are
 * shared with the [ImageVector] overload.
 */
@Composable
fun ChevronIcon(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
    style: IconStyle = IconStyle(),
) {
    val rotation = when (direction) {
        ChevronDirection.Left -> 0f
        ChevronDirection.Up -> 90f
        ChevronDirection.Down -> 270f
        ChevronDirection.Right -> 180f
    }
    val sizeModifier = when (val size = style.size) {
        is IconSize.Fixed -> Modifier.size(size.size)
        is IconSize.Scale -> Modifier.fillMaxHeight(size.fraction)
        IconSize.Fill -> Modifier.fillMaxHeight()
    }
    Icon(
        modifier = modifier
            .then(sizeModifier)
            .aspectRatio(1f, matchHeightConstraintsFirst = false),
        style = IconStyle(color = style.color),
    ) { color ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation)
                .clip(ChevronIconShape)
                .background(color)
        )
    }
}

private const val ChevronIconWidthProportion = 0.8f
private val ChevronIconShape: Shape = GenericShape { size, _ ->
    val offsetEndX = size.width * ChevronIconWidthProportion
    moveTo(offsetEndX, 0f)
    lineTo(0f, size.height / 2f)
    lineTo(offsetEndX, size.height)
    lineTo(offsetEndX, 0f)
}

@Preview
@Composable
private fun ChevronButtonPreview() {
    Surface {
        ChevronButton(
            direction = ChevronDirection.Right,
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun ChevronIconPreview() {
    Surface {
        ChevronIcon(
            direction = ChevronDirection.Right,
        )
    }
}
