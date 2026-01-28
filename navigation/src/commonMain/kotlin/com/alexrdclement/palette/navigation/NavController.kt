package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberNavController(state: NavState): NavController {
    return remember(state) { NavController(state) }
}

class NavController(private val state: NavState) {
    fun navigate(
        route: NavKey,
        replace: Boolean = false,
    ) {
        val resolvedRoute = resolveGraphRoot(route, state.navGraph)
        state.navigate(
            route = resolvedRoute,
            replace = replace,
        )
    }

    private fun resolveGraphRoot(route: NavKey, navGraph: NavGraph): NavKey {
        val node = navGraph.findNode(route::class) ?: return route
        return node.graphStartRoute ?: route
    }

    fun goBack() {
        state.goBack()
    }
}
