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
    fun `nested child route builds full ancestor chain`() {
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

        assertEquals(listOf(Route1, Route2, Route3), backStack)
    }

    @Test
    fun `start route of nested subgraph includes ancestor start route`() {
        // Deeplink that resolves to the *start* of a nested subgraph (Route2).
        // Route2 is Graph2's start, so the climbing algorithm must look past Graph2
        // to find Graph1's resolved start (Route1) as the parent entry.
        val navGraph = navGraph(root = RootRoute, start = Graph1) {
            navGraph(root = Graph1, start = Route1) {
                route(Route1)
                navGraph(root = Graph2, start = Route2) {
                    route(Route2)
                    route(Route3)
                }
            }
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Graph2.pathSegment}")

        assertEquals(listOf(Route1, Route2), backStack)
    }

    @Test
    fun `start route of multiple ancestor graphs returns single entry`() {
        // Route1 is the resolved start of Graph1, Graph2, and RootRoute — so it
        // has no genuinely distinct ancestor to prepend.
        val navGraph = navGraph(root = RootRoute, start = Graph1) {
            navGraph(root = Graph1, start = Graph2) {
                navGraph(root = Graph2, start = Route1) {
                    route(Route1)
                }
            }
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Graph1.pathSegment}/${Graph2.pathSegment}/${Route1.pathSegment}")

        assertEquals(listOf(Route1), backStack)
    }

    @Test
    fun `wildcard child route skips wildcard level`() {
        // Children of a wildcardRoute share the wildcard's parent (the containing graph),
        // so the wildcard instance itself does not appear in the synthetic back stack.
        val navGraph = navGraph(root = RootRoute, start = Graph1) {
            navGraph(root = Graph1, start = Route1) {
                route(Route1)
                wildcardRoute<TestRoute>(
                    children = { route(Route2) }
                ) { segment -> TestRoute(segment.value) }
            }
        }

        val backStack = navGraph.parseDeeplinkToBackStack("${RootRoute.pathSegment}/${Graph1.pathSegment}/any-value/${Route2.pathSegment}")

        // Route2's parent is Graph1 (not the wildcard), so back stack is [Route1, Route2]
        assertEquals(listOf(Route1, Route2), backStack)
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
