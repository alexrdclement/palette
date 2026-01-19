package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken

enum class ButtonStyleToken {
    Primary,
    Secondary,
    Tertiary,
}

data class ButtonStyle(
    val token: ButtonStyleToken,
    val contentColor: ColorToken,
    val containerColor: ColorToken,
    val shape: ShapeToken,
    val borderStyle: BorderStyleToken?,
)

fun ButtonStyleToken.toStyle(buttonStyles: ButtonStyleScheme): ButtonStyle {
    return when (this) {
        ButtonStyleToken.Primary -> buttonStyles.primary
        ButtonStyleToken.Secondary -> buttonStyles.secondary
        ButtonStyleToken.Tertiary -> buttonStyles.tertiary
    }
}

@Composable
fun ButtonStyleToken.toStyle(): ButtonStyle {
    return toStyle(PaletteTheme.styles.button)
}
