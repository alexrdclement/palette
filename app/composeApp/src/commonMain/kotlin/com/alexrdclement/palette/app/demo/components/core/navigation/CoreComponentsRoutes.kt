package com.alexrdclement.palette.app.demo.components.core.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface CoreComponentsRoute : NavKey

@Serializable
@SerialName("core")
data object CoreComponentsGraph : CoreComponentsRoute {
    override val pathSegment = "core".toPathSegment()
}

@Serializable
@SerialName("component-catalog")
data object CoreComponentCatalogRoute : CoreComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("core-component")
data class CoreComponentRoute(
    override val ordinal: Int,
) : CoreComponentsRoute, EnumNavKey<CoreComponent> {
    override val entries = CoreComponent.entries

    val component get() = value
    constructor(component: CoreComponent) : this(component.ordinal)
    constructor(pathSegment: PathSegment) : this(component = pathSegment.toEnumEntry(CoreComponent.entries))
}
