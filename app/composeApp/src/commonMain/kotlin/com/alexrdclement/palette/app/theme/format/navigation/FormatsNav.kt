package com.alexrdclement.palette.app.theme.format.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.format.MoneyFormatScreen
import com.alexrdclement.palette.app.theme.format.NumberFormatScreen
import com.alexrdclement.palette.app.theme.format.TextFormatScreen
import com.alexrdclement.palette.app.theme.format.datetime.navigation.DateTimeFormatGraph
import com.alexrdclement.palette.app.theme.format.datetime.navigation.DateTimeFormatNav
import com.alexrdclement.palette.app.theme.format.datetime.navigation.DateTimeFormatRoute
import com.alexrdclement.palette.app.theme.format.datetime.navigation.dateTimeFormatNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.formatNavGraph() = navGraph(
    route = FormatsGraph,
    start = FormatCatalogRoute,
) {
    route(NumberFormatRoute)
    route(MoneyFormatRoute)
    route(TextFormatRoute)

    dateTimeFormatNavGraph()
}

@Composable
fun FormatsNav(
    route: NavKey,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        is FormatRoute -> FormatsNav(
            route = route,
            navController = navController,
            themeController = themeController,
        )
        is DateTimeFormatRoute -> DateTimeFormatNav(
            route = route,
            navController = navController,
            themeController = themeController,
        )
    }
}

@Composable
private fun FormatsNav(
    route: FormatRoute,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        FormatsGraph,
        FormatCatalogRoute,
        -> CatalogScreen(
            items = Format.entries.toList(),
            onItemClick = { format ->
                when (format) {
                    Format.DateTime -> navController.navigate(DateTimeFormatGraph)
                    Format.Money -> navController.navigate(MoneyFormatRoute)
                    Format.Number -> navController.navigate(NumberFormatRoute)
                    Format.Text -> navController.navigate(TextFormatRoute)
                }
            },
            title = "Format",
            onNavigateBack = navController::goBack,
        )

        is MoneyFormatRoute -> MoneyFormatScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is NumberFormatRoute -> NumberFormatScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is TextFormatRoute -> TextFormatScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
    }
}
