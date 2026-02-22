package com.alexrdclement.palette.app.demo.formats.money.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatScreen
import com.alexrdclement.palette.app.navigation.catalogEntry
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
    catalogEntry<MoneyFormatCatalogRoute, MoneyFormat>(
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
