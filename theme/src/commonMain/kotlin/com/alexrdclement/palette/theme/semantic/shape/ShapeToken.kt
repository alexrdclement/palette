package com.alexrdclement.palette.theme.semantic.shape

import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.components.core.toComposeShape
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import com.alexrdclement.palette.theme.primitive.ShapePrimitives
import androidx.compose.ui.graphics.Shape as ComposeShape

enum class ShapeToken {
    Primary,
    Secondary,
    Tertiary,
    Surface,
}

fun ShapeToken.primitiveToken(shapeScheme: ShapeScheme): ShapePrimitiveToken {
    return when (this) {
        ShapeToken.Primary -> shapeScheme.primary
        ShapeToken.Secondary -> shapeScheme.secondary
        ShapeToken.Tertiary -> shapeScheme.tertiary
        ShapeToken.Surface -> shapeScheme.surface
    }
}

fun ShapeToken.toShape(shapeScheme: ShapeScheme, shapePrimitives: ShapePrimitives): Shape {
    return shapePrimitives.shape(primitiveToken(shapeScheme))
}

@Composable
fun ShapeToken.toShape(): Shape {
    return toShape(PaletteTheme.semantic.shape, PaletteTheme.primitive.shape)
}

fun ShapeToken.toComposeShape(
    shapeScheme: ShapeScheme,
    shapePrimitives: ShapePrimitives,
): ComposeShape {
    return this.toShape(shapeScheme, shapePrimitives).toComposeShape()
}

@Composable
fun ShapeToken.toComposeShape(): ComposeShape {
    return toComposeShape(PaletteTheme.semantic.shape, PaletteTheme.primitive.shape)
}
