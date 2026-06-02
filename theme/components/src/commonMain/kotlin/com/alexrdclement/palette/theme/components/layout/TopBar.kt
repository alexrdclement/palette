package com.alexrdclement.palette.theme.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.layout.TopBar as ComponentTopBar

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navButton: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
) {
    ComponentTopBar(
        modifier = modifier,
        spacing = PaletteTheme.spacing.small,
        navButton = navButton,
        actions = actions,
        title = title,
    )
}
