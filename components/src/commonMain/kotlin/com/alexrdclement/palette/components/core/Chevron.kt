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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.style.PaletteStyle

enum class ChevronDirection {
    Up,
    Down,
    Left,
    Right,
}

@Composable
fun ChevronButton(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
    style: PaletteStyle = ChevronButtonDefaults.style,
    contentColor: androidx.compose.ui.graphics.Color = PaletteTheme.colorScheme.primary,
    contentPadding: PaddingValues = PaddingValues(PaletteTheme.spacing.medium),
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        style = style,
        contentColor = contentColor,
        contentPadding = contentPadding,
        modifier = modifier
    ) {
        ChevronIcon(
            direction = direction,
        )
    }
}

object ChevronButtonDefaults {
    val style: PaletteStyle get() = PaletteStyle {
        background(ColorToken.Surface)
        contentColor(ColorToken.Primary)
    }
}

@Composable
fun ChevronIcon(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
) {
    val rotation = when (direction) {
        ChevronDirection.Left -> 0f
        ChevronDirection.Up -> 90f
        ChevronDirection.Down -> 270f
        ChevronDirection.Right -> 180f
    }
    // Color is read from LocalContentColor rather than hardcoded so ChevronIcon responds to
    // the content color set by the enclosing Button / Surface style.
    val color = LocalContentColor.current
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
    PaletteTheme {
        Surface {
            ChevronButton(
                direction = ChevronDirection.Right,
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
private fun ChevronIconPreview() {
    PaletteTheme {
        Surface {
            ChevronIcon(
                direction = ChevronDirection.Right,
            )
        }
    }
}
