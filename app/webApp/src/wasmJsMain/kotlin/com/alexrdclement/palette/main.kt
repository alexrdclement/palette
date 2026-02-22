package com.alexrdclement.palette

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.alexrdclement.palette.app.App
import com.alexrdclement.palette.app.navigation.rememberPaletteNavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.fromDeeplink
import com.alexrdclement.palette.navigation.toDeeplink
import com.github.terrakok.navigation3.browser.ChronologicalBrowserNavigation

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val navController = rememberPaletteNavController()

        ChronologicalBrowserNavigation(
            backStack = navController.state.backStack,
            saveKey = { key -> "#${key.toDeeplink(tree = navController.state.navGraph)}" },
            restoreKey = { fragment ->
                NavKey.fromDeeplink(
                    deeplink = fragment.removePrefix("#"),
                    navGraph = navController.state.navGraph,
                )
            },
        )

        App(
            navController = navController,
        )
    }
}
