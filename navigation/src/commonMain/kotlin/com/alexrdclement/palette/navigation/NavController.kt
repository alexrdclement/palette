package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberNavController(state: NavState): NavController {
    return remember(state) { NavController(state) }
}

class NavController(val state: NavState) {
    fun navigate(
        route: NavKey,
        replace: Boolean = false,
    ) {
        val resolvedRoute = state.navGraph.resolve(route)
        state.navigate(
            route = resolvedRoute,
            replace = replace,
        )
    }

    fun navigateToDeeplink(deeplink: String, replace: Boolean = false) {
        val route = NavKey.fromDeeplink(deeplink, state.navGraph) ?: return
        navigate(route, replace = replace)
    }

    fun goBack() {
        state.goBack()
    }

    fun navigateUp() {
        val currentRoute = state.currentRoute ?: return
        val navGraph = state.navGraph

        // Find the first ancestor that resolves to a different route
        var currentNode = navGraph.findNode(currentRoute::class)
        while (currentNode != null) {
            val parent = currentNode.parent
            if (parent == null) {
                goBack()
                return
            }

            val resolvedParent = navGraph.resolve(parent)

            // If the parent resolves to a different route, navigate there
            if (resolvedParent::class != currentRoute::class) {
                // If the resolved parent is already the previous route in the backstack,
                // just pop the current route to reveal it (for hierarchy-based navigation)
                if (resolvedParent::class == state.previousRoute?.let { it::class }) {
                    goBack()
                    return
                }

                // Otherwise, navigate to the parent with replace (for deeplink scenario)
                navigate(parent, replace = true)
                return
            }

            // Otherwise, keep going up the hierarchy
            currentNode = navGraph.findNode(parent::class)
        }

        // Route not found in graph or parent chain exhausted, fall back to goBack()
        goBack()
    }
}
