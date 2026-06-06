package com.alexrdclement.palette.theme.styles

import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

data class ButtonStyleTokenSet(
    val token: ButtonStyleToken,
    val contentColor: ColorToken,
    val containerColor: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
)
