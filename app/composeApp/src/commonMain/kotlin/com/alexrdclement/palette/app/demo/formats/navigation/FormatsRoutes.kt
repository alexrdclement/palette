package com.alexrdclement.palette.app.demo.formats.navigation

import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface FormatsRoute : NavKey

@Serializable
@SerialName("formats")
data object FormatsGraph : FormatsRoute {
    override val pathSegment = "formats".toPathSegment()
}

@Serializable
@SerialName("format-catalog")
data object FormatCatalogRoute : FormatsRoute {
    override val pathSegment = "catalog".toPathSegment()
}
