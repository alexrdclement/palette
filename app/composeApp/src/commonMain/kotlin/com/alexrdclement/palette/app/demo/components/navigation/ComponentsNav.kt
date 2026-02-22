package com.alexrdclement.palette.app.demo.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.Component
import com.alexrdclement.palette.app.demo.components.auth.navigation.AuthComponentsGraph
import com.alexrdclement.palette.app.demo.components.auth.navigation.authComponentsEntryProvider
import com.alexrdclement.palette.app.demo.components.auth.navigation.authComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.color.navigation.ColorComponentsGraph
import com.alexrdclement.palette.app.demo.components.color.navigation.colorComponentsEntryProvider
import com.alexrdclement.palette.app.demo.components.color.navigation.colorComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.core.navigation.CoreComponentsGraph
import com.alexrdclement.palette.app.demo.components.core.navigation.coreComponentsEntryProvider
import com.alexrdclement.palette.app.demo.components.core.navigation.coreComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.geometry.navigation.GeometryComponentsGraph
import com.alexrdclement.palette.app.demo.components.geometry.navigation.geometryComponentsEntryProvider
import com.alexrdclement.palette.app.demo.components.geometry.navigation.geometryComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.media.navigation.MediaComponentsGraph
import com.alexrdclement.palette.app.demo.components.media.navigation.mediaComponentsEntryProvider
import com.alexrdclement.palette.app.demo.components.media.navigation.mediaComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.money.navigation.MoneyComponentsGraph
import com.alexrdclement.palette.app.demo.components.money.navigation.moneyComponentsEntryProvider
import com.alexrdclement.palette.app.demo.components.money.navigation.moneyComponentsNavGraph
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey

fun NavGraphBuilder.componentsNavGraph() = navGraph(
    root = ComponentsGraph,
    start = ComponentCatalogRoute,
) {
    route(ComponentCatalogRoute)
    authComponentsNavGraph()
    colorComponentsNavGraph()
    coreComponentsNavGraph()
    geometryComponentsNavGraph()
    mediaComponentsNavGraph()
    moneyComponentsNavGraph()
}

fun EntryProviderScope<NavKey>.componentsEntryProvider(
    navController: NavController,
) {
    entry<ComponentCatalogRoute> {
        ComponentsCatalog(navController)
    }

    authComponentsEntryProvider(navController)
    colorComponentsEntryProvider(navController)
    coreComponentsEntryProvider(navController)
    geometryComponentsEntryProvider(navController)
    mediaComponentsEntryProvider(navController)
    moneyComponentsEntryProvider(navController)
}

@Composable
private fun ComponentsCatalog(navController: NavController) {
    CatalogScreen(
        items = Component.entries.toList(),
        onItemClick = { component ->
            val targetRoute = when (component) {
                Component.Auth -> AuthComponentsGraph
                Component.Color -> ColorComponentsGraph
                Component.Core -> CoreComponentsGraph
                Component.Geometry -> GeometryComponentsGraph
                Component.Media -> MediaComponentsGraph
                Component.Money -> MoneyComponentsGraph
            }
            navController.navigate(targetRoute)
        },
        title = "Components",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )
}
