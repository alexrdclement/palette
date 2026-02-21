package com.alexrdclement.palette.navigation

import androidx.navigation3.runtime.NavKey as Navigation3NavKey
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer

fun navKeySerializersModule(
    block: NavKeySerializersModuleBuilder.() -> Unit,
): SerializersModule = NavKeySerializersModuleBuilder().apply(block).build()

class NavKeySerializersModuleBuilder {
    @PublishedApi
    internal data class Entry(
        val clazz: KClass<out NavKey>,
        val serializer: KSerializer<out NavKey>,
    )

    @PublishedApi
    internal val entries = mutableListOf<Entry>()
    private val includes = mutableListOf<SerializersModule>()

    inline fun <reified T : NavKey> subclass() {
        entries.add(Entry(T::class, serializer<T>()))
    }

    fun subclass(clazz: KClass<out NavKey>, serializer: KSerializer<out NavKey>) {
        entries.add(Entry(clazz, serializer))
    }

    fun include(module: SerializersModule) {
        includes.add(module)
    }

    @Suppress("UNCHECKED_CAST")
    fun build(): SerializersModule = SerializersModule {
        polymorphic(Navigation3NavKey::class) {
            entries.forEach { (clazz, serializer) ->
                subclass(clazz as KClass<NavKey>, serializer as KSerializer<NavKey>)
            }
        }
        includes.forEach { include(it) }
    }
}
