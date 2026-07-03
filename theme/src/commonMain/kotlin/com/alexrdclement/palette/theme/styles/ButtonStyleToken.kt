package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

enum class ButtonStyleToken(val default: ButtonStyleTokenSet) {
    Primary(
        ButtonStyleTokenSet(
            contentColor = ColorToken.OnPrimary,
            containerColor = ColorToken.Primary,
            shape = ShapeToken.Primary,
            borderStyle = BorderStyleToken.Primary,
        ),
    ),
    Secondary(
        ButtonStyleTokenSet(
            contentColor = ColorToken.Secondary,
            containerColor = ColorToken.Surface,
            shape = ShapeToken.Secondary,
            borderStyle = BorderStyleToken.Secondary,
        ),
    ),
    Tertiary(
        ButtonStyleTokenSet(
            contentColor = ColorToken.Primary,
            containerColor = ColorToken.OnPrimary,
            shape = ShapeToken.Tertiary,
            borderStyle = BorderStyleToken.Tertiary,
        ),
    ),
}

@Composable
fun ButtonStyleToken.tokenSet(): ButtonStyleTokenSet =
    LocalStyles.current.button.getValue(this)

@Composable
fun ButtonStyleToken.resolve(): ButtonStyle = tokenSet().toComponentStyle()
