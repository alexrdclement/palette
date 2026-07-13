package com.alexrdclement.palette.theme.semantic.shape

import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken

/**
 * Semantic shape tokens: each role references a primitive shape token
 * ([com.alexrdclement.palette.theme.primitive.ShapePrimitives]). Roles that reference the same
 * primitive share its geometry (e.g. the rectangle's corner radius).
 */
data class ShapeScheme(
    val primary: ShapePrimitiveToken,
    val secondary: ShapePrimitiveToken,
    val tertiary: ShapePrimitiveToken,
    val surface: ShapePrimitiveToken,
)

val PaletteShapeScheme = ShapeScheme(
    primary = ShapePrimitiveToken.Circle,
    secondary = ShapePrimitiveToken.Rectangle,
    tertiary = ShapePrimitiveToken.Rectangle,
    surface = ShapePrimitiveToken.Rectangle,
)

fun ShapeScheme.copy(
    token: ShapeToken,
    shapePrimitiveToken: ShapePrimitiveToken,
) = this.copy(
    primary = if (token == ShapeToken.Primary) shapePrimitiveToken else this.primary,
    secondary = if (token == ShapeToken.Secondary) shapePrimitiveToken else this.secondary,
    tertiary = if (token == ShapeToken.Tertiary) shapePrimitiveToken else this.tertiary,
    surface = if (token == ShapeToken.Surface) shapePrimitiveToken else this.surface,
)
