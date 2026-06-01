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

@Composable
fun BoxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    labelPadding: Dp = 4.dp,
    labelStyle: TextStyle = TextStyle(),
    borderColor: Color = Color.Unspecified,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = labelStyle,
            modifier = Modifier
                .border(1.dp, borderColor)
                .padding(labelPadding)
                .align(Alignment.Start)
        )
        content()
    }
}
