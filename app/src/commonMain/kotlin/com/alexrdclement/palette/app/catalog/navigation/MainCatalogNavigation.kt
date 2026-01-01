package com.alexrdclement.palette.app.catalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.catalog.MainCatalogItem
import com.alexrdclement.palette.app.theme.ThemeButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("catalog")
object CatalogRoute

fun NavGraphBuilder.mainCatalogScreen(
    onItemClick: (MainCatalogItem) -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<CatalogRoute> {
        CatalogScreen(
            items = MainCatalogItem.entries.toList(),
            onItemClick = onItemClick,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
