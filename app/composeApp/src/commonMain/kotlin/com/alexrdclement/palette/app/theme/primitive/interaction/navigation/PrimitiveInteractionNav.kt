package com.alexrdclement.palette.app.theme.primitive.interaction.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.primitive.interaction.IndicationScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.primitiveInteractionNavGraph() = navGraph(
    root = PrimitiveInteractionGraph,
    start = PrimitiveInteractionCatalogRoute,
) {
    route(PrimitiveInteractionCatalogRoute)
    route(PrimitiveIndicationRoute)
}

fun EntryProviderScope<NavKey>.primitiveInteractionEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<PrimitiveInteractionCatalogRoute, PrimitiveInteraction>(
        onItemClick = { interaction ->
            when (interaction) {
                PrimitiveInteraction.Indication -> navController.navigate(PrimitiveIndicationRoute)
            }
        },
        title = "Interaction",
        onNavigateUp = navController::goBack,
    )

    entry<PrimitiveIndicationRoute> {
        IndicationScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}
