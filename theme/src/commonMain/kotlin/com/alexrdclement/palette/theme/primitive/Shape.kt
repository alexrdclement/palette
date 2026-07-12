package com.alexrdclement.palette.theme.primitive

import com.alexrdclement.palette.components.core.Shape

/**
 * Primitive shape tokens: the raw, unopinionated shapes. Semantic shape tokens
 * (com.alexrdclement.palette.theme.semantic.ShapeToken.Primary, ...) reference these. Backed by
 * [Shape] in the components module, which remains the source of truth for shape geometry.
 */
object PaletteShapePrimitives {
    val circle: Shape get() = Shape.Circle
    val rectangle: Shape get() = Shape.Rectangle()
    val triangle: Shape get() = Shape.Triangle
    val diamond: Shape get() = Shape.Diamond
}
