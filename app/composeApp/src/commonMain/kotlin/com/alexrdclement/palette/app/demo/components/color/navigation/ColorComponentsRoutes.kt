package com.alexrdclement.palette.app.demo.components.color.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ColorComponentsRoute : NavKey

@Serializable
@SerialName("color-components")
data object ColorComponentsGraph : ColorComponentsRoute {
    override val pathSegment = "color".toPathSegment()
}

@Serializable
@SerialName("color-component-catalog")
data object ColorComponentCatalogRoute : ColorComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("color-component")
data class ColorComponentRoute(
    override val ordinal: Int,
) : ColorComponentsRoute, EnumNavKey<ColorComponent> {
    override val entries = ColorComponent.entries

    val component get() = value
    constructor(component: ColorComponent) : this(component.ordinal)
    constructor(pathSegment: PathSegment) : this(component = pathSegment.toEnumEntry(ColorComponent.entries))
}

val colorComponentsSerializersModule = navKeySerializersModule {
    subclass<ColorComponentsGraph>()
    subclass<ColorComponentCatalogRoute>()
    subclass<ColorComponentRoute>()
}
