package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.BorderStyle as ComponentBorderStyle
import com.alexrdclement.palette.components.core.ButtonStyle as ComponentButtonStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.toStyle
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.toStyle
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
fun BorderStyleToken.toComponentStyle(): ComponentBorderStyle = this.toStyle().toComponentStyle()

/**
 * Resolves a theme [ButtonStyleToken] into the unopinionated component [ComponentButtonStyle],
 * reading the current [PaletteTheme] for colors, shapes, borders, and disabled alphas.
 */
@Composable
fun ButtonStyleToken.toComponentStyle(): ComponentButtonStyle {
    val style = this.toStyle()
    return ComponentButtonStyle(
        contentColor = style.contentColor.toColor(),
        containerColor = style.containerColor.toColor(),
        shape = style.shape.toShape(),
        borderStyle = style.borderStyle?.toComponentStyle(),
        disabledContentAlpha = PaletteTheme.colorScheme.disabledContentAlpha,
        disabledContainerAlpha = PaletteTheme.colorScheme.disabledContainerAlpha,
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = PaletteTheme.spacing.large,
            vertical = PaletteTheme.spacing.medium,
        ),
        indication = PaletteTheme.indication,
    )
}
