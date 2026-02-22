package com.alexrdclement.palette.app.demo.modifiers.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.demo.modifiers.DemoModifier
import com.alexrdclement.palette.app.demo.modifiers.ModifierScreen
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey

fun NavGraphBuilder.modifiersNavGraph() = navGraph(
    root = ModifiersGraph,
    start = ModifierCatalogRoute,
) {
    route(ModifierCatalogRoute)
    wildcardRoute<ModifierRoute> { pathSegment ->
        ModifierRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.modifiersEntryProvider(
    navController: NavController,
) {
    catalogEntry<ModifierCatalogRoute, DemoModifier>(
        onItemClick = { modifier ->
            navController.navigate(ModifierRoute(modifier))
        },
        title = "Modifiers",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )

    entry<ModifierRoute> {
        ModifierScreen(
            modifierType = it.modifier,
            onNavigateUp = navController::navigateUp,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
