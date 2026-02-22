package com.alexrdclement.palette.navigation

import androidx.navigation3.runtime.NavKey as Navigation3NavKey
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class NavKeySerializationTest {

    private val navKeySerializer = PolymorphicSerializer(Navigation3NavKey::class)

    private fun NavGraph.toJson(classDiscriminator: String = "type") = Json {
        serializersModule = this@toJson.toSerializersModule()
        this.classDiscriminator = classDiscriminator
    }

    @Test
    fun `encodes and decodes leaf route`() {
        val navGraph = navGraph(root = RootRoute, start = Route1) {
            route(Route1)
        }
        val json = navGraph.toJson()

        val encoded = json.encodeToString(navKeySerializer, Route1)
        val decoded = json.decodeFromString(navKeySerializer, encoded)

        assertEquals(Route1, decoded)
    }

    @Test
    fun `encodes and decodes multiple routes`() {
        val navGraph = navGraph(root = RootRoute, start = Route1) {
            route(Route1)
            route(Route2)
            route(Route3)
        }
        val json = navGraph.toJson()

        listOf(Route1, Route2, Route3).forEach { route ->
            val encoded = json.encodeToString(navKeySerializer, route)
            val decoded = json.decodeFromString(navKeySerializer, encoded)
            assertEquals(route, decoded)
        }
    }

    @Test
    fun `encodes and decodes route with dynamic path segment`() {
        val navGraph = navGraph(root = RootRoute, start = RootRoute) {
            wildcardRoute<TestRoute> { segment -> TestRoute(segment.value) }
        }
        val json = navGraph.toJson()
        val route = TestRoute("some-path")

        val encoded = json.encodeToString(navKeySerializer, route)
        val decoded = json.decodeFromString(navKeySerializer, encoded)

        assertEquals(route, decoded)
    }

    @Test
    fun `graph routes are excluded from serializers module`() {
        val navGraph = navGraph(root = Graph1, start = Route1) {
            route(Route1)
        }
        val json = navGraph.toJson()

        assertFailsWith<SerializationException> {
            json.encodeToString(navKeySerializer, Graph1)
        }
    }

    @Test
    fun `leaf routes in nested graphs are included`() {
        val navGraph = navGraph(root = Graph1, start = Route1) {
            route(Route1)
            navGraph(root = Graph2, start = Route2) {
                route(Route2)
                route(Route3)
            }
        }
        val json = navGraph.toJson()

        listOf(Route1, Route2, Route3).forEach { route ->
            val encoded = json.encodeToString(navKeySerializer, route)
            val decoded = json.decodeFromString(navKeySerializer, encoded)
            assertEquals(route, decoded)
        }
    }

    @Test
    fun `encoded JSON includes type discriminator`() {
        val navGraph = navGraph(root = RootRoute, start = Route1) {
            route(Route1)
        }
        val json = navGraph.toJson()

        val encoded = json.encodeToString(navKeySerializer, Route1)

        assertTrue("\"type\"" in encoded)
        assertTrue("\"route1\"" in encoded)
    }

    @Test
    fun `custom class discriminator used in encoded JSON`() {
        val navGraph = navGraph(root = RootRoute, start = Route1) {
            route(Route1)
        }
        val json = navGraph.toJson(classDiscriminator = "class")

        val encoded = json.encodeToString(navKeySerializer, Route1)

        assertTrue("\"class\"" in encoded)
        assertTrue("\"route1\"" in encoded)
    }
}
