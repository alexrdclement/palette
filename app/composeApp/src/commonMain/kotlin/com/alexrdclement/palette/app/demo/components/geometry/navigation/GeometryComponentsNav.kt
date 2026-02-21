package com.alexrdclement.palette.app.demo.components.geometry.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.geometry.GeometryComponentScreen
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
    entry<GeometryComponentsGraph> {
        GeometryComponentsCatalog(navController)
    }

    entry<GeometryComponentCatalogRoute> {
        GeometryComponentsCatalog(navController)
    }

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

@Composable
private fun GeometryComponentsCatalog(navController: NavController) {
    CatalogScreen(
        items = GeometryComponent.entries.toList(),
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
}
