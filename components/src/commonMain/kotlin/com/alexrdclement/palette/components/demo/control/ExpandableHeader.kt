package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ChevronDirection
import com.alexrdclement.palette.components.core.ChevronIcon
import com.alexrdclement.palette.components.core.ChevronIconStyle
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle

data class ExpandableHeaderStyle(
    val headerStyle: TextStyle = TextStyle(),
    val borderColor: Color = Color.Unspecified,
    val spacing: Dp = 8.dp,
    val borderWidth: Dp = 1.dp,
    val labelPadding: Dp = 4.dp,
    val iconPadding: Dp = 6.dp,
    val chevronIconStyle: ChevronIconStyle = ChevronIconStyle(),
)

@Composable
fun ExpandableHeader(
    name: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    style: ExpandableHeaderStyle = ExpandableHeaderStyle(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(style.spacing),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clickable { onExpandedChange(!expanded) }
    ) {
        Text(
            text = name,
            style = style.headerStyle,
            modifier = Modifier
                .border(style.borderWidth, style.borderColor)
                .padding(style.labelPadding)
        )
        ChevronIcon(
            direction = if (expanded) ChevronDirection.Up else ChevronDirection.Down,
            style = style.chevronIconStyle,
            modifier = Modifier
                .fillMaxHeight()
                .padding(style.iconPadding)
        )
    }
}
