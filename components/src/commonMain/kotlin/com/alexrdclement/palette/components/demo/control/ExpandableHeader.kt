package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
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
import com.alexrdclement.palette.components.core.IconStyle
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle

data class ExpandableHeaderStyle(
    val headerStyle: TextStyle = TextStyle(),
    val borderColor: Color = Color.Unspecified,
    val spacing: Dp = 8.dp,
    val borderWidth: Dp = 1.dp,
    val labelPadding: PaddingValues = PaddingValues(4.dp),
    val chevronIconStyle: IconStyle = IconStyle(),
    val indication: Indication? = null,
)

@Composable
fun ExpandableHeader(
    name: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    style: ExpandableHeaderStyle = ExpandableHeaderStyle(),
) {
    Surface(
        onClick = { onExpandedChange(!expanded) },
        style = SurfaceStyle(indication = style.indication),
        modifier = modifier,
    ) {
        // The label drives the row height (IntrinsicSize.Min), and the chevron fills it, so the
        // glyph tracks the header text instead of a fixed size — its intrinsic IconSize.Fill.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(style.spacing),
            modifier = Modifier
                .height(IntrinsicSize.Min)
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
            )
        }
    }
}
