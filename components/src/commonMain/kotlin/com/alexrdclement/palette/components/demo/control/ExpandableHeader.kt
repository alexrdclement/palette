package com.alexrdclement.palette.components.demo.control

import com.alexrdclement.palette.components.demo.LocalDemoStyle
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
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ChevronDirection
import com.alexrdclement.palette.components.core.ChevronIcon
import com.alexrdclement.palette.components.core.Text

@Composable
fun ExpandableHeader(
    name: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clickable { onExpandedChange(!expanded) }
    ) {
        Text(
            text = name,
            style = LocalDemoStyle.current.headerStyle,
            modifier = Modifier
                .border(1.dp, LocalDemoStyle.current.borderColor)
                .padding(4.dp)
        )
        ChevronIcon(
            direction = if (expanded) ChevronDirection.Up else ChevronDirection.Down,
            modifier = Modifier
                .fillMaxHeight()
                .padding(6.dp)
        )
    }
}
