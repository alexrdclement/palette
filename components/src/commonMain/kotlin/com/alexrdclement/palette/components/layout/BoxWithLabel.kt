package com.alexrdclement.palette.components.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle

data class BoxWithLabelStyle(
    val spacing: Dp = 8.dp,
    val labelPadding: Dp = 4.dp,
    val labelStyle: TextStyle = TextStyle(),
    val borderColor: Color = Color.Unspecified,
)

@Composable
fun BoxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    style: BoxWithLabelStyle = BoxWithLabelStyle(),
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(style.spacing),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = style.labelStyle,
            modifier = Modifier
                .border(1.dp, style.borderColor)
                .padding(style.labelPadding)
                .align(Alignment.Start)
        )
        content()
    }
}
