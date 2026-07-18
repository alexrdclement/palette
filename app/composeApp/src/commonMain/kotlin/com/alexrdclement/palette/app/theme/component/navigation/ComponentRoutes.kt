package com.alexrdclement.palette.app.theme.component.navigation

import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ComponentRoute : NavKey

@Serializable
@SerialName("theme-component")
data object ComponentGraph : ComponentRoute, NavGraphRoute {
    override val pathSegment = "component".toPathSegment()
}

@Serializable
@SerialName("theme-component-catalog")
data object ComponentCatalogRoute : ComponentRoute {
    override val pathSegment = "catalog".toPathSegment()
}
