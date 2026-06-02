package com.alexrdclement.palette.theme.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.layout.FloatingAction as ComponentFloatingAction

@Composable
fun FloatingAction(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    ComponentFloatingAction(
        modifier = modifier,
        spacing = PaletteTheme.spacing.small,
        content = content,
    )
}
