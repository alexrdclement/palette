package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.BorderStyle as ComponentBorderStyle
import com.alexrdclement.palette.components.core.ButtonStyle as ComponentButtonStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleTokenSet
import com.alexrdclement.palette.theme.styles.tokenSet
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape

/**
 * Resolves a theme [BorderStyle] (token-based) into the unopinionated component
 * [ComponentBorderStyle], reading the current [PaletteTheme] for colors and shapes.
 */
@Composable
fun BorderStyle.toComponentStyle(): ComponentBorderStyle = ComponentBorderStyle(
    width = this.width,
    color = this.color.toColor(),
    shape = this.shape.toShape(),
)

/**
 * Resolves a theme [BorderStyleToken] into the unopinionated component [ComponentBorderStyle],
 * reading the current [PaletteTheme] for colors and shapes.
 */
@Composable
fun BorderStyleToken.toComponentStyle(): ComponentBorderStyle = this.tokenSet().toComponentStyle()

/**
 * Resolves a theme [ButtonStyleTokenSet] into the unopinionated component [ComponentButtonStyle],
 * reading the current [PaletteTheme] for colors, shapes, borders, and disabled alphas.
 */
@Composable
fun ButtonStyleTokenSet.toComponentStyle(): ComponentButtonStyle = ComponentButtonStyle(
    contentColor = contentColor.toColor(),
    containerColor = containerColor.toColor(),
    shape = shape.toShape(),
    borderStyle = borderStyle?.toComponentStyle(),
    disabledContentAlpha = PaletteTheme.colorScheme.disabledContentAlpha,
    disabledContainerAlpha = PaletteTheme.colorScheme.disabledContainerAlpha,
    contentPadding = PaddingValues(
        horizontal = PaletteTheme.spacing.large,
        vertical = PaletteTheme.spacing.medium,
    ),
    indication = PaletteTheme.indication,
)
