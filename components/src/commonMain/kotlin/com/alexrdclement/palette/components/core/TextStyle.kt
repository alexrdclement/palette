package com.alexrdclement.palette.components.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.alexrdclement.palette.formats.core.TextFormat
import androidx.compose.ui.text.TextStyle as ComposeTextStyle

data class TextStyle(
    val composeTextStyle: ComposeTextStyle = ComposeTextStyle(),
    val format: TextFormat = TextFormat(),
)

fun TextStyle.copy(
    color: Color = this.composeTextStyle.color,
    textAlign: TextAlign = this.composeTextStyle.textAlign,
) = copy(
    composeTextStyle = this.composeTextStyle.copy(
        color = color,
        textAlign = textAlign,
    ),
)
