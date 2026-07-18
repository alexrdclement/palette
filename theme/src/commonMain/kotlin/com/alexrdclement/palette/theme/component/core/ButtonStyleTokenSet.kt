package com.alexrdclement.palette.theme.component.core

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.semantic.color.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.dimensions.PaddingValuesTokenSet
import com.alexrdclement.palette.theme.semantic.dimensions.PalettePaddingScheme
import com.alexrdclement.palette.theme.semantic.dimensions.toPaddingValues
import com.alexrdclement.palette.theme.semantic.shape.ShapeToken
import com.alexrdclement.palette.theme.semantic.color.toColor
import com.alexrdclement.palette.theme.semantic.shape.toShape
import com.alexrdclement.palette.components.core.ButtonStyle as ComponentButtonStyle

data class ButtonStyleTokenSet(
    val containerColor: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
    val contentPadding: PaddingValuesTokenSet = PalettePaddingScheme.default,
)

@Composable
fun ButtonStyleTokenSet.toComponentStyle(): ComponentButtonStyle = ComponentButtonStyle(
    containerColor = containerColor.toColor(),
    shape = shape.toShape(),
    borderStyle = borderStyle?.resolve(),
    disabledContentAlpha = PaletteTheme.semantic.color.disabledContentAlpha,
    disabledContainerAlpha = PaletteTheme.semantic.color.disabledContainerAlpha,
    contentPadding = contentPadding.toPaddingValues(),
    indication = PaletteTheme.semantic.indication,
)
