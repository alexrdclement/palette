package com.alexrdclement.palette.app.theme.semantic

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class SemanticItem : CatalogItem {
    Color,
    Typography,
    Shape,
    Dimension,
    Interaction,
    Format,
    ;

    override val title = name
}
