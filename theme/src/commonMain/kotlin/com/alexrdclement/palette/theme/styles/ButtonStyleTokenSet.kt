package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.SpacingToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.components.core.ButtonStyle as ComponentButtonStyle

data class ButtonStyleTokenSet(
    val containerColor: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
    val contentPadding: PaddingValuesTokenSet = PaddingValuesTokenSet(
        start = SpacingToken.Large,
        top = SpacingToken.Small,
        end = SpacingToken.Large,
        bottom = SpacingToken.Small,
    ),
)

@Composable
fun ButtonStyleTokenSet.toComponentStyle(): ComponentButtonStyle = ComponentButtonStyle(
    containerColor = containerColor.toColor(),
    shape = shape.toShape(),
    borderStyle = borderStyle?.resolve(),
    disabledContentAlpha = PaletteTheme.colorScheme.disabledContentAlpha,
    disabledContainerAlpha = PaletteTheme.colorScheme.disabledContainerAlpha,
    contentPadding = contentPadding.toPaddingValues(),
    indication = PaletteTheme.indication,
)
