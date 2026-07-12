package com.alexrdclement.palette.theme.component.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.theme.semantic.ColorToken
import com.alexrdclement.palette.theme.semantic.ShapeToken
import com.alexrdclement.palette.theme.semantic.toColor
import com.alexrdclement.palette.theme.semantic.toShape
import com.alexrdclement.palette.components.core.BorderStyle as ComponentBorderStyle

data class BorderStyleTokenSet(
    val width: Dp = Dp.Hairline,
    val color: ColorToken = ColorToken.Outline,
    val shape: ShapeToken = ShapeToken.Surface,
)

@Composable
fun BorderStyleTokenSet.toComponentStyle(): ComponentBorderStyle = ComponentBorderStyle(
    width = this.width,
    color = this.color.toColor(),
    shape = this.shape.toShape(),
)
