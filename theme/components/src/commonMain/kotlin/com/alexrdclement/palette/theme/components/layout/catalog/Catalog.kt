package com.alexrdclement.palette.theme.components.layout.catalog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.components.toComponentStyle
import com.alexrdclement.palette.components.layout.catalog.Catalog as ComponentCatalog

@Composable
fun <T : CatalogItem> Catalog(
    items: List<T>,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    ComponentCatalog(
        items = items,
        onItemClick = onItemClick,
        modifier = modifier,
        contentPadding = contentPadding,
        itemSpacing = PaletteTheme.spacing.medium,
        itemStyle = ButtonStyleToken.Secondary.toComponentStyle(),
    )
}
