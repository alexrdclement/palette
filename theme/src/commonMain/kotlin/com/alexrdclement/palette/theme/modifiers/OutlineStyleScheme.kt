package com.alexrdclement.palette.theme.modifiers

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken

enum class OutlineStyleToken {
    Primary,
    Secondary,
}

fun OutlineStyleToken.toStyle(outlineStyles: OutlineStyleScheme): OutlineStyle {
    return when (this) {
        OutlineStyleToken.Primary -> outlineStyles.primary
        OutlineStyleToken.Secondary -> outlineStyles.secondary
    }
}

data class OutlineStyleScheme(
    val primary: OutlineStyle,
    val secondary: OutlineStyle,
)

val PaletteOutlineStyleScheme = OutlineStyleScheme(
    primary = OutlineStyle(
        width = 1.dp,
        color = ColorToken.Outline,
        shape = ShapeToken.Primary,
    ),
    secondary = OutlineStyle(
        width = Dp.Hairline,
        color = ColorToken.Outline,
        shape = ShapeToken.Secondary,
    ),
)
