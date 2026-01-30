package com.alexrdclement.palette.app.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.demo.components.navigation.ComponentsNav
import com.alexrdclement.palette.app.demo.components.navigation.componentsNavGraph
import com.alexrdclement.palette.app.demo.formats.navigation.FormatsNav
import com.alexrdclement.palette.app.demo.formats.navigation.formatsNavGraph
import com.alexrdclement.palette.app.demo.modifiers.navigation.ModifiersNav
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifiersNavGraph
import com.alexrdclement.palette.app.main.navigation.MainGraph
import com.alexrdclement.palette.app.main.navigation.MainNav
import com.alexrdclement.palette.app.main.navigation.mainNavGraph
import com.alexrdclement.palette.app.theme.navigation.ThemeNav
import com.alexrdclement.palette.app.theme.navigation.themeNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.NavState
import com.alexrdclement.palette.navigation.NavigationEventHandler
import com.alexrdclement.palette.navigation.navGraph
import com.alexrdclement.palette.navigation.rememberNavController
import com.alexrdclement.palette.navigation.rememberNavState
import com.alexrdclement.palette.navigation.toPathSegment
import com.alexrdclement.palette.theme.control.ThemeController

data object PaletteGraph : NavKey {
    override val pathSegment = "".toPathSegment()
}

val PaletteNavGraph = navGraph(
    root = PaletteGraph,
    start = MainGraph,
) {
    mainNavGraph()
    componentsNavGraph()
    formatsNavGraph()
    modifiersNavGraph()
    themeNavGraph()
}

@Composable
fun PaletteNav(
    themeController: ThemeController,
    navState: NavState = rememberPaletteNavState(),
    navController: NavController = rememberNavController(
        state = navState,
    ),
) {
    val currentRoute = navState.currentRoute ?: return

    NavigationEventHandler(
        navState = navState,
        navController = navController,
    )

    MainNav(
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

@Composable
fun rememberPaletteNavController(
    initialDeeplink: String? = null,
    onBackStackEmpty: () -> Unit = {},
) = rememberNavController(
    state = rememberPaletteNavState(
        deeplink = initialDeeplink,
        onBackStackEmpty = onBackStackEmpty,
    ),
)

@Composable
fun rememberPaletteNavState(
    deeplink: String? = null,
    onBackStackEmpty: () -> Unit = {},
) = rememberNavState(
    navGraph = PaletteNavGraph,
    deeplink = deeplink,
    onWouldBecomeEmpty = onBackStackEmpty,
)
