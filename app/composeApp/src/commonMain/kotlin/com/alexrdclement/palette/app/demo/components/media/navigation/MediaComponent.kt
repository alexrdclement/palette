package com.alexrdclement.palette.app.demo.components.media.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class MediaComponent : CatalogItem {
    MediaControlSheet,
    ;

    override val title = this.name
}
