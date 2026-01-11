package com.alexrdclement.palette.app.demo.components.core.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class CoreComponent : CatalogItem {
    Button,
    Text,
    TextField,
    ;

    override val title = this.name
}
