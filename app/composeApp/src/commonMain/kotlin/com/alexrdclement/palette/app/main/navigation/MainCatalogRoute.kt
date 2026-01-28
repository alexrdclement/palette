package com.alexrdclement.palette.app.main.navigation

import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("catalog")
data object MainCatalogRoute : NavKey {
    override val pathSegment = "catalog".toPathSegment()
}
