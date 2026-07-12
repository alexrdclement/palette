package com.alexrdclement.palette.theme.semantic

import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.components.core.toComposeShape
import androidx.compose.ui.graphics.Shape as ComposeShape

enum class ShapeToken {
    Primary,
    Secondary,
    Tertiary,
    Surface,
}

fun ShapeToken.toShape(shapeScheme: ShapeScheme): Shape {
    return when (this) {
        ShapeToken.Primary -> shapeScheme.primary
        ShapeToken.Secondary -> shapeScheme.secondary
        ShapeToken.Tertiary -> shapeScheme.tertiary
        ShapeToken.Surface -> shapeScheme.surface
    }
}

@Composable
fun ShapeToken.toShape(): Shape {
    return toShape(PaletteTheme.semantic.shape)
}

fun ShapeToken.toComposeShape(shapeScheme: ShapeScheme): ComposeShape {
    return this.toShape(shapeScheme).toComposeShape()
}

@Composable
fun ShapeToken.toComposeShape(): ComposeShape {
    return toComposeShape(PaletteTheme.semantic.shape)
}
