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
        // Auto-resolve graph routes to their start destinations
        val resolvedRoute = resolveGraphRoot(route, state.navGraph)
        state.navigate(
            route = resolvedRoute,
            replace = replace,
        )
    }

    private fun resolveGraphRoot(route: NavKey, navGraph: NavGraph): NavKey {
        val flattenedNodes = flattenNodes(navGraph.nodes)
        val node = flattenedNodes.firstOrNull { it.navKeyClass == route::class }
            ?: return route

        // If this node has a graph root child, return it
        val graphRootChild = node.children.firstOrNull { it.isGraphRoot }
        if (graphRootChild != null) {
            return graphRootChild.parser(PathSegment.Empty) ?: route
        }

        return route
    }

    private fun flattenNodes(nodes: List<NavGraphNode>): List<NavGraphNode> = buildList {
        fun addNodes(nodes: List<NavGraphNode>) {
            nodes.forEach { node ->
                add(node)
                addNodes(node.children)
            }
        }
        addNodes(nodes)
    }

    fun goBack() {
        state.goBack()
    }
}
