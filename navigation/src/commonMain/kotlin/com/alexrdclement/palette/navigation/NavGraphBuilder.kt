package com.alexrdclement.palette.navigation

class NavGraphBuilder(
    val parent: NavKey? = null,
) {
    val nodes = mutableListOf<NavGraphNode>()

    inline fun <reified T : NavKey> navGraph(
        route: T,
        start: NavKey,
        children: NavGraphBuilder.() -> Unit = {}
    ) {
        val childrenBuilder = NavGraphBuilder(parent = route)

        // Add the graph root as the first child
        childrenBuilder.nodes.add(
            NavGraphNode(
                pathSegment = PathSegment.Empty,
                navKeyClass = start::class,
                parser = { start },
                parent = route,
                children = emptyList(),
                isGraphRoot = true,
            )
        )

        // Add other children
        childrenBuilder.children()

        nodes.add(
            NavGraphNode(
                pathSegment = route.pathSegment,
                navKeyClass = T::class,
                parser = { route },
                parent = parent,
                children = childrenBuilder.build().nodes,
            )
        )
    }

    inline fun <reified T : NavKey> route(
        pathSegment: PathSegment,
        noinline parser: (PathSegment) -> NavKey?,
        children: NavGraphBuilder.() -> Unit = {}
    ) {
        val childrenBuilder = NavGraphBuilder(parent = parser(pathSegment))
        childrenBuilder.children()

        nodes.add(
            NavGraphNode(
                pathSegment = pathSegment,
                navKeyClass = T::class,
                parser = parser,
                parent = parent,
                children = childrenBuilder.build().nodes,
            )
        )
    }

    inline fun <reified T : NavKey> wildcardRoute(
        children: NavGraphBuilder.() -> Unit = {},
        noinline parser: (PathSegment) -> NavKey?
    ) {
        val pathSegment = PathSegment.Wildcard

        // Parser should return null for wildcard during tree construction
        // Actual parsing happens at runtime with real path segments
        val childrenBuilder = NavGraphBuilder(parent = parser(pathSegment))
        childrenBuilder.children()

        nodes.add(
            NavGraphNode(
                pathSegment = pathSegment,
                navKeyClass = T::class,
                parser = parser,
                parent = parent,
                children = childrenBuilder.build().nodes,
            )
        )
    }

    inline fun <reified T : NavKey> route(
        navKey: T,
        children: NavGraphBuilder.() -> Unit = {}
    ) {
        route<T>(
            pathSegment = navKey.pathSegment,
            parser = { navKey },
            children = children,
        )
    }

    fun build(): NavGraph = NavGraph(nodes = nodes)
}

fun navGraph(block: NavGraphBuilder.() -> Unit): NavGraph {
    return NavGraphBuilder().apply(block).build()
}
