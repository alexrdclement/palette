package com.alexrdclement.palette.theme.styles

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.components.core.ButtonStyle as ComponentButtonStyle

data class ButtonStyleTokenSet(
    val contentColor: ColorToken,
    val containerColor: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
)

@Composable
fun ButtonStyleTokenSet.toComponentStyle(): ComponentButtonStyle = ComponentButtonStyle(
    containerColor = containerColor.toColor(),
    shape = shape.toShape(),
    borderStyle = borderStyle?.resolve(),
    disabledContentAlpha = PaletteTheme.colorScheme.disabledContentAlpha,
    disabledContainerAlpha = PaletteTheme.colorScheme.disabledContainerAlpha,
    contentPadding = PaddingValues(
        horizontal = PaletteTheme.spacing.large,
        vertical = PaletteTheme.spacing.medium,
    ),
    indication = PaletteTheme.indication,
)
