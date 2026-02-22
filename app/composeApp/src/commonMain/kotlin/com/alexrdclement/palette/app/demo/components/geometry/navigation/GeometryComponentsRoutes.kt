package com.alexrdclement.palette.app.demo.components.geometry.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface GeometryComponentsRoute : NavKey

@Serializable
@SerialName("geometry")
data object GeometryComponentsGraph : GeometryComponentsRoute, NavGraphRoute {
    override val pathSegment = "geometry".toPathSegment()
}

@Serializable
@SerialName("geometry-component-catalog")
data object GeometryComponentCatalogRoute : GeometryComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("geometry-component")
data class GeometryComponentRoute(
    override val ordinal: Int,
) : GeometryComponentsRoute, EnumNavKey<GeometryComponent> {
    override val entries = GeometryComponent.entries

    val component get() = value
    constructor(component: GeometryComponent) : this(component.ordinal)
    constructor(pathSegment: PathSegment) : this(component = pathSegment.toEnumEntry(GeometryComponent.entries))
}
