package com.alexrdclement.palette.app.theme.interaction.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.interaction.IndicationScreen
import com.alexrdclement.palette.app.theme.interaction.navigation.Interaction
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.interactionNavGraph() = navGraph(
    route = InteractionGraph,
    start = InteractionCatalogRoute,
) {
    route(InteractionCatalogRoute)
    route(IndicationRoute)
}

@Composable
fun InteractionNav(
    route: NavKey,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        is InteractionRoute -> InteractionNav(
            route = route,
            navController = navController,
            themeController = themeController,
        )
    }
}

@Composable
private fun InteractionNav(
    route: InteractionRoute,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        InteractionGraph,
        InteractionCatalogRoute,
        -> CatalogScreen(
            items = Interaction.entries.toList(),
            onItemClick = { interaction ->
                when (interaction) {
                    Interaction.Indication -> navController.navigate(IndicationRoute)
                }
            },
            title = "Interaction",
            onNavigateBack = navController::goBack,
        )
        is IndicationRoute -> IndicationScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
    }
}
