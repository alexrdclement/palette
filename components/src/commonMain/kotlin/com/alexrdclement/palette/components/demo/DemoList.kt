package com.alexrdclement.palette.components.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.collections.immutable.PersistentList

@Composable
fun <T> DemoList(
    items: List<T>,
    controls: PersistentList<Control>,
    modifier: Modifier = Modifier,
    key: ((item: T) -> Any)? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(
        space = PaletteTheme.spacing.large,
        alignment = Alignment.CenterVertically,
    ),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable DemoScope.(T) -> Unit,
) {
    Demo(
        controls = controls,
        modifier = modifier,
    ) {
        LazyColumn(
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            contentPadding = PaddingValues(PaletteTheme.spacing.medium),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
        ) {
            items(
                items = items,
                key = key,
            ) { item ->
                content(item)
            }
        }
    }
}
