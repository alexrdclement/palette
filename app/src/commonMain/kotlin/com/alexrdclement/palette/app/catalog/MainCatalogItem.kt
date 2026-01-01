package com.alexrdclement.palette.app.catalog

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class MainCatalogItem : CatalogItem {
    Components,
    Modifiers;

    override val title = this.name
}
