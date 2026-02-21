package com.alexrdclement.palette.app.theme.format.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.format.MoneyFormatScreen
import com.alexrdclement.palette.app.theme.format.NumberFormatScreen
import com.alexrdclement.palette.app.theme.format.TextFormatScreen
import com.alexrdclement.palette.app.theme.format.datetime.navigation.DateTimeFormatGraph
import com.alexrdclement.palette.app.theme.format.datetime.navigation.dateTimeFormatEntryProvider
import com.alexrdclement.palette.app.theme.format.datetime.navigation.dateTimeFormatNavGraph
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.formatNavGraph() = navGraph(
    root = FormatsGraph,
    start = FormatCatalogRoute,
) {
    route(FormatCatalogRoute)
    route(NumberFormatRoute)
    route(MoneyFormatRoute)
    route(TextFormatRoute)

    dateTimeFormatNavGraph()
}

fun EntryProviderScope<NavKey>.formatsEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    entry<FormatsGraph> {
        FormatCatalog(navController)
    }

    entry<FormatCatalogRoute> {
        FormatCatalog(navController)
    }

    entry<MoneyFormatRoute> {
        MoneyFormatScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<NumberFormatRoute> {
        NumberFormatScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<TextFormatRoute> {
        TextFormatScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    dateTimeFormatEntryProvider(navController, themeController)
}

@Composable
private fun FormatCatalog(navController: NavController) {
    CatalogScreen(
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
        onNavigateUp = navController::goBack,
    )
}
