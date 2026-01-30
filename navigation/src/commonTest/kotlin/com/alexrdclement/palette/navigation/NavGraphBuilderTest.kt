package com.alexrdclement.palette.navigation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class NavGraphBuilderTest {

    @Test
    fun `creates graph with single route`() {
        val navGraph = navGraph(
            root = Route1,
            start = Route1,
        ) {
            route(Route1)
        }

        val node = navGraph.findNode(Route1::class)
        assertNotNull(node)
        assertEquals(Route1.pathSegment, node.pathSegment)
        assertNull(node.parent)
        assertEquals(1, node.children.size)
        assertEquals(Route1, node.graphStartRoute)
    }

    @Test
    fun `creates graph with start route`() {
        val navGraph = navGraph(
            root = Graph1,
            start = Route1,
        ) {
            route(Route1)
        }

        val graphNode = navGraph.findNode(Graph1::class)
        assertNotNull(graphNode)
        assertEquals(Route1, graphNode.graphStartRoute)
        assertEquals(1, graphNode.children.size)
    }

    @Test
    fun `multiple routes at same level`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            route(Route1)
            route(Route2)
        }

        val rootNode = navGraph.nodes.firstOrNull()
        assertNotNull(rootNode)
        assertEquals(2, rootNode.children.size)

        val route1Node = rootNode.children.firstOrNull { it.pathSegment.value == Route1.pathSegment.value }
        val route2Node = rootNode.children.firstOrNull { it.pathSegment.value == Route2.pathSegment.value }
        assertNotNull(route1Node)
        assertNotNull(route2Node)
    }

    @Test
    fun `nested creates parent-child relationship`() {
        val navGraph = navGraph(
            root = Graph1,
            start = Route1,
        ) {
            route(Route1)
            navGraph(
                root = Graph2,
                start = Route2,
            ) {
                route(Route2)
            }
        }

        val parentNode = navGraph.nodes.firstOrNull { it.pathSegment.value == Graph1.pathSegment.value }
        assertNotNull(parentNode)
        assertNull(parentNode.parent)
        assertEquals(2, parentNode.children.size) // route1 + graph2

        val childNode = parentNode.children.firstOrNull { it.pathSegment.value == Graph2.pathSegment.value }
        assertNotNull(childNode)
        assertEquals(Graph1, childNode.parent)
        assertEquals(1, childNode.children.size) // route2
    }

    @Test
    fun `creates wildcard route with wildcard path segment`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            wildcardRoute<TestRoute> { segment ->
                TestRoute(segment.value)
            }
        }

        val rootNode = navGraph.nodes.firstOrNull()
        assertNotNull(rootNode)

        val wildcardNode = rootNode.children.firstOrNull()
        assertNotNull(wildcardNode)
        assertEquals(PathSegment.Wildcard, wildcardNode.pathSegment)
    }

    @Test
    fun `wildcard matches any segment`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            wildcardRoute<TestRoute> { segment ->
                TestRoute(segment.value)
            }
        }

        val route1 = navGraph.parseDeeplink("${RootRoute.pathSegment}/anything")
        assertEquals(TestRoute("anything"), route1)

        val route2 = navGraph.parseDeeplink("${RootRoute.pathSegment}/something-else")
        assertEquals(TestRoute("something-else"), route2)
    }

    @Test
    fun `wildcard route with children does not call parser with Wildcard`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            wildcardRoute<TestRoute>(
                children = {
                    route(Route1)
                }
            ) { segment ->
                require(segment != PathSegment.Wildcard) {
                    "Parser should not be called with PathSegment.Wildcard"
                }
                TestRoute(segment.value)
            }
        }

        val rootNode = navGraph.nodes.firstOrNull()
        assertNotNull(rootNode)
        assertEquals(1, rootNode.children.size)

        val wildcardNode = rootNode.children.firstOrNull()
        assertNotNull(wildcardNode)
        assertEquals(PathSegment.Wildcard, wildcardNode.pathSegment)

        assertEquals(1, wildcardNode.children.size)
        val childNode = wildcardNode.children.firstOrNull()
        assertNotNull(childNode)
        assertEquals(Route1.pathSegment, childNode.pathSegment)

        assertEquals(RootRoute, childNode.parent)
    }

    @Test
    fun `wildcard route with nested graph builds correct structure`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
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
                require(segment != PathSegment.Wildcard) {
                    "Parser should not be called with PathSegment.Wildcard"
                }
                TestRoute(segment.value)
            }
        }

        val rootNode = navGraph.nodes.firstOrNull()
        assertNotNull(rootNode)
        assertEquals(1, rootNode.children.size)

        val wildcardNode = rootNode.children.firstOrNull()
        assertNotNull(wildcardNode)
        assertEquals(PathSegment.Wildcard, wildcardNode.pathSegment)

        assertEquals(1, wildcardNode.children.size)

        val graphNode = wildcardNode.children.firstOrNull { it.pathSegment.value == Graph1.pathSegment.value }
        assertNotNull(graphNode)
        assertEquals(Graph1.pathSegment, graphNode.pathSegment)
        assertEquals(Route1, graphNode.graphStartRoute) // Graph should have start route
        assertEquals(RootRoute, graphNode.parent) // Parent should be RootRoute, not null

        assertEquals(2, graphNode.children.size)

        val route1Node = graphNode.children.firstOrNull { it.pathSegment.value == Route1.pathSegment.value }
        val route2Node = graphNode.children.firstOrNull { it.pathSegment.value == Route2.pathSegment.value }
        assertNotNull(route1Node)
        assertNotNull(route2Node)
        assertEquals(Graph1, route1Node.parent) // Parent should be the graph
        assertEquals(Graph1, route2Node.parent) // Parent should be the graph
    }
}

