package com.alexrdclement.palette.app.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.main.navigation.MainCatalogNav
import com.alexrdclement.palette.app.main.navigation.MainCatalogRoute
import com.alexrdclement.palette.app.demo.components.navigation.ComponentsNav
import com.alexrdclement.palette.app.demo.formats.navigation.FormatsNav
import com.alexrdclement.palette.app.demo.modifiers.navigation.ModifiersNav
import com.alexrdclement.palette.app.main.navigation.mainCatalogNavGraph
import com.alexrdclement.palette.app.theme.navigation.ThemeNav
import com.alexrdclement.palette.app.theme.navigation.themeNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavState
import com.alexrdclement.palette.navigation.NavigationEventHandler
import com.alexrdclement.palette.navigation.navGraph
import com.alexrdclement.palette.navigation.rememberNavController
import com.alexrdclement.palette.navigation.rememberNavState
import com.alexrdclement.palette.theme.control.ThemeController

val PaletteNavGraph = navGraph {
    mainCatalogNavGraph()
    themeNavGraph()
}

@Composable
fun PaletteNav(
    themeController: ThemeController,
    navState: NavState = rememberNavState(
        startRoute = MainCatalogRoute,
        navGraph = PaletteNavGraph,
    ),
    navController: NavController = rememberNavController(navState)
) {
    val currentRoute = navState.backStack.lastOrNull() ?: return

    NavigationEventHandler(
        navState = navState,
        navController = navController,
    )

    MainCatalogNav(
        route = currentRoute,
        navController = navController,
    )
    ComponentsNav(
        route = currentRoute,
        navController = navController,
    )
    ModifiersNav(
        route = currentRoute,
        navController = navController,
    )
    FormatsNav(
        route = currentRoute,
        navController = navController,
    )
    ThemeNav(
        route = currentRoute,
        navController = navController,
        themeController = themeController,
    )
}
