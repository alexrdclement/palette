package com.alexrdclement.palette.app.demo.components.navigation

import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ComponentsRoute : NavKey

@Serializable
@SerialName("components")
data object ComponentsGraph : ComponentsRoute, NavGraphRoute {
    override val pathSegment = "components".toPathSegment()
}

@Serializable
@SerialName("component-catalog")
data object ComponentCatalogRoute : ComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}
