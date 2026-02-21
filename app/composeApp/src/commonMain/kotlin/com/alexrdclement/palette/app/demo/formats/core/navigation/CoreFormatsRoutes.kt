package com.alexrdclement.palette.app.demo.formats.core.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface CoreFormatsRoute : NavKey

@Serializable
@SerialName("core-formats")
data object CoreFormatsGraph : CoreFormatsRoute {
    override val pathSegment = "core".toPathSegment()
}

@Serializable
@SerialName("core-format-catalog")
data object CoreFormatCatalogRoute : CoreFormatsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("core-format")
data class CoreFormatRoute(
    override val ordinal: Int,
) : CoreFormatsRoute, EnumNavKey<CoreFormat> {
    override val entries = CoreFormat.entries

    val format get() = value
    constructor(format: CoreFormat) : this(format.ordinal)
    constructor(pathSegment: PathSegment) : this(format = pathSegment.toEnumEntry(CoreFormat.entries))
}

val coreFormatsSerializersModule = navKeySerializersModule {
    subclass<CoreFormatsGraph>()
    subclass<CoreFormatCatalogRoute>()
    subclass<CoreFormatRoute>()
}
