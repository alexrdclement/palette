package com.alexrdclement.palette.theme.modifiers

import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toComposeShape

// TODO token-system: Move to modifiers

data class BorderStyle(
    val width: Dp = Dp.Hairline,
    val color: ColorToken = ColorToken.Outline,
    val shape: ShapeToken = ShapeToken.Surface,
)

@Composable
fun Modifier.border(
    style: BorderStyle,
): Modifier = this.border(
    width = style.width,
    color = style.color.toColor(),
    shape = style.shape.toComposeShape(),
)
