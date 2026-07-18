package com.alexrdclement.palette.app.theme.semantic

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class SemanticItem : CatalogItem {
    Color,
    Typography,
    Shape,
    Spacing,
    Padding,
    Interaction,
    Format,
    ;

    override val title = name
}
