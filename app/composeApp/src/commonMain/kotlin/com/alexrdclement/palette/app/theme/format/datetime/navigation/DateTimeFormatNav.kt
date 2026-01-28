package com.alexrdclement.palette.app.theme.format.datetime.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.format.datetime.DateFormatSchemeScreen
import com.alexrdclement.palette.app.theme.format.datetime.DateTimeFormatSchemeScreen
import com.alexrdclement.palette.app.theme.format.datetime.InstantFormatSchemeScreen
import com.alexrdclement.palette.app.theme.format.datetime.TimeFormatSchemeScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.dateTimeFormatNavGraph() = navGraph(
    route = DateTimeFormatGraph,
    start = DateTimeFormatCatalogRoute,
) {
    wildcardRoute<DateTimeFormatItemRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else DateTimeFormatItemRoute(pathSegment)
    }
}

@Composable
fun DateTimeFormatNav(
    route: DateTimeFormatRoute,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        DateTimeFormatGraph,
        is DateTimeFormatCatalogRoute,
        -> CatalogScreen(
            items = DateTimeFormatCatalogItem.entries.toList(),
            onItemClick = { item ->
                navController.navigate(DateTimeFormatItemRoute(item.ordinal))
            },
            title = "DateTime",
            onNavigateBack = navController::goBack,
        )
        is DateTimeFormatItemRoute -> {
            val format = DateTimeFormatCatalogItem.entries[route.ordinal]
            when (format) {
                DateTimeFormatCatalogItem.Date -> DateFormatSchemeScreen(
                    themeController = themeController,
                    onNavigateBack = navController::goBack,
                )
                DateTimeFormatCatalogItem.DateTime -> DateTimeFormatSchemeScreen(
                    themeController = themeController,
                    onNavigateBack = navController::goBack,
                )
                DateTimeFormatCatalogItem.Instant -> InstantFormatSchemeScreen(
                    themeController = themeController,
                    onNavigateBack = navController::goBack,
                )
                DateTimeFormatCatalogItem.Time -> TimeFormatSchemeScreen(
                    themeController = themeController,
                    onNavigateBack = navController::goBack,
                )
            }
        }
    }
}
