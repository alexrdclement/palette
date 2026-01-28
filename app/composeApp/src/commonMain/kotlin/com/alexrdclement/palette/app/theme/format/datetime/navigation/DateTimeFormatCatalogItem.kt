package com.alexrdclement.palette.app.theme.format.datetime.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class DateTimeFormatCatalogItem : CatalogItem {
    Date,
    DateTime,
    Instant,
    Time,
    ;

    override val title = this.name
}
