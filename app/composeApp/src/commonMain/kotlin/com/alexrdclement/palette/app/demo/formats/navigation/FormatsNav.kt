package com.alexrdclement.palette.app.demo.formats.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.core.navigation.CoreFormatsGraph
import com.alexrdclement.palette.app.demo.formats.core.navigation.CoreFormatsNav
import com.alexrdclement.palette.app.demo.formats.core.navigation.CoreFormatsRoute
import com.alexrdclement.palette.app.demo.formats.core.navigation.coreFormatsNavGraph
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.DateTimeFormatsGraph
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.DateTimeFormatsNav
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.DateTimeFormatsRoute
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.dateTimeFormatsNavGraph
import com.alexrdclement.palette.app.demo.formats.money.navigation.MoneyFormatsGraph
import com.alexrdclement.palette.app.demo.formats.money.navigation.MoneyFormatsNav
import com.alexrdclement.palette.app.demo.formats.money.navigation.MoneyFormatsRoute
import com.alexrdclement.palette.app.demo.formats.money.navigation.moneyFormatsNavGraph
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey

fun NavGraphBuilder.formatsNavGraph() = navGraph(
    route = FormatsGraph,
    start = FormatCatalogRoute,
) {
    route(FormatCatalogRoute)
    coreFormatsNavGraph()
    dateTimeFormatsNavGraph()
    moneyFormatsNavGraph()
}

@Composable
fun FormatsNav(
    route: NavKey,
    navController: NavController,
) {
    when (route) {
        FormatsGraph,
        FormatCatalogRoute,
        -> CatalogScreen(
            items = FormatCategory.entries.toList(),
            onItemClick = { category ->
                val targetRoute = when (category) {
                    FormatCategory.Core -> CoreFormatsGraph
                    FormatCategory.DateTime -> DateTimeFormatsGraph
                    FormatCategory.Money -> MoneyFormatsGraph
                }
                navController.navigate(targetRoute)
            },
            title = "Formats",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is CoreFormatsRoute -> CoreFormatsNav(
            route = route,
            navController = navController,
        )
        is DateTimeFormatsRoute -> DateTimeFormatsNav(
            route = route,
            navController = navController,
        )
        is MoneyFormatsRoute -> MoneyFormatsNav(
            route = route,
            navController = navController,
        )
    }
}
