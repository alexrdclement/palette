package com.alexrdclement.palette.app.demo.components.auth.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.auth.AuthComponentScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
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

@Composable
fun AuthComponentsNav(
    route: AuthComponentsRoute,
    navController: NavController,
) {
    when (route) {
        AuthComponentsGraph,
        AuthComponentCatalogRoute,
        -> CatalogScreen(
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
        is AuthComponentRoute -> AuthComponentScreen(
            component = route.component,
            onNavigateUp = navController::navigateUp,
            onThemeClick = {
                navController.navigate(ThemeGraph)
            },
        )
    }
}
