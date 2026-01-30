package com.alexrdclement.palette.navigation

import kotlin.test.Test
import kotlin.test.assertEquals

class NavGraphResolveTest {

    @Test
    fun `graph route resolves to start route`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            navGraph(
                root = Graph1,
                start = Route1,
            ) {
                route(Route1)
            }
        }

        val resolved = navGraph.resolve(Graph1)

        assertEquals(Route1, resolved)
    }

    @Test
    fun `non-graph route returns route as-is`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
        }

        val resolved = navGraph.resolve(Route1)

        assertEquals(Route1, resolved)
    }

    @Test
    fun `nested graph resolves recursively to leaf start route`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
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

        val resolved = navGraph.resolve(Graph1)

        assertEquals(Route1, resolved)
    }

    @Test
    fun `route not in graph returns route as-is`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
        }

        val resolved = navGraph.resolve(Route2)

        assertEquals(Route2, resolved)
    }

    @Test
    fun `graph with circular start route returns route to prevent infinite loop`() {
        // Graph1 points to itself as start route
        val navGraph = navGraph(
            root = RootRoute,
            start = RootRoute,
        ) {
            navGraph(
                root = Graph1,
                start = Graph1,
            ) {
                // Empty graph
            }
        }

        val resolved = navGraph.resolve(Graph1)

        assertEquals(Graph1, resolved)
    }
}
