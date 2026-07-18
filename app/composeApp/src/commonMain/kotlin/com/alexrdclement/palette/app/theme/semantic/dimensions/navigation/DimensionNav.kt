package com.alexrdclement.palette.app.theme.semantic.dimensions.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.semantic.dimensions.PaddingScreen
import com.alexrdclement.palette.app.theme.semantic.dimensions.SpacingScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.dimensionNavGraph() = navGraph(
    root = DimensionsGraph,
    start = DimensionsCatalogRoute,
) {
    route(DimensionsCatalogRoute)
    route(SpacingRoute)
    route(PaddingRoute)
}

fun EntryProviderScope<NavKey>.dimensionEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<DimensionsCatalogRoute, Dimension>(
        onItemClick = { dimension ->
            when (dimension) {
                Dimension.Spacing -> navController.navigate(SpacingRoute)
                Dimension.Padding -> navController.navigate(PaddingRoute)
            }
        },
        title = "Dimensions",
        onNavigateUp = navController::goBack,
    )

    entry<SpacingRoute> {
        SpacingScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<PaddingRoute> {
        PaddingScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}
