package com.alexrdclement.palette.app.theme.format.datetime.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.format.datetime.DateFormatSchemeScreen
import com.alexrdclement.palette.app.theme.format.datetime.DateTimeFormatSchemeScreen
import com.alexrdclement.palette.app.theme.format.datetime.InstantFormatSchemeScreen
import com.alexrdclement.palette.app.theme.format.datetime.TimeFormatSchemeScreen
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.dateTimeFormatNavGraph() = navGraph(
    root = DateTimeFormatGraph,
    start = DateTimeFormatCatalogRoute,
) {
    route(DateTimeFormatCatalogRoute)
    wildcardRoute<DateTimeFormatItemRoute> { pathSegment ->
        DateTimeFormatItemRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.dateTimeFormatEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    entry<DateTimeFormatCatalogRoute> {
        DateTimeFormatCatalog(navController)
    }

    entry<DateTimeFormatItemRoute> { route ->
        val format = DateTimeFormatCatalogItem.entries[route.ordinal]
        when (format) {
            DateTimeFormatCatalogItem.Date -> DateFormatSchemeScreen(
                themeController = themeController,
                onNavigateUp = navController::goBack,
            )
            DateTimeFormatCatalogItem.DateTime -> DateTimeFormatSchemeScreen(
                themeController = themeController,
                onNavigateUp = navController::goBack,
            )
            DateTimeFormatCatalogItem.Instant -> InstantFormatSchemeScreen(
                themeController = themeController,
                onNavigateUp = navController::goBack,
            )
            DateTimeFormatCatalogItem.Time -> TimeFormatSchemeScreen(
                themeController = themeController,
                onNavigateUp = navController::goBack,
            )
        }
    }
}

@Composable
private fun DateTimeFormatCatalog(navController: NavController) {
    CatalogScreen(
        items = DateTimeFormatCatalogItem.entries.toList(),
        onItemClick = { item ->
            navController.navigate(DateTimeFormatItemRoute(item.ordinal))
        },
        title = "DateTime",
        onNavigateUp = navController::goBack,
    )
}
