package com.alexrdclement.palette.app.theme.component.core.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.component.core.BorderStyleScreen
import com.alexrdclement.palette.app.theme.component.core.ButtonStyleScreen
import com.alexrdclement.palette.app.theme.component.core.CoreStyleItem
import com.alexrdclement.palette.app.theme.component.core.SurfaceStyleScreen
import com.alexrdclement.palette.app.theme.component.core.TextStyleScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.coreNavGraph() = navGraph(
    root = CoreGraph,
    start = CoreCatalogRoute,
) {
    route(CoreCatalogRoute)
    route(ButtonStylesRoute)
    route(BorderStylesRoute)
    route(SurfaceStylesRoute)
    route(TextStylesRoute)
}

fun EntryProviderScope<NavKey>.coreEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<CoreCatalogRoute, CoreStyleItem>(
        onItemClick = { style ->
            when (style) {
                CoreStyleItem.Border -> navController.navigate(BorderStylesRoute)
                CoreStyleItem.Button -> navController.navigate(ButtonStylesRoute)
                CoreStyleItem.Surface -> navController.navigate(SurfaceStylesRoute)
                CoreStyleItem.Text -> navController.navigate(TextStylesRoute)
            }
        },
        title = "Core",
        onNavigateUp = navController::goBack,
    )

    entry<BorderStylesRoute> {
        BorderStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<ButtonStylesRoute> {
        ButtonStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<SurfaceStylesRoute> {
        SurfaceStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<TextStylesRoute> {
        TextStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}
