package com.alexrdclement.palette.app.demo.components.auth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.auth.AuthComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.PathSegment

fun NavGraphBuilder.authComponentsNavGraph() = navGraph(
    root = AuthComponentsGraph,
    start = AuthComponentCatalogRoute,
) {
    route(AuthComponentCatalogRoute)
    wildcardRoute<AuthComponentRoute> { pathSegment ->
        AuthComponentRoute(pathSegment)
    }
}

fun EntryProviderScope<NavKey>.authComponentsEntryProvider(
    navController: NavController,
) {
    entry<AuthComponentsGraph> {
        AuthComponentsCatalog(navController)
    }

    entry<AuthComponentCatalogRoute> {
        AuthComponentsCatalog(navController)
    }

    entry<AuthComponentRoute> {
        AuthComponentScreen(
            component = it.component,
            onNavigateUp = navController::navigateUp,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}

@Composable
private fun AuthComponentsCatalog(navController: NavController) {
    CatalogScreen(
        items = AuthComponent.entries.toList(),
        onItemClick = { component ->
            navController.navigate(AuthComponentRoute(component))
        },
        title = "Auth",
        onNavigateUp = navController::navigateUp,
        actions = {
            ThemeButton(
                onClick = { navController.navigate(ThemeGraph) },
            )
        },
    )
}
