package com.alexrdclement.palette.theme.styles

import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

data class ButtonStyleScheme(
    val primary: ButtonStyle,
    val secondary: ButtonStyle,
    val tertiary: ButtonStyle,
)

fun ButtonStyleScheme.copy(
    token: ButtonStyleToken,
    value: ButtonStyle,
) = this.copy(
    primary = if (token == ButtonStyleToken.Primary) value else this.primary,
    secondary = if (token == ButtonStyleToken.Secondary) value else this.secondary,
    tertiary = if (token == ButtonStyleToken.Tertiary) value else this.tertiary,
)

val PaletteButtonStyleScheme = ButtonStyleScheme(
    primary = ButtonStyle(
        token = ButtonStyleToken.Primary,
        contentColor = ColorToken.OnPrimary,
        containerColor = ColorToken.Primary,
        shape = ShapeToken.Primary,
        borderStyle = BorderStyleToken.Primary,
    ),
    secondary = ButtonStyle(
        token = ButtonStyleToken.Secondary,
        contentColor = ColorToken.Secondary,
        containerColor = ColorToken.Surface,
        shape = ShapeToken.Secondary,
        borderStyle = BorderStyleToken.Secondary,
    ),
    tertiary = ButtonStyle(
        token = ButtonStyleToken.Tertiary,
        contentColor = ColorToken.Primary,
        containerColor = ColorToken.OnPrimary,
        shape = ShapeToken.Tertiary,
        borderStyle = BorderStyleToken.Tertiary,
    ),
)
