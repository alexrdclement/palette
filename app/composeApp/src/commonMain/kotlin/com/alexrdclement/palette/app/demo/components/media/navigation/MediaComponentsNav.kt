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
    root = MediaComponentsGraph,
    start = MediaComponentCatalogRoute,
) {
    route(MediaComponentCatalogRoute)
    wildcardRoute<MediaComponentRoute> { pathSegment ->
        MediaComponentRoute(pathSegment)
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
            onNavigateUp = navController::navigateUp,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is MediaComponentRoute -> MediaComponentScreen(
            component = route.component,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
