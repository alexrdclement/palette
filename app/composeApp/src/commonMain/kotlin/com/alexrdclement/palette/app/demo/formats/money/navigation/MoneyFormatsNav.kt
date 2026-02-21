package com.alexrdclement.palette.app.demo.formats.money.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.moneyFormatsNavGraph() = navGraph(
    root = MoneyFormatsGraph,
    start = MoneyFormatCatalogRoute,
) {
    route(MoneyFormatCatalogRoute)
    wildcardRoute<MoneyFormatRoute> { pathSegment ->
        MoneyFormatRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.moneyFormatsEntryProvider(
    navController: NavController,
) {
    entry<MoneyFormatsGraph> {
        MoneyFormatsCatalog(navController)
    }

    entry<MoneyFormatCatalogRoute> {
        MoneyFormatsCatalog(navController)
    }

    entry<MoneyFormatRoute> {
        MoneyFormatScreen(
            format = it.format,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}

@Composable
private fun MoneyFormatsCatalog(navController: NavController) {
    CatalogScreen(
        items = MoneyFormat.entries.toList(),
        onItemClick = { format ->
            navController.navigate(MoneyFormatRoute(format))
        },
        title = "Money",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )
}
