package com.alexrdclement.palette.app.catalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.catalog.MainCatalogItem
import com.alexrdclement.palette.app.configuration.ConfigureButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("catalog")
object CatalogRoute

fun NavGraphBuilder.mainCatalogScreen(
    onItemClick: (MainCatalogItem) -> Unit,
    onConfigureClick: () -> Unit,
) {
    composable<CatalogRoute> {
        CatalogScreen(
            items = MainCatalogItem.entries.toList(),
            onItemClick = onItemClick,
            actions = {
                ConfigureButton(onClick = onConfigureClick)
            },
        )
    }
}
