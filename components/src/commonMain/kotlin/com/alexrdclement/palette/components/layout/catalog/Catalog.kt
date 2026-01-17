package com.alexrdclement.palette.components.layout.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

@Composable
fun <T : CatalogItem> Catalog(
    items: List<T>,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(
            space = PaletteTheme.spacing.medium,
            alignment = Alignment.CenterVertically,
        ),
        contentPadding = contentPadding,
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            items = items,
            key = { it.title }
        ) {
            Button(
                style = ButtonStyleToken.Secondary,
                onClick = { onItemClick(it) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(it.title)
            }
        }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}


private enum class MainCatalogItem : CatalogItem {
    Components,
    Modifiers,
    ;

    override val title = this.name
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        Catalog(
            items = MainCatalogItem.entries.toList(),
            onItemClick = {}
        )
    }
}
