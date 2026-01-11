package com.alexrdclement.palette.app.demo.components.color.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class ColorComponent : CatalogItem {
    ColorPicker,
    ;

    override val title = this.name
}
