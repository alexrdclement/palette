package com.alexrdclement.palette.app.demo.components

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class ComponentCategory : CatalogItem {
    Auth,
    Color,
    Core,
    Geometry,
    Media,
    Money,
    ;

    override val title = this.name
}
