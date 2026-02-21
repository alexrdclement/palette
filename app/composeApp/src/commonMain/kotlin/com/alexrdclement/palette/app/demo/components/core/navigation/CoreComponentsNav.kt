package com.alexrdclement.palette.app.demo.components.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.core.CoreComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.coreComponentsNavGraph() = navGraph(
    root = CoreComponentsGraph,
    start = CoreComponentCatalogRoute,
) {
    route(CoreComponentCatalogRoute)
    wildcardRoute<CoreComponentRoute> { pathSegment ->
        CoreComponentRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.coreComponentsEntryProvider(
    navController: NavController,
) {
    entry<CoreComponentsGraph> {
        CoreComponentsCatalog(navController)
    }

    entry<CoreComponentCatalogRoute> {
        CoreComponentsCatalog(navController)
    }

    entry<CoreComponentRoute> {
        CoreComponentScreen(
            component = it.component,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}

@Composable
private fun CoreComponentsCatalog(navController: NavController) {
    CatalogScreen(
        items = CoreComponent.entries.toList(),
        onItemClick = { component ->
            navController.navigate(CoreComponentRoute(component))
        },
        title = "Core",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )
}
