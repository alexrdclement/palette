package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import com.github.terrakok.navigation3.browser.ChronologicalBrowserNavigation

@Composable
actual fun NavigationEventHandler(
    navState: NavState,
    navController: NavController,
) {
    ChronologicalBrowserNavigation(
        backStack = navState.backStack,
        saveKey = { key ->
            "#${key.toDeeplink(tree = navState.navGraph)}"
        },
        restoreKey = { fragment ->
            NavKey.fromDeeplink(
                deeplink = fragment.removePrefix("#"),
                navGraph = navState.navGraph,
            )
        }
    )
}
