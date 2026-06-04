package com.alexrdclement.palette.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.LocalContentColor

enum class ChevronDirection {
    Up,
    Down,
    Left,
    Right,
}

data class ChevronButtonStyle(
    val buttonStyle: ButtonStyle = ButtonStyle(),
    val iconColor: Color = Color.Unspecified,
    val contentPadding: PaddingValues = PaddingValues(16.dp),
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
        contentPadding = style.contentPadding,
        modifier = modifier
    ) {
        ChevronIcon(
            direction = direction,
            color = style.iconColor.takeIf { it != Color.Unspecified } ?: LocalContentColor.current,
        )
    }
}

@Composable
fun ChevronIcon(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
) {
    val rotation = when (direction) {
        ChevronDirection.Left -> 0f
        ChevronDirection.Up -> 90f
        ChevronDirection.Down -> 270f
        ChevronDirection.Right -> 180f
    }
    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f, matchHeightConstraintsFirst = false)
            .rotate(rotation)
            .clip(ChevronIconShape)
            .background(color)
    )
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
