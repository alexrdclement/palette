package com.alexrdclement.palette.app.demo.formats.datetime.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.datetime.DateTimeFormatScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.dateTimeFormatsNavGraph() = navGraph(
    route = DateTimeFormatsGraph,
    start = DateTimeFormatCatalogRoute,
) {
    wildcardRoute<DateTimeFormatRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else DateTimeFormatRoute(pathSegment)
    }
}

@Composable
fun DateTimeFormatsNav(
    route: NavKey,
    navController: NavController,
) {
    when (route) {
        DateTimeFormatsGraph,
        DateTimeFormatCatalogRoute,
        -> CatalogScreen(
            items = DateTimeFormat.entries.toList(),
            onItemClick = { format ->
                navController.navigate(DateTimeFormatRoute(format))
            },
            title = "DateTime",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is DateTimeFormatRoute -> DateTimeFormatScreen(
            format = route.format,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
