package com.alexrdclement.palette.app.demo.components.datetime.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class DateTimeComponent : CatalogItem {
    DateTimeFormat,
    ;

    override val title = this.name
}
