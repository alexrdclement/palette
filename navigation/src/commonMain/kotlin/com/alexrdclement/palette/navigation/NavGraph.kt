package com.alexrdclement.palette.navigation

import androidx.compose.runtime.Stable
import kotlin.reflect.KClass

@Stable
data class NavGraph(
    val root: NavKey,
    val nodes: List<NavGraphNode>,
) {
    val startRoute: NavKey
        get() = resolve(root)
}

/**
 * Resolves a route to its start route if it's a graph, recursively.
 * If the route is not a graph or is already a leaf route, returns the route itself.
 */
fun NavGraph.resolve(route: NavKey): NavKey {
    fun resolve(currentRoute: NavKey): NavKey {
        val node = findNode(currentRoute::class) ?: return currentRoute
        val start = node.graphStartRoute ?: return currentRoute
        // Stop if the start route is the same as current route (prevents infinite loop)
        if (start::class == currentRoute::class) return currentRoute
        return resolve(start)
    }
    return resolve(route)
}

fun NavKey.toDeeplink(tree: NavGraph): String {
    val node = tree.findNode(this::class) ?: return pathSegment.value

    val parentPath = node.parent?.toDeeplink(tree)

    return if (!parentPath.isNullOrEmpty()) {
        "$parentPath/${pathSegment.value}"
    } else {
        pathSegment.value
    }
}

fun NavKey.Companion.fromDeeplink(
    deeplink: String,
    navGraph: NavGraph,
): NavKey? {
    return navGraph.parseDeeplink(deeplink)
}

fun NavGraph.parseDeeplink(deeplink: String): NavKey? {
    val segments = deeplink.lowercase().split("/").filter { it.isNotEmpty() }.map(::PathSegment)
    return segments.toNavKey(this)
}

fun NavGraph.parseDeeplinkToBackStack(deeplink: String): List<NavKey> {
    val dest = parseDeeplink(deeplink) ?: return listOf(startRoute)
    return buildBackStack(dest)
}

private fun NavGraph.buildBackStack(dest: NavKey): List<NavKey> {
    val node = findNode(dest::class) ?: return listOf(dest)
    val parent = node.parent ?: return listOf(dest)
    val resolvedParent = resolve(parent)
    if (resolvedParent::class == dest::class) return listOf(dest)
    return listOf(resolvedParent, dest)
}

internal fun List<PathSegment>.toNavKey(
    navGraph: NavGraph,
): NavKey? {
    fun matchRoute(
        nodes: List<NavGraphNode>,
        segmentIndex: Int,
    ): NavKey? {
        if (segmentIndex >= this.size) return null

        val segment = this[segmentIndex]

        for (node in nodes) {
            // Skip nodes with empty path segments - search their children directly
            if (node.pathSegment.value.isEmpty() && node.children.isNotEmpty()) {
                val childMatch = matchRoute(node.children, segmentIndex)
                if (childMatch != null) return childMatch
                continue
            }

            val isMatch = node.pathSegment == PathSegment.Wildcard || node.pathSegment == segment
            if (!isMatch) continue

            val parsed = node.parser(segment) ?: continue

            val isLastSegment = segmentIndex == this@toNavKey.size - 1
            if (isLastSegment || node.children.isEmpty()) {
                return navGraph.resolve(parsed)
            }

            val childMatch = matchRoute(node.children, segmentIndex + 1)
            if (childMatch != null) return childMatch
        }

        return null
    }

    return matchRoute(
        nodes = navGraph.nodes,
        segmentIndex = 0,
    )
}

internal fun NavGraph.findNode(navKeyClass: KClass<out NavKey>): NavGraphNode? {
    return this.nodes.findNode(navKeyClass)
}

internal fun List<NavGraphNode>.findNode(navKeyClass: KClass<out NavKey>): NavGraphNode? {
    for (node in this) {
        if (node.navKeyClass == navKeyClass) return node
        val found = node.children.findNode(navKeyClass)
        if (found != null) return found
    }
    return null
}
