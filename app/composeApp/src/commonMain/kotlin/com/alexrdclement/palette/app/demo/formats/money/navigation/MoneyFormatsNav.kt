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
    route = MoneyFormatsGraph,
    start = MoneyFormatCatalogRoute,
) {
    wildcardRoute<MoneyFormatRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else MoneyFormatRoute(pathSegment)
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
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is MoneyFormatRoute -> MoneyFormatScreen(
            format = route.format,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
