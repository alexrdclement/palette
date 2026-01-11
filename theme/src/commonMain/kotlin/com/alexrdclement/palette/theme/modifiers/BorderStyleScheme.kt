package com.alexrdclement.palette.theme.modifiers

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken

enum class BorderStyleToken {
    Surface,
    Primary,
    Secondary,
    Tertiary,
}

data class BorderStyleScheme(
    val surface: BorderStyle,
    val primary: BorderStyle,
    val secondary: BorderStyle,
    val tertiary: BorderStyle,
)

fun BorderStyleToken.toStyle(borderStyles: BorderStyleScheme): BorderStyle {
    return when (this) {
        BorderStyleToken.Surface -> borderStyles.surface
        BorderStyleToken.Primary -> borderStyles.primary
        BorderStyleToken.Secondary -> borderStyles.secondary
        BorderStyleToken.Tertiary -> borderStyles.tertiary
    }
}

@Composable
fun BorderStyleToken.toStyle(): BorderStyle {
    return toStyle(PaletteTheme.styles.border)
}

val PaletteBorderStyleScheme = BorderStyleScheme(
    surface = BorderStyle(
        width = 1.dp,
        color = ColorToken.Outline,
        shape = ShapeToken.Surface,
    ),
    primary = BorderStyle(
        width = 1.dp,
        color = ColorToken.Outline,
        shape = ShapeToken.Primary,
    ),
    secondary = BorderStyle(
        width = 1.dp,
        color = ColorToken.Outline,
        shape = ShapeToken.Secondary,
    ),
    tertiary = BorderStyle(
        width = Dp.Hairline,
        color = ColorToken.Outline,
        shape = ShapeToken.Tertiary,
    )
)
