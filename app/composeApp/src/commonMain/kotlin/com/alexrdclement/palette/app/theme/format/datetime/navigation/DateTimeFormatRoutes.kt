package com.alexrdclement.palette.app.theme.format.datetime.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface DateTimeFormatRoute : NavKey

@Serializable
@SerialName("datetime-graph")
data object DateTimeFormatGraph : DateTimeFormatRoute {
    override val pathSegment = "datetime".toPathSegment()
}

@Serializable
@SerialName("datetime-theme")
data object DateTimeFormatCatalogRoute : DateTimeFormatRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("dateTimeFormat")
data class DateTimeFormatItemRoute(
    override val ordinal: Int,
) : EnumNavKey<DateTimeFormatCatalogItem>, DateTimeFormatRoute {
    override val entries = DateTimeFormatCatalogItem.entries

    constructor(item: DateTimeFormatCatalogItem) : this(item.ordinal)
    constructor(pathSegment: PathSegment) : this(item = pathSegment.toEnumEntry(DateTimeFormatCatalogItem.entries))
}

val dateTimeFormatSerializersModule = navKeySerializersModule {
    subclass<DateTimeFormatGraph>()
    subclass<DateTimeFormatCatalogRoute>()
    subclass<DateTimeFormatItemRoute>()
}
