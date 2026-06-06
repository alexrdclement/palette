package com.alexrdclement.palette.theme.styles

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.theme.PaletteTheme

enum class ButtonStyleToken {
    Primary,
    Secondary,
    Tertiary,
}

fun ButtonStyleToken.toStyle(buttonStyles: ButtonStyleScheme): ButtonStyleTokenSet {
    return when (this) {
        ButtonStyleToken.Primary -> buttonStyles.primary
        ButtonStyleToken.Secondary -> buttonStyles.secondary
        ButtonStyleToken.Tertiary -> buttonStyles.tertiary
    }
}

@Composable
fun ButtonStyleToken.toStyle(): ButtonStyleTokenSet {
    return toStyle(PaletteTheme.styles.button)
}
