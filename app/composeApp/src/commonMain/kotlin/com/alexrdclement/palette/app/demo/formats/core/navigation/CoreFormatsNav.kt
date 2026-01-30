package com.alexrdclement.palette.app.demo.formats.core.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.core.CoreFormatScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
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

@Composable
fun CoreFormatsNav(
    route: CoreFormatsRoute,
    navController: NavController,
) {
    when (route) {
        CoreFormatsGraph,
        CoreFormatCatalogRoute,
        -> CatalogScreen(
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
        is CoreFormatRoute -> CoreFormatScreen(
            format = route.format,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
