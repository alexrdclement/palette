package com.alexrdclement.palette.theme.modifiers

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.styles.LocalStyles
import com.alexrdclement.palette.theme.styles.tokenSet
import com.alexrdclement.palette.components.core.BorderStyle as ComponentBorderStyle

enum class BorderStyleToken(val default: BorderStyle) {
    Surface(BorderStyle(width = 1.dp, color = ColorToken.Outline, shape = ShapeToken.Surface)),
    Primary(BorderStyle(width = 1.dp, color = ColorToken.Outline, shape = ShapeToken.Primary)),
    Secondary(BorderStyle(width = 1.dp, color = ColorToken.Outline, shape = ShapeToken.Secondary)),
    Tertiary(BorderStyle(width = Dp.Hairline, color = ColorToken.Outline, shape = ShapeToken.Tertiary)),
}

@Composable
fun BorderStyleToken.tokenSet(): BorderStyle = LocalStyles.current.border.getValue(this)

@Composable
fun BorderStyleToken.toComponentStyle(): ComponentBorderStyle = this.tokenSet().toComponentStyle()
