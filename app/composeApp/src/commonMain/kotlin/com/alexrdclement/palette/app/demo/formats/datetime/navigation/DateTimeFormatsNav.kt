package com.alexrdclement.palette.app.demo.formats.datetime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.datetime.DateTimeFormatScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.dateTimeFormatsNavGraph() = navGraph(
    root = DateTimeFormatsGraph,
    start = DateTimeFormatCatalogRoute,
) {
    route(DateTimeFormatCatalogRoute)
    wildcardRoute<DateTimeFormatRoute> { pathSegment ->
        DateTimeFormatRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.dateTimeFormatsEntryProvider(
    navController: NavController,
) {
    entry<DateTimeFormatCatalogRoute> {
        DateTimeFormatsCatalog(navController)
    }

    entry<DateTimeFormatRoute> {
        DateTimeFormatScreen(
            format = it.format,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}

@Composable
private fun DateTimeFormatsCatalog(navController: NavController) {
    CatalogScreen(
        items = DateTimeFormat.entries.toList(),
        onItemClick = { format ->
            navController.navigate(DateTimeFormatRoute(format))
        },
        title = "DateTime",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )
}
