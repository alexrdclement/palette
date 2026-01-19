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
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ChevronDirection
import com.alexrdclement.palette.components.core.ChevronIcon
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun ExpandableHeader(
    name: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.small),
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clickable { onExpandedChange(!expanded) }
    ) {
        Text(
            text = name,
            style = PaletteTheme.styles.text.labelSmall,
            modifier = Modifier
                .border(1.dp, PaletteTheme.colorScheme.outline)
                .padding(PaletteTheme.spacing.xs)
        )
        ChevronIcon(
            direction = if (expanded) ChevronDirection.Up else ChevronDirection.Down,
            modifier = Modifier
                .fillMaxHeight()
                .padding(6.dp)
        )
    }
}
