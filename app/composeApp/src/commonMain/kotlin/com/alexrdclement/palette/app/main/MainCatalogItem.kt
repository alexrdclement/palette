package com.alexrdclement.palette.app.main

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class MainCatalogItem : CatalogItem {
    Components,
    Formats,
    Modifiers;

    override val title = this.name
}
