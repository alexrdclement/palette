package com.alexrdclement.palette.app.demo.components.geometry.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.demo.components.geometry.GeometryComponentScreen
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder

fun NavGraphBuilder.geometryComponentsNavGraph() = navGraph(
    root = GeometryComponentsGraph,
    start = GeometryComponentCatalogRoute,
) {
    route(GeometryComponentCatalogRoute)
    wildcardRoute<GeometryComponentRoute> { pathSegment ->
        GeometryComponentRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.geometryComponentsEntryProvider(
    navController: NavController,
) {
    catalogEntry<GeometryComponentCatalogRoute, GeometryComponent>(
        onItemClick = { component ->
            navController.navigate(GeometryComponentRoute(component))
        },
        title = "Geometry",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )

    entry<GeometryComponentRoute> {
        GeometryComponentScreen(
            component = it.component,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
