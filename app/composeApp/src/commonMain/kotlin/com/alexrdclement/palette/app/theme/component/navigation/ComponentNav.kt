package com.alexrdclement.palette.app.theme.component.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.component.ComponentItem
import com.alexrdclement.palette.app.theme.component.core.navigation.CoreGraph
import com.alexrdclement.palette.app.theme.component.core.navigation.coreEntryProvider
import com.alexrdclement.palette.app.theme.component.core.navigation.coreNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.componentNavGraph() = navGraph(
    root = ComponentGraph,
    start = ComponentCatalogRoute,
) {
    route(ComponentCatalogRoute)
    coreNavGraph()
}

fun EntryProviderScope<NavKey>.componentEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<ComponentCatalogRoute, ComponentItem>(
        onItemClick = { item ->
            when (item) {
                ComponentItem.Core -> navController.navigate(CoreGraph)
            }
        },
        title = "Component",
        onNavigateUp = navController::goBack,
    )
    coreEntryProvider(navController, themeController)
}
