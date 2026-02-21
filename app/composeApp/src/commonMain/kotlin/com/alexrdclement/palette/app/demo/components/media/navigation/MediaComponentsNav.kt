package com.alexrdclement.palette.app.demo.components.media.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.media.MediaComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder

fun NavGraphBuilder.mediaComponentsNavGraph() = navGraph(
    root = MediaComponentsGraph,
    start = MediaComponentCatalogRoute,
) {
    route(MediaComponentCatalogRoute)
    wildcardRoute<MediaComponentRoute> { pathSegment ->
        MediaComponentRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.mediaComponentsEntryProvider(
    navController: NavController,
) {
    entry<MediaComponentsGraph> {
        MediaComponentsCatalog(navController)
    }

    entry<MediaComponentCatalogRoute> {
        MediaComponentsCatalog(navController)
    }

    entry<MediaComponentRoute> {
        MediaComponentScreen(
            component = it.component,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}

@Composable
private fun MediaComponentsCatalog(navController: NavController) {
    CatalogScreen(
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
}
