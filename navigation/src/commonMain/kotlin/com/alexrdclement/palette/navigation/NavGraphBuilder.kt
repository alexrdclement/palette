package com.alexrdclement.palette.navigation

inline fun <reified T : NavKey> navGraph(
    root: T,
    start: NavKey,
    noinline block: NavGraphBuilder.() -> Unit = {},
): NavGraph {
    return NavGraphBuilder().apply {
        navGraph(
            root = root,
            start = start,
            children = block,
        )
    }.build(root)
}

class NavGraphBuilder(
    val parent: NavKey? = null,
) {
    val nodes = mutableListOf<NavGraphNode>()

    inline fun <reified T : NavKey> navGraph(
        root: T,
        start: NavKey,
        children: NavGraphBuilder.() -> Unit = {}
    ) {
        val childrenBuilder = NavGraphBuilder(parent = root)
        childrenBuilder.children()

        nodes.add(
            NavGraphNode(
                pathSegment = root.pathSegment,
                navKeyClass = T::class,
                parser = { root },
                parent = parent,
                children = childrenBuilder.nodes,
                graphStartRoute = start,
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
                children = childrenBuilder.nodes,
            )
        )
    }

    inline fun <reified T : NavKey> wildcardRoute(
        children: NavGraphBuilder.() -> Unit = {},
        noinline parser: (PathSegment) -> NavKey?
    ) {
        val pathSegment = PathSegment.Wildcard

        // For children of wildcard routes, use the current parent (the container)
        // since wildcard routes don't have a single concrete instance to use as parent
        val childrenBuilder = NavGraphBuilder(parent = parent)
        childrenBuilder.children()

        nodes.add(
            NavGraphNode(
                pathSegment = pathSegment,
                navKeyClass = T::class,
                parser = parser,
                parent = parent,
                children = childrenBuilder.nodes,
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

    fun build(root: NavKey): NavGraph = NavGraph(root = root, nodes = nodes)
}
