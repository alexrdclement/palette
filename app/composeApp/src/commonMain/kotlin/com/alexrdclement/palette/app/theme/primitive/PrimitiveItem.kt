package com.alexrdclement.palette.app.theme.primitive

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class PrimitiveItem : CatalogItem {
    Typography,
    Shape,
    Interaction,
    ;

    override val title = name
}
