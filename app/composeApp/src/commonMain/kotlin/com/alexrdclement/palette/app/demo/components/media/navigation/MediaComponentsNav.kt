package com.alexrdclement.palette.app.demo.components.media.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.media.MediaComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.mediaComponentsNavGraph() = navGraph(
    route = MediaComponentsGraph,
    start = MediaComponentCatalogRoute,
) {
    route(MediaComponentCatalogRoute)
    wildcardRoute<MediaComponentRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else MediaComponentRoute(pathSegment)
    }
}

@Composable
fun MediaComponentsNav(
    route: MediaComponentsRoute,
    navController: NavController,
) {
    when (route) {
        MediaComponentsGraph,
        MediaComponentCatalogRoute,
        -> CatalogScreen(
            items = MediaComponent.entries.toList(),
            onItemClick = { component ->
                navController.navigate(MediaComponentRoute(component))
            },
            title = "Media",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is MediaComponentRoute -> MediaComponentScreen(
            component = route.component,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
