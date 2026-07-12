package com.alexrdclement.palette.app.theme.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.ThemeItem
import com.alexrdclement.palette.app.theme.component.navigation.ComponentGraph
import com.alexrdclement.palette.app.theme.component.navigation.componentEntryProvider
import com.alexrdclement.palette.app.theme.component.navigation.componentNavGraph
import com.alexrdclement.palette.app.theme.primitive.navigation.PrimitiveGraph
import com.alexrdclement.palette.app.theme.primitive.navigation.primitiveEntryProvider
import com.alexrdclement.palette.app.theme.primitive.navigation.primitiveNavGraph
import com.alexrdclement.palette.app.theme.semantic.navigation.SemanticGraph
import com.alexrdclement.palette.app.theme.semantic.navigation.semanticEntryProvider
import com.alexrdclement.palette.app.theme.semantic.navigation.semanticNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.themeNavGraph() = navGraph(
    root = ThemeGraph,
    start = ThemeCatalogRoute,
) {
    route(ThemeCatalogRoute)
    primitiveNavGraph()
    semanticNavGraph()
    componentNavGraph()
}

fun EntryProviderScope<NavKey>.themeEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<ThemeCatalogRoute, ThemeItem>(
        onItemClick = { item ->
            when (item) {
                ThemeItem.Primitive -> navController.navigate(PrimitiveGraph)
                ThemeItem.Semantic -> navController.navigate(SemanticGraph)
                ThemeItem.Component -> navController.navigate(ComponentGraph)
            }
        },
        title = "Theme",
        onNavigateUp = navController::goBack,
    )

    primitiveEntryProvider(navController, themeController)
    semanticEntryProvider(navController, themeController)
    componentEntryProvider(navController, themeController)
}
