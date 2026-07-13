package com.alexrdclement.palette.theme.primitive

import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Shape

enum class ShapePrimitiveToken {
    Circle,
    Rectangle,
    RoundRect,
    Triangle,
    Diamond,
}

data class ShapePrimitives(
    val circle: Shape = Shape.Circle,
    val rectangle: Shape = Shape.Rectangle(),
    val roundRect: Shape = Shape.Rectangle(cornerRadius = 64.dp),
    val triangle: Shape = Shape.Triangle,
    val diamond: Shape = Shape.Diamond,
) {
    fun shape(token: ShapePrimitiveToken): Shape = when (token) {
        ShapePrimitiveToken.Circle -> circle
        ShapePrimitiveToken.Rectangle -> rectangle
        ShapePrimitiveToken.RoundRect -> roundRect
        ShapePrimitiveToken.Triangle -> triangle
        ShapePrimitiveToken.Diamond -> diamond
    }
}

val PaletteShapePrimitives = ShapePrimitives()
