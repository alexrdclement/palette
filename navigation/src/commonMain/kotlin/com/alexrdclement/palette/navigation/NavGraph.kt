package com.alexrdclement.palette.navigation

import kotlin.jvm.JvmInline
import kotlin.reflect.KClass

@JvmInline
value class NavGraph(val nodes: List<NavGraphNode>)

fun NavKey.toDeeplink(tree: NavGraph): String {
    val node = tree.findNode(this::class) ?: return pathSegment.value

    // Graph roots don't add their own path segment
    if (node.isGraphRoot) {
        return node.parent?.toDeeplink(tree) ?: ""
    }

    val parentPath = node.parent?.toDeeplink(tree)

    return if (parentPath != null) {
        "$parentPath/${pathSegment.value}"
    } else {
        pathSegment.value
    }
}

fun NavKey.Companion.fromDeeplink(
    deeplink: String,
    navGraph: NavGraph,
): NavKey? {
    val segments = deeplink.split("/").filter { it.isNotEmpty() }.map(::PathSegment)
    return segments.toBackStack(navGraph).lastOrNull()
}

fun List<PathSegment>.toBackStack(
    navGraph: NavGraph,
): List<NavKey> {
    fun matchRoute(nodes: List<NavGraphNode>, segmentIndex: Int, accumulated: List<NavKey>): List<NavKey>? {
        if (segmentIndex >= this.size) return null

        val segment = this[segmentIndex]

        for (node in nodes) {
            val isMatch = node.pathSegment == PathSegment.Wildcard || node.pathSegment == segment

            if (isMatch) {
                val parsed = node.parser(segment) ?: continue
                val newAccumulated = accumulated + parsed

                // Try to match children first
                if (node.children.isNotEmpty() && segmentIndex + 1 < this@toBackStack.size) {
                    val childMatch = matchRoute(node.children, segmentIndex + 1, newAccumulated)
                    if (childMatch != null) return childMatch
                }

                // If we're at the last segment, check for graph root child
                if (segmentIndex == this@toBackStack.size - 1) {
                    val graphRootChild = node.children.firstOrNull { it.isGraphRoot }
                    if (graphRootChild != null) {
                        val graphRootParsed = graphRootChild.parser(PathSegment.Empty)
                        if (graphRootParsed != null) {
                            return newAccumulated + graphRootParsed
                        }
                    }
                    return newAccumulated
                }
            }
        }

        return null
    }

    return matchRoute(navGraph.nodes, 0, emptyList()) ?: emptyList()
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
