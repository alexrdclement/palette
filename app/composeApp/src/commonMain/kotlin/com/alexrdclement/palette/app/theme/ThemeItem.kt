package com.alexrdclement.palette.app.theme

import com.alexrdclement.palette.components.layout.catalog.CatalogItem

enum class ThemeItem : CatalogItem {
    Color,
    Format,
    Interaction,
    Shape,
    Spacing,
    Styles,
    Typography,
    ;

    override val title = this.name
}
