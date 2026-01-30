package com.alexrdclement.palette.app.main.navigation

import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface MainRoute : NavKey

@Serializable
@SerialName("main")
data object MainGraph : MainRoute {
    override val pathSegment = "main".toPathSegment()
}

@Serializable
@SerialName("catalog")
data object MainCatalogRoute : MainRoute {
    override val pathSegment = "catalog".toPathSegment()
}
