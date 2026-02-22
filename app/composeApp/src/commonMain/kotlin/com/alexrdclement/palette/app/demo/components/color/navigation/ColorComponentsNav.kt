package com.alexrdclement.palette.app.demo.components.color.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.demo.components.color.ColorComponentScreen
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.colorComponentsNavGraph() = navGraph(
    root = ColorComponentsGraph,
    start = ColorComponentCatalogRoute,
) {
    route(ColorComponentCatalogRoute)
    wildcardRoute<ColorComponentRoute> { pathSegment ->
        ColorComponentRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.colorComponentsEntryProvider(
    navController: NavController,
) {
    catalogEntry<ColorComponentCatalogRoute, ColorComponent>(
        onItemClick = { component ->
            navController.navigate(ColorComponentRoute(component))
        },
        title = "Color",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )

    entry<ColorComponentRoute> {
        ColorComponentScreen(
            component = it.component,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
