package com.alexrdclement.palette.app.demo.components.core.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.core.CoreComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.coreComponentsNavGraph() = navGraph(
    route = CoreComponentsGraph,
    start = CoreComponentCatalogRoute,
) {
    route(CoreComponentCatalogRoute)
    wildcardRoute<CoreComponentRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else CoreComponentRoute(pathSegment)
    }
}

@Composable
fun CoreComponentsNav(
    route: CoreComponentsRoute,
    navController: NavController,
) {
    when (route) {
        CoreComponentsGraph,
        CoreComponentCatalogRoute,
        -> CatalogScreen(
            items = CoreComponent.entries.toList(),
            onItemClick = { component ->
                navController.navigate(CoreComponentRoute(component))
            },
            title = "Core",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is CoreComponentRoute -> CoreComponentScreen(
            component = route.component,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
