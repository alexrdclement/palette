package com.alexrdclement.palette.app.demo.components.navigation

import com.alexrdclement.palette.app.demo.components.auth.navigation.authComponentsSerializersModule
import com.alexrdclement.palette.app.demo.components.color.navigation.colorComponentsSerializersModule
import com.alexrdclement.palette.app.demo.components.core.navigation.coreComponentsSerializersModule
import com.alexrdclement.palette.app.demo.components.geometry.navigation.geometryComponentsSerializersModule
import com.alexrdclement.palette.app.demo.components.media.navigation.mediaComponentsSerializersModule
import com.alexrdclement.palette.app.demo.components.money.navigation.moneyComponentsSerializersModule
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ComponentsRoute : NavKey

@Serializable
@SerialName("components")
data object ComponentsGraph : ComponentsRoute {
    override val pathSegment = "components".toPathSegment()
}

@Serializable
@SerialName("component-catalog")
data object ComponentCatalogRoute : ComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

val componentsSerializersModule = navKeySerializersModule {
    subclass<ComponentsGraph>()
    subclass<ComponentCatalogRoute>()
    include(authComponentsSerializersModule)
    include(colorComponentsSerializersModule)
    include(coreComponentsSerializersModule)
    include(geometryComponentsSerializersModule)
    include(mediaComponentsSerializersModule)
    include(moneyComponentsSerializersModule)
}
