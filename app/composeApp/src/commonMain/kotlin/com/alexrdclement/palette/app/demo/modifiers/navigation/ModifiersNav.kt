package com.alexrdclement.palette.app.demo.modifiers.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.modifiers.DemoModifier
import com.alexrdclement.palette.app.demo.modifiers.ModifierScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.modifiersNavGraph() = navGraph(
    route = ModifiersGraph,
    start = ModifierCatalogRoute,
) {
    wildcardRoute<ModifierRoute> { pathSegment ->
        if (pathSegment == PathSegment.Wildcard) null
        else ModifierRoute(pathSegment)
    }
}

@Composable
fun ModifiersNav(
    route: NavKey,
    navController: NavController,
) {
    when (route) {
        ModifiersGraph,
        ModifierCatalogRoute,
        -> CatalogScreen(
            items = DemoModifier.entries.toList(),
            onItemClick = { modifier ->
                navController.navigate(ModifierRoute(modifier))
            },
            title = "Modifiers",
            onNavigateBack = navController::goBack,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is ModifierRoute -> ModifierScreen(
            modifierType = route.modifier,
            onNavigateBack = navController::goBack,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
