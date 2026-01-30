package com.alexrdclement.palette.navigation

data class TestRoute(
    override val pathSegment: PathSegment,
    val parent: NavKey? = null,
) : NavKey

fun TestRoute(value: String, parent: NavKey? = null) = TestRoute(PathSegment(value), parent)

data object RootRoute : NavKey {
    override val pathSegment = PathSegment("root")
}

data object Graph1 : NavKey {
    override val pathSegment = PathSegment("graph1")
}

data object Graph2 : NavKey {
    override val pathSegment = PathSegment("graph2")
}

data object TestRouteWildcard : NavKey {
    override val pathSegment = PathSegment.Wildcard
}

data object Route1 : NavKey {
    override val pathSegment = PathSegment("route1")
}

data object Route2 : NavKey {
    override val pathSegment = PathSegment("route2")
}

data object Route3 : NavKey {
    override val pathSegment = PathSegment("route3")
}

data object EmptyRoute : NavKey {
    override val pathSegment = PathSegment("")
}
