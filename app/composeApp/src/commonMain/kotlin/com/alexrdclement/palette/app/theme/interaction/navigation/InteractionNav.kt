package com.alexrdclement.palette.app.theme.interaction.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.interaction.IndicationScreen
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.interactionNavGraph() = navGraph(
    root = InteractionGraph,
    start = InteractionCatalogRoute,
) {
    route(InteractionCatalogRoute)
    route(IndicationRoute)
}

fun EntryProviderScope<NavKey>.interactionEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    entry<InteractionGraph> {
        InteractionCatalog(navController)
    }

    entry<InteractionCatalogRoute> {
        InteractionCatalog(navController)
    }

    entry<IndicationRoute> {
        IndicationScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}

@Composable
private fun InteractionCatalog(navController: NavController) {
    CatalogScreen(
        items = Interaction.entries.toList(),
        onItemClick = { interaction ->
            when (interaction) {
                Interaction.Indication -> navController.navigate(IndicationRoute)
            }
        },
        title = "Interaction",
        onNavigateUp = navController::goBack,
    )
}
