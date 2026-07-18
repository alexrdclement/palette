package com.alexrdclement.palette.app.theme.semantic.dimensions.navigation

import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface DimensionRoute : NavKey

@Serializable
@SerialName("dimensions")
data object DimensionsGraph : DimensionRoute, NavGraphRoute {
    override val pathSegment = "dimensions".toPathSegment()
}

@Serializable
@SerialName("dimensions-catalog")
data object DimensionsCatalogRoute : DimensionRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("spacing")
data object SpacingRoute : DimensionRoute {
    override val pathSegment = "spacing".toPathSegment()
}

@Serializable
@SerialName("padding")
data object PaddingRoute : DimensionRoute {
    override val pathSegment = "padding".toPathSegment()
}
