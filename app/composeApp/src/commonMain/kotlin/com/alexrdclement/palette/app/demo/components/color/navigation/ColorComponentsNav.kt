package com.alexrdclement.palette.app.demo.components.color.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.color.ColorComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.colorComponentsNavGraph() = navGraph(
    route = ColorComponentsGraph,
    start = ColorComponentCatalogRoute,
) {
    wildcardRoute<ColorComponentRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else ColorComponentRoute(pathSegment)
    }
}

@Composable
fun ColorComponentsNav(
    route: ColorComponentsRoute,
    navController: NavController,
) {
    when (route) {
        ColorComponentsGraph,
        ColorComponentCatalogRoute,
        -> CatalogScreen(
            items = ColorComponent.entries.toList(),
            onItemClick = { component ->
                navController.navigate(ColorComponentRoute(component))
            },
            title = "Color",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is ColorComponentRoute -> ColorComponentScreen(
            component = route.component,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
