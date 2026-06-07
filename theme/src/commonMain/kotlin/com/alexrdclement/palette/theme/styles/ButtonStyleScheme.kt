package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.components.toComponentStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

data class ButtonStyleScheme(
    val primary: ButtonStyleTokenSet,
    val secondary: ButtonStyleTokenSet,
    val tertiary: ButtonStyleTokenSet,
)

fun ButtonStyleScheme.copy(
    token: ButtonStyleToken,
    value: ButtonStyleTokenSet,
) = this.copy(
    primary = if (token == ButtonStyleToken.Primary) value else this.primary,
    secondary = if (token == ButtonStyleToken.Secondary) value else this.secondary,
    tertiary = if (token == ButtonStyleToken.Tertiary) value else this.tertiary,
)

/** Resolved per-variant [ButtonStyle]s, mirroring [ResolvedTextStyleScheme]. */
data class ResolvedButtonStyleScheme(
    val primary: ButtonStyle,
    val secondary: ButtonStyle,
    val tertiary: ButtonStyle,
) {
    operator fun get(token: ButtonStyleToken): ButtonStyle = when (token) {
        ButtonStyleToken.Primary -> primary
        ButtonStyleToken.Secondary -> secondary
        ButtonStyleToken.Tertiary -> tertiary
    }
}

@Composable
fun ButtonStyleScheme.resolve(): ResolvedButtonStyleScheme = ResolvedButtonStyleScheme(
    primary = ButtonStyleToken.Primary.toComponentStyle(),
    secondary = ButtonStyleToken.Secondary.toComponentStyle(),
    tertiary = ButtonStyleToken.Tertiary.toComponentStyle(),
)

val PaletteButtonStyleScheme = ButtonStyleScheme(
    primary = ButtonStyleTokenSet(
        token = ButtonStyleToken.Primary,
        contentColor = ColorToken.OnPrimary,
        containerColor = ColorToken.Primary,
        shape = ShapeToken.Primary,
        borderStyle = BorderStyleToken.Primary,
    ),
    secondary = ButtonStyleTokenSet(
        token = ButtonStyleToken.Secondary,
        contentColor = ColorToken.Secondary,
        containerColor = ColorToken.Surface,
        shape = ShapeToken.Secondary,
        borderStyle = BorderStyleToken.Secondary,
    ),
    tertiary = ButtonStyleTokenSet(
        token = ButtonStyleToken.Tertiary,
        contentColor = ColorToken.Primary,
        containerColor = ColorToken.OnPrimary,
        shape = ShapeToken.Tertiary,
        borderStyle = BorderStyleToken.Tertiary,
    ),
)
