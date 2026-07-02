package com.alexrdclement.palette.theme.styles

import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

data class SurfaceStyleTokenSet(
    val color: ColorToken,
    val shape: ShapeToken,
    /** Border applied to the surface; `null` for no border. Referenced by [BorderStyleToken], not raw values. */
    val borderStyle: BorderStyleToken?,
)
