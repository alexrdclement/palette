package com.alexrdclement.palette.components.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun BoxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.small),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = PaletteTheme.styles.text.labelSmall,
            modifier = Modifier
                .border(1.dp, PaletteTheme.colorScheme.outline)
                .padding(PaletteTheme.spacing.xs)
                .align(Alignment.Start)
        )
        content()
    }
}
