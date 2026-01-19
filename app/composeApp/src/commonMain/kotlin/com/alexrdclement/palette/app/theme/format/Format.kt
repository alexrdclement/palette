package com.alexrdclement.palette.app.theme.format

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class Format : CatalogItem {
    DateTime,
    Money,
    Number,
    Text,
    ;

    override val title = name
}
