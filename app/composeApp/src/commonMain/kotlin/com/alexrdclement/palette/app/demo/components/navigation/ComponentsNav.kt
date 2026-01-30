package com.alexrdclement.palette.app.demo.components.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.Component
import com.alexrdclement.palette.app.demo.components.auth.navigation.AuthComponentsGraph
import com.alexrdclement.palette.app.demo.components.auth.navigation.AuthComponentsNav
import com.alexrdclement.palette.app.demo.components.auth.navigation.AuthComponentsRoute
import com.alexrdclement.palette.app.demo.components.auth.navigation.authComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.color.navigation.ColorComponentsGraph
import com.alexrdclement.palette.app.demo.components.color.navigation.ColorComponentsNav
import com.alexrdclement.palette.app.demo.components.color.navigation.ColorComponentsRoute
import com.alexrdclement.palette.app.demo.components.color.navigation.colorComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.core.navigation.CoreComponentsGraph
import com.alexrdclement.palette.app.demo.components.core.navigation.CoreComponentsNav
import com.alexrdclement.palette.app.demo.components.core.navigation.CoreComponentsRoute
import com.alexrdclement.palette.app.demo.components.core.navigation.coreComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.geometry.navigation.GeometryComponentsGraph
import com.alexrdclement.palette.app.demo.components.geometry.navigation.GeometryComponentsNav
import com.alexrdclement.palette.app.demo.components.geometry.navigation.GeometryComponentsRoute
import com.alexrdclement.palette.app.demo.components.geometry.navigation.geometryComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.media.navigation.MediaComponentsGraph
import com.alexrdclement.palette.app.demo.components.media.navigation.MediaComponentsNav
import com.alexrdclement.palette.app.demo.components.media.navigation.MediaComponentsRoute
import com.alexrdclement.palette.app.demo.components.media.navigation.mediaComponentsNavGraph
import com.alexrdclement.palette.app.demo.components.money.navigation.MoneyComponentsGraph
import com.alexrdclement.palette.app.demo.components.money.navigation.MoneyComponentsNav
import com.alexrdclement.palette.app.demo.components.money.navigation.MoneyComponentsRoute
import com.alexrdclement.palette.app.demo.components.money.navigation.moneyComponentsNavGraph
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey

fun NavGraphBuilder.componentsNavGraph() = navGraph(
    root = ComponentsGraph,
    start = ComponentCatalogRoute,
) {
    route(ComponentCatalogRoute)
    authComponentsNavGraph()
    colorComponentsNavGraph()
    coreComponentsNavGraph()
    geometryComponentsNavGraph()
    mediaComponentsNavGraph()
    moneyComponentsNavGraph()
}

@Composable
fun ComponentsNav(
    route: NavKey,
    navController: NavController,
) {
    when (route) {
        ComponentsGraph,
        ComponentCatalogRoute,
        -> CatalogScreen(
            items = Component.entries.toList(),
            onItemClick = { component ->
                val targetRoute = when (component) {
                    Component.Auth -> AuthComponentsGraph
                    Component.Color -> ColorComponentsGraph
                    Component.Core -> CoreComponentsGraph
                    Component.Geometry -> GeometryComponentsGraph
                    Component.Media -> MediaComponentsGraph
                    Component.Money -> MoneyComponentsGraph
                }
                navController.navigate(targetRoute)
            },
            title = "Components",
            onNavigateUp = navController::navigateUp,
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
        is AuthComponentsRoute -> AuthComponentsNav(
            route = route,
            navController = navController,
        )
        is ColorComponentsRoute -> ColorComponentsNav(
            route = route,
            navController = navController,
        )
        is CoreComponentsRoute -> CoreComponentsNav(
            route = route,
            navController = navController,
        )
        is GeometryComponentsRoute -> GeometryComponentsNav(
            route = route,
            navController = navController,
        )
        is MediaComponentsRoute -> MediaComponentsNav(
            route = route,
            navController = navController,
        )
        is MoneyComponentsRoute -> MoneyComponentsNav(
            route = route,
            navController = navController,
        )
    }
}
