package com.alexrdclement.palette.app.demo.formats.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.core.navigation.CoreFormatsGraph
import com.alexrdclement.palette.app.demo.formats.core.navigation.coreFormatsEntryProvider
import com.alexrdclement.palette.app.demo.formats.core.navigation.coreFormatsNavGraph
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.DateTimeFormatsGraph
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.dateTimeFormatsEntryProvider
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.dateTimeFormatsNavGraph
import com.alexrdclement.palette.app.demo.formats.money.navigation.MoneyFormatsGraph
import com.alexrdclement.palette.app.demo.formats.money.navigation.moneyFormatsEntryProvider
import com.alexrdclement.palette.app.demo.formats.money.navigation.moneyFormatsNavGraph
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey

fun NavGraphBuilder.formatsNavGraph() = navGraph(
    root = FormatsGraph,
    start = FormatCatalogRoute,
) {
    route(FormatCatalogRoute)
    coreFormatsNavGraph()
    dateTimeFormatsNavGraph()
    moneyFormatsNavGraph()
}

fun EntryProviderScope<NavKey>.formatsEntryProvider(
    navController: NavController,
) {
    entry<FormatCatalogRoute> {
        FormatsCatalog(navController)
    }

    coreFormatsEntryProvider(navController)
    dateTimeFormatsEntryProvider(navController)
    moneyFormatsEntryProvider(navController)
}

@Composable
private fun FormatsCatalog(navController: NavController) {
    CatalogScreen(
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
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )
}
