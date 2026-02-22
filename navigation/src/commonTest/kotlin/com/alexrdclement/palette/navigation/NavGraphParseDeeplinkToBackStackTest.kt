package com.alexrdclement.palette.navigation

import kotlin.test.Test
import kotlin.test.assertEquals

class NavGraphParseDeeplinkToBackStackTest {

    @Test
    fun `start route returns single entry`() {
        val navGraph = navGraph(root = RootRoute, start = Route1) {
            route(Route1)
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Route1.pathSegment}")

        assertEquals(listOf(Route1), backStack)
    }

    @Test
    fun `child route includes parent start route`() {
        val navGraph = navGraph(root = RootRoute, start = Graph1) {
            navGraph(root = Graph1, start = Route1) {
                route(Route1)
                route(Route2)
            }
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Route2.pathSegment}")

        assertEquals(listOf(Route1, Route2), backStack)
    }

    @Test
    fun `sibling route at root level includes root start route`() {
        val navGraph = navGraph(root = RootRoute, start = Graph1) {
            navGraph(root = Graph1, start = Route1) {
                route(Route1)
            }
            route(Route2)
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Route2.pathSegment}")

        assertEquals(listOf(Route1, Route2), backStack)
    }

    @Test
    fun `with no match returns start route`() {
        val navGraph = navGraph(root = RootRoute, start = Route1) {
            route(Route1)
        }

        val backStack = navGraph.parseDeeplinkToBackStack("nonexistent/path")

        assertEquals(listOf(Route1), backStack)
    }

    @Test
    fun `nested child route includes only immediate parent`() {
        val navGraph = navGraph(root = RootRoute, start = Graph1) {
            navGraph(root = Graph1, start = Route1) {
                route(Route1)
                navGraph(root = Graph2, start = Route2) {
                    route(Route2)
                    route(Route3)
                }
            }
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Graph2.pathSegment}/${Route3.pathSegment}")

        assertEquals(listOf(Route2, Route3), backStack)
    }

    @Test
    fun `with empty root path segment start route returns single entry`() {
        val navGraph = navGraph(root = EmptyRoute, start = Route1) {
            route(Route1)
        }

        val backStack = navGraph.parseDeeplinkToBackStack(Route1.pathSegment.value)

        assertEquals(listOf(Route1), backStack)
    }

    @Test
    fun `with empty root path segment child route includes parent`() {
        val navGraph = navGraph(root = EmptyRoute, start = Graph1) {
            navGraph(root = Graph1, start = Route1) {
                route(Route1)
                route(Route2)
            }
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${Graph1.pathSegment}/${Route2.pathSegment}")

        assertEquals(listOf(Route1, Route2), backStack)
    }
}
