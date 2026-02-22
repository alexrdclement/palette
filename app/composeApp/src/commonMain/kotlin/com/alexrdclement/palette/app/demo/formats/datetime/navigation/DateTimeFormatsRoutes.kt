package com.alexrdclement.palette.app.demo.formats.datetime.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface DateTimeFormatsRoute : NavKey

@Serializable
@SerialName("dateTime")
data object DateTimeFormatsGraph : DateTimeFormatsRoute, NavGraphRoute {
    override val pathSegment = "datetime".toPathSegment()
}

@Serializable
@SerialName("datetime-catalog")
data object DateTimeFormatCatalogRoute : DateTimeFormatsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("datetime-format")
data class DateTimeFormatRoute(
    override val ordinal: Int,
) : DateTimeFormatsRoute, EnumNavKey<DateTimeFormat> {
    override val entries = DateTimeFormat.entries

    val format get() = value
    constructor(format: DateTimeFormat) : this(format.ordinal)
    constructor(pathSegment: PathSegment) : this(format = pathSegment.toEnumEntry(DateTimeFormat.entries))
}
