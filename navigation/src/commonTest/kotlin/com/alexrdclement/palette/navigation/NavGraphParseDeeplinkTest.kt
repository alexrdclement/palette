package com.alexrdclement.palette.navigation

import kotlin.test.Test
import kotlin.test.assertEquals

class NavGraphParseDeeplinkTest {

    @Test
    fun `with single segment`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/${Route1.pathSegment}")

        assertEquals(Route1, route)
    }

    @Test
    fun `with multiple segments`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
            route(Route2)
        }

        val route1 = navGraph.parseDeeplink("${RootRoute.pathSegment}/${Route1.pathSegment}")
        assertEquals(Route1, route1)

        val route2 = navGraph.parseDeeplink("${RootRoute.pathSegment}/${Route2.pathSegment}")
        assertEquals(Route2, route2)
    }

    @Test
    fun `filters empty segments`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
        }

        // Leading, trailing, and consecutive slashes
        val route = navGraph.parseDeeplink("/${RootRoute.pathSegment}//${Route1.pathSegment}//")

        assertEquals(Route1, route)
    }

    @Test
    fun `with no matches returns null`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
        }

        val route = navGraph.parseDeeplink("nonexistent/path")

        assertEquals(null, route)
    }

    @Test
    fun `with graph containers returns only final destination`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = Graph1,
        ) {
            navGraph(
                root = Graph1,
                start = Route2,
            ) {
                route(Route2)
                navGraph(
                    root = Graph2,
                    start = Route1,
                ) {
                    route(Route1)
                }
            }
        }

        val deeplink = "${RootRoute.pathSegment}/${Graph1.pathSegment}/${Graph2.pathSegment}/${Route1.pathSegment}"
        val route = navGraph.parseDeeplink(deeplink)

        assertEquals(Route1, route)
    }

    @Test
    fun `when ending at graph root resolves to start route`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = Graph1,
        ) {
            navGraph(
                root = Graph1,
                start = Route2,
            ) {
                route(Route2)
            }
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/${Graph1.pathSegment}")

        assertEquals(Route2, route)
    }

    @Test
    fun `when ending at nested graph root resolves recursively to leaf start route`() {
        // Create a nested graph structure: Root -> Graph1 -> Graph2 -> Route3
        val navGraph = navGraph(
            root = RootRoute,
            start = Graph1,
        ) {
            navGraph(
                root = Graph1,
                start = Graph2,
            ) {
                navGraph(
                    root = Graph2,
                    start = Route3,
                ) {
                    route(Route3)
                }
            }
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/${Graph1.pathSegment}")

        assertEquals(Route3, route)
    }

    @Test
    fun `with empty root path segment skips root in parsing`() {
        val emptyRoot = EmptyRoute
        val child = TestRoute("child", parent = emptyRoot)

        val navGraph = navGraph(
            root = emptyRoot,
            start = child,
        ) {
            route(child)
        }

        val route = navGraph.parseDeeplink(child.pathSegment.toString())

        assertEquals(child, route)
    }

    @Test
    fun `with empty root path segment excludes root from deeplink generation`() {
        val emptyRoot = EmptyRoute
        val child = TestRoute("child", parent = emptyRoot)

        val navGraph = navGraph(
            root = emptyRoot,
            start = child,
        ) {
            route(child)
        }

        val deeplink = child.toDeeplink(navGraph)

        assertEquals(child.pathSegment.toString(), deeplink)
    }

    @Test
    fun `wildcard route with children parses deeplink to child correctly`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = TestRouteWildcard,
        ) {
            wildcardRoute<TestRoute>(
                children = {
                    route(Route1)
                    route(Route2)
                }
            ) { segment ->
                TestRoute(segment.value)
            }
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/dynamic-value/${Route1.pathSegment}")

        assertEquals(Route1, route)
    }

    @Test
    fun `wildcard route with children parses deeplink to wildcard route correctly`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = TestRouteWildcard,
        ) {
            wildcardRoute<TestRoute>(
                children = {
                    route(Route1)
                    route(Route2)
                }
            ) { segment ->
                TestRoute(segment.value)
            }
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/dynamic-value")

        assertEquals(TestRoute("dynamic-value"), route)
    }

    @Test
    fun `wildcard route with nested graph parses deeplink correctly`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = TestRouteWildcard,
        ) {
            wildcardRoute<TestRoute>(
                children = {
                    navGraph(
                        root = Graph1,
                        start = Route1,
                    ) {
                        route(Route1)
                        route(Route2)
                    }
                }
            ) { segment ->
                TestRoute(segment.value)
            }
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/dynamic/${Graph1.pathSegment}/${Route2.pathSegment}")

        assertEquals(Route2, route)
    }

    @Test
    fun `wildcard route with nested graph ending at graph resolves to start route`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = TestRouteWildcard,
        ) {
            wildcardRoute<TestRoute>(
                children = {
                    navGraph(
                        root = Graph1,
                        start = Route1,
                    ) {
                        route(Route1)
                        route(Route2)
                    }
                }
            ) { segment ->
                TestRoute(segment.value)
            }
        }

        val route = navGraph.parseDeeplink("${RootRoute.pathSegment}/dynamic/${Graph1.pathSegment}")

        assertEquals(Route1, route)
    }
}

