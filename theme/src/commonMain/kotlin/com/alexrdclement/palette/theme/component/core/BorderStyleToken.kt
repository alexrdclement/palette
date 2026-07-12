package com.alexrdclement.palette.theme.component.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.semantic.ColorToken
import com.alexrdclement.palette.theme.semantic.ShapeToken
import com.alexrdclement.palette.components.core.BorderStyle as ComponentBorderStyle

enum class BorderStyleToken(val default: BorderStyleTokenSet) {
    Surface(
        BorderStyleTokenSet(
            width = 1.dp,
            color = ColorToken.Outline,
            shape = ShapeToken.Surface
        )
    ),
    Primary(
        BorderStyleTokenSet(
            width = 1.dp,
            color = ColorToken.Outline,
            shape = ShapeToken.Primary
        )
    ),
    Secondary(
        BorderStyleTokenSet(
            width = 1.dp,
            color = ColorToken.Outline,
            shape = ShapeToken.Secondary
        )
    ),
    Tertiary(
        BorderStyleTokenSet(
            width = Dp.Hairline,
            color = ColorToken.Outline,
            shape = ShapeToken.Tertiary
        )
    ),
}

@Composable
fun BorderStyleToken.tokenSet(): BorderStyleTokenSet = LocalStyles.current.border.getValue(this)

@Composable
fun BorderStyleToken.resolve(): ComponentBorderStyle = tokenSet().toComponentStyle()
