package com.alexrdclement.palette.app.theme.interaction.navigation

import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface InteractionRoute : NavKey

@Serializable
@SerialName("interaction")
data object InteractionGraph : InteractionRoute {
    override val pathSegment = "interaction".toPathSegment()
}

@Serializable
@SerialName("catalog")
data object InteractionCatalogRoute : InteractionRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("indication")
data object IndicationRoute : InteractionRoute {
    override val pathSegment = "indication".toPathSegment()
}
