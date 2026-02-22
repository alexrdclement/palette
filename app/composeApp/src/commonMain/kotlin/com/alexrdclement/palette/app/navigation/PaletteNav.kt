package com.alexrdclement.palette.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.alexrdclement.palette.app.demo.components.navigation.componentsEntryProvider
import com.alexrdclement.palette.app.demo.components.navigation.componentsNavGraph
import com.alexrdclement.palette.app.demo.formats.navigation.formatsEntryProvider
import com.alexrdclement.palette.app.demo.formats.navigation.formatsNavGraph
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifiersEntryProvider
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifiersNavGraph
import com.alexrdclement.palette.app.main.navigation.MainGraph
import com.alexrdclement.palette.app.main.navigation.mainEntryProvider
import com.alexrdclement.palette.app.main.navigation.mainNavGraph
import com.alexrdclement.palette.app.theme.navigation.themeEntryProvider
import com.alexrdclement.palette.app.theme.navigation.themeNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.navGraph
import com.alexrdclement.palette.navigation.rememberNavController
import com.alexrdclement.palette.navigation.rememberNavState
import com.alexrdclement.palette.navigation.toPathSegment
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ui-playground")
data object PaletteGraph : NavGraphRoute {
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
    navController: NavController = rememberPaletteNavController(),
) {
    NavDisplay(
        backStack = navController.state.backStack,
        entryProvider = entryProvider {
            paletteEntryProvider(
                navController = navController,
                themeController = themeController,
            )
        },
        onBack = navController::goBack,
    )
}

@Composable
fun rememberPaletteNavController(
    initialDeeplink: String? = null,
    buildSyntheticBackStack: Boolean = true,
    onBackStackEmpty: () -> Unit = {},
) = rememberNavController(
    state = rememberPaletteNavState(
        initialDeeplink = initialDeeplink,
        buildSyntheticBackStack = buildSyntheticBackStack,
        onBackStackEmpty = onBackStackEmpty,
    ),
)

@Composable
fun rememberPaletteNavState(
    initialDeeplink: String? = null,
    buildSyntheticBackStack: Boolean = true,
    onBackStackEmpty: () -> Unit = {},
) = rememberNavState(
    navGraph = PaletteNavGraph,
    initialDeeplink = initialDeeplink,
    buildSyntheticBackStack = buildSyntheticBackStack,
    onWouldBecomeEmpty = onBackStackEmpty,
)

fun EntryProviderScope<NavKey>.paletteEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    mainEntryProvider(navController)
    componentsEntryProvider(navController)
    formatsEntryProvider(navController)
    modifiersEntryProvider(navController)
    themeEntryProvider(navController, themeController)
}
