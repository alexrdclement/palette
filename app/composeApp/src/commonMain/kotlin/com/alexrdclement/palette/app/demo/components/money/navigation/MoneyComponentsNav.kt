package com.alexrdclement.palette.app.demo.components.money.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.money.MoneyComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.moneyComponentsNavGraph() = navGraph(
    route = MoneyComponentsGraph,
    start = MoneyComponentCatalogRoute,
) {
    route(MoneyComponentCatalogRoute)
    wildcardRoute<MoneyComponentRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else MoneyComponentRoute(pathSegment)
    }
}

@Composable
fun MoneyComponentsNav(
    route: MoneyComponentsRoute,
    navController: NavController,
) {
    when (route) {
        MoneyComponentsGraph,
        MoneyComponentCatalogRoute,
        -> CatalogScreen(
            items = MoneyComponent.entries.toList(),
            onItemClick = { component ->
                navController.navigate(MoneyComponentRoute(component))
            },
            title = "Money",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is MoneyComponentRoute -> MoneyComponentScreen(
            component = route.component,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
