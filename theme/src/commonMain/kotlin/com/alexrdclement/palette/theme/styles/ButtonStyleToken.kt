package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken

enum class ButtonStyleToken(val default: ButtonStyleTokenSet) {
    Primary(
        ButtonStyleTokenSet(
            containerColor = ColorToken.Primary,
            shape = ShapeToken.Primary,
            borderStyle = BorderStyleToken.Primary,
        ),
    ),
    Secondary(
        ButtonStyleTokenSet(
            containerColor = ColorToken.Surface,
            shape = ShapeToken.Secondary,
            borderStyle = BorderStyleToken.Secondary,
        ),
    ),
    Tertiary(
        ButtonStyleTokenSet(
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
