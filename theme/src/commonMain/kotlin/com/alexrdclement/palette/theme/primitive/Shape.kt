package com.alexrdclement.palette.theme.primitive

import com.alexrdclement.palette.components.core.Shape

/** Identifies a primitive shape that a semantic shape token can reference. */
enum class ShapePrimitiveToken {
    Circle,
    Rectangle,
    Triangle,
    Diamond,
}

/**
 * Primitive shape tokens: the raw base shapes that semantic shape tokens reference. The rectangle's
 * corner radius is editable at the primitive level and is therefore shared by every semantic role
 * that references [Rectangle][ShapePrimitiveToken.Rectangle]. Backed by [Shape] in the components
 * module, which remains the source of truth for shape geometry.
 */
data class ShapePrimitives(
    val circle: Shape = Shape.Circle,
    val rectangle: Shape = Shape.Rectangle(),
    val triangle: Shape = Shape.Triangle,
    val diamond: Shape = Shape.Diamond,
) {
    fun shape(token: ShapePrimitiveToken): Shape = when (token) {
        ShapePrimitiveToken.Circle -> circle
        ShapePrimitiveToken.Rectangle -> rectangle
        ShapePrimitiveToken.Triangle -> triangle
        ShapePrimitiveToken.Diamond -> diamond
    }
}

val PaletteShapePrimitives = ShapePrimitives()
