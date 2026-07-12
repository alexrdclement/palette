package com.alexrdclement.palette.theme.component.core

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.semantic.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.ShapeToken
import com.alexrdclement.palette.theme.semantic.toColor
import com.alexrdclement.palette.theme.semantic.toShape
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
    borderStyle = borderStyle?.resolve(),
    indication = PaletteTheme.semantic.indication,
)
