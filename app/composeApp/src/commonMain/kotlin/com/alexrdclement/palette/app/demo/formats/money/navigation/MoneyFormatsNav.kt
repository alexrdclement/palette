package com.alexrdclement.palette.app.demo.formats.money.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
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

@Composable
fun MoneyFormatsNav(
    route: MoneyFormatsRoute,
    navController: NavController,
) {
    when (route) {
        MoneyFormatsGraph,
        MoneyFormatCatalogRoute,
        -> CatalogScreen(
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
        is MoneyFormatRoute -> MoneyFormatScreen(
            format = route.format,
            onNavigateUp = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
