package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.demo.DemoListStyle
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.DemoStyle
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.demo.Demo as BaseDemo
import com.alexrdclement.palette.components.demo.DemoList as BaseDemoList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/** [BaseDemo] pre-bound to the palette theme (`style` defaults to `styles.demo.style`). */
@Composable
fun Demo(
    modifier: Modifier = Modifier,
    style: DemoStyle = PaletteTheme.styles.demo.style,
    controls: PersistentList<Control> = persistentListOf(),
    content: @Composable DemoScope.() -> Unit,
) = BaseDemo(
    modifier = modifier,
    style = style,
    controls = controls,
    content = content,
)

/** [BaseDemoList] pre-bound to the palette theme (`style` defaults to `styles.demo.list`). */
@Composable
fun <T> DemoList(
    items: List<T>,
    controls: PersistentList<Control>,
    modifier: Modifier = Modifier,
    style: DemoListStyle = PaletteTheme.styles.demo.list,
    key: ((item: T) -> Any)? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(
        space = 24.dp,
        alignment = Alignment.CenterVertically,
    ),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable DemoScope.(T) -> Unit,
) = BaseDemoList(
    items = items,
    controls = controls,
    modifier = modifier,
    style = style,
    key = key,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    content = content,
)
