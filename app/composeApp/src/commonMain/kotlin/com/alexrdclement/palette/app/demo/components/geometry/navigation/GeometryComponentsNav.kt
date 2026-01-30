package com.alexrdclement.palette.app.demo.components.geometry.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.geometry.GeometryComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.geometryComponentsNavGraph() = navGraph(
    root = GeometryComponentsGraph,
    start = GeometryComponentCatalogRoute,
) {
    route(GeometryComponentCatalogRoute)
    wildcardRoute<GeometryComponentRoute> { pathSegment ->
        GeometryComponentRoute(pathSegment)
    }
}

@Composable
fun GeometryComponentsNav(
    route: GeometryComponentsRoute,
    navController: NavController,
) {
    when (route) {
        GeometryComponentsGraph,
        GeometryComponentCatalogRoute,
        -> CatalogScreen(
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
        is GeometryComponentRoute -> GeometryComponentScreen(
            component = route.component,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
