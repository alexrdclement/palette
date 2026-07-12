package com.alexrdclement.palette.app.theme

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class ThemeItem : CatalogItem {
    Primitive,
    Semantic,
    Component,
    ;

    override val title = this.name
}
