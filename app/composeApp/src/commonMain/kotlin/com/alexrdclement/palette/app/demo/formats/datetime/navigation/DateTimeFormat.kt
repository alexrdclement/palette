package com.alexrdclement.palette.app.demo.formats.datetime.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class DateTimeFormat : CatalogItem {
    Date,
    DateTime,
    Instant,
    Time,
    ;

    override val title = this.name
}
