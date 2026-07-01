package com.alexrdclement.palette.theme.components.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.components.layout.TopBarStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.layout.BoxWithLabel as BaseBoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold as BaseScaffold
import com.alexrdclement.palette.components.layout.TopBar as BaseTopBar

/** [BaseScaffold] pre-bound to the palette theme (`style` defaults to `styles.layout.scaffold`). */
@Composable
fun Scaffold(
    topBar: @Composable () -> Unit = {},
    floatingAction: @Composable () -> Unit = {},
    navigationBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    style: ScaffoldStyle = PaletteTheme.styles.layout.scaffold,
    content: @Composable (PaddingValues) -> Unit,
) = BaseScaffold(
    topBar = topBar,
    floatingAction = floatingAction,
    navigationBar = navigationBar,
    modifier = modifier,
    style = style,
    content = content,
)

/** [BaseBoxWithLabel] pre-bound to the palette theme (`style` defaults to `styles.layout.boxWithLabel`). */
@Composable
fun BoxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    style: BoxWithLabelStyle = PaletteTheme.styles.layout.boxWithLabel,
    content: @Composable () -> Unit,
) = BaseBoxWithLabel(
    label = label,
    modifier = modifier,
    style = style,
    content = content,
)

/** [BaseTopBar] pre-bound to the palette theme (`style` defaults to `styles.layout.topBar`). */
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    style: TopBarStyle = PaletteTheme.styles.layout.topBar,
    navButton: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
) = BaseTopBar(
    modifier = modifier,
    style = style,
    navButton = navButton,
    actions = actions,
    title = title,
)
