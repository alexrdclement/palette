package com.alexrdclement.palette.app.demo.formats

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class FormatCategory : CatalogItem {
    Core,
    DateTime,
    Money,
    ;

    override val title = this.name
}
