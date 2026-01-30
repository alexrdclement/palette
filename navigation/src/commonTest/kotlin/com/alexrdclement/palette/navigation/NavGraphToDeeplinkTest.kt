package com.alexrdclement.palette.navigation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NavGraphToDeeplinkTest {

    @Test
    fun `with single route generates correct deeplink`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = Route1,
        ) {
            route(Route1)
        }

        val deeplink = Route1.toDeeplink(navGraph)

        assertEquals("${RootRoute.pathSegment}/${Route1.pathSegment}", deeplink)
    }

    @Test
    fun `with multiple routes at same level`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = Route1,
        ) {
            route(Route1)
            route(Route2)
        }

        val deeplink1 = Route1.toDeeplink(navGraph)
        assertEquals("${RootRoute.pathSegment}/${Route1.pathSegment}", deeplink1)

        val deeplink2 = Route2.toDeeplink(navGraph)
        assertEquals("${RootRoute.pathSegment}/${Route2.pathSegment}", deeplink2)
    }

    @Test
    fun `with nested graph includes graph in path`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = Graph1,
        ) {
            navGraph(
                root = Graph1,
                start = Route1,
            ) {
                route(Route1)
                route(Route2)
            }
        }

        val deeplink = Route2.toDeeplink(navGraph)

        assertEquals("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Route2.pathSegment}", deeplink)
    }

    @Test
    fun `with deeply nested graphs includes all parents in path`() {
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
                    start = Route1,
                ) {
                    route(Route1)
                }
            }
        }

        val deeplink = Route1.toDeeplink(navGraph)

        assertEquals("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Graph2.pathSegment}/${Route1.pathSegment}", deeplink)
    }

    @Test
    fun `with empty root path segment excludes root from deeplink`() {
        val emptyRoot = EmptyRoute
        val child = TestRoute("child", parent = emptyRoot)

        val navGraph = navGraph(
            root = emptyRoot,
            start = emptyRoot,
        ) {
            route(child)
        }

        val deeplink = child.toDeeplink(navGraph)

        assertEquals("child", deeplink)
    }

    @Test
    fun `wildcard route with children generates deeplink correctly`() {
        val navGraph = navGraph(
            root = RootRoute,
            start = TestRoute(PathSegment.Wildcard),
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

        val deeplink = Route1.toDeeplink(navGraph)

        assertEquals("${RootRoute.pathSegment}/${Route1.pathSegment}", deeplink)
    }

    @Test
    fun `wildcard route with nested graph generates deeplink for child route`() {
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

        val deeplink = Route2.toDeeplink(navGraph)

        assertEquals("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Route2.pathSegment}", deeplink)
    }
}

