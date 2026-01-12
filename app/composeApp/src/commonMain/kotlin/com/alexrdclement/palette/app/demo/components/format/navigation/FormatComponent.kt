package com.alexrdclement.palette.app.demo.components.format.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class FormatComponent : CatalogItem {
    Number,
    ;

    override val title = this.name
}
