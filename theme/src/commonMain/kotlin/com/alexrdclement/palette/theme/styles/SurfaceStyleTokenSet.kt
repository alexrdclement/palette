package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.toComponentStyle
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.components.core.SurfaceStyle as ComponentSurfaceStyle

data class SurfaceStyleTokenSet(
    val color: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
)

@Composable
fun SurfaceStyleTokenSet.toComponentStyle(): ComponentSurfaceStyle = ComponentSurfaceStyle(
    shape = shape.toShape(),
    color = color.toColor(),
    borderStyle = borderStyle?.toComponentStyle(),
    indication = PaletteTheme.indication,
)
