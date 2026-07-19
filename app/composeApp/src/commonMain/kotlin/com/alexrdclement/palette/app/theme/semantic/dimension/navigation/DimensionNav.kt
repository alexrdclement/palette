package com.alexrdclement.palette.app.theme.semantic.dimension.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.semantic.dimension.SpacingScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.dimensionNavGraph() = navGraph(
    root = DimensionGraph,
    start = DimensionCatalogRoute,
) {
    route(DimensionCatalogRoute)
    route(SpacingRoute)
}

fun EntryProviderScope<NavKey>.dimensionEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<DimensionCatalogRoute, Dimension>(
        onItemClick = { dimension ->
            when (dimension) {
                Dimension.Spacing -> navController.navigate(SpacingRoute)
            }
        },
        title = "Dimension",
        onNavigateUp = navController::goBack,
    )

    entry<SpacingRoute> {
        SpacingScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}
