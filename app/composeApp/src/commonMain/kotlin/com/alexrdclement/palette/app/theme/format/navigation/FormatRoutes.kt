package com.alexrdclement.palette.app.theme.format.navigation

import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface FormatRoute : NavKey

@Serializable
@SerialName("formats")
data object FormatsGraph : FormatRoute {
    override val pathSegment = "formats".toPathSegment()
}

@Serializable
@SerialName("catalog")
data object FormatCatalogRoute : FormatRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("moneyFormat")
data object MoneyFormatRoute : FormatRoute {
    override val pathSegment = "money".toPathSegment()
}

@Serializable
@SerialName("numberFormat")
data object NumberFormatRoute : FormatRoute {
    override val pathSegment = "number".toPathSegment()
}

@Serializable
@SerialName("textFormat")
data object TextFormatRoute : FormatRoute {
    override val pathSegment = "text".toPathSegment()
}
