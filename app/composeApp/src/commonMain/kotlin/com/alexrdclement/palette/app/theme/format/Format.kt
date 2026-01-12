package com.alexrdclement.palette.app.theme.format

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class Format : CatalogItem {
    Number,
    Money,
    ;

    override val title = name
}
