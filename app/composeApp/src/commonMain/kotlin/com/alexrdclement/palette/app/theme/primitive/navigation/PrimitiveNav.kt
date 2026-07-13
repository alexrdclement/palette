package com.alexrdclement.palette.app.theme.primitive.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.primitive.PrimitiveItem
import com.alexrdclement.palette.app.theme.primitive.shape.ShapeScreen
import com.alexrdclement.palette.app.theme.primitive.typography.TypographyScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.primitiveNavGraph() = navGraph(
    root = PrimitiveGraph,
    start = PrimitiveCatalogRoute,
) {
    route(PrimitiveCatalogRoute)
    route(PrimitiveTypographyRoute)
    route(PrimitiveShapeRoute)
}

fun EntryProviderScope<NavKey>.primitiveEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<PrimitiveCatalogRoute, PrimitiveItem>(
        onItemClick = { item ->
            when (item) {
                PrimitiveItem.Typography -> navController.navigate(PrimitiveTypographyRoute)
                PrimitiveItem.Shape -> navController.navigate(PrimitiveShapeRoute)
            }
        },
        title = "Primitive",
        onNavigateUp = navController::goBack,
    )

    entry<PrimitiveTypographyRoute> {
        TypographyScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<PrimitiveShapeRoute> {
        ShapeScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}
