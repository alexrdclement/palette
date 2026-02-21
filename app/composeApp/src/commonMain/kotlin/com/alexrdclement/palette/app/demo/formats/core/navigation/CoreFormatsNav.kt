package com.alexrdclement.palette.app.demo.formats.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.core.CoreFormatScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.coreFormatsNavGraph() = navGraph(
    root = CoreFormatsGraph,
    start = CoreFormatCatalogRoute,
) {
    route(CoreFormatCatalogRoute)
    wildcardRoute<CoreFormatRoute> { pathSegment ->
        CoreFormatRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.coreFormatsEntryProvider(
    navController: NavController,
) {
    entry<CoreFormatsGraph> {
        CoreFormatsCatalog(navController)
    }

    entry<CoreFormatCatalogRoute> {
        CoreFormatsCatalog(navController)
    }

    entry<CoreFormatRoute> {
        CoreFormatScreen(
            format = it.format,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}

@Composable
private fun CoreFormatsCatalog(navController: NavController) {
    CatalogScreen(
        items = CoreFormat.entries.toList(),
        onItemClick = { format ->
            navController.navigate(CoreFormatRoute(format))
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
