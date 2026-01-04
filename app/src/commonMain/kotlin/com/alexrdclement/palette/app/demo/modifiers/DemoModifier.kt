package com.alexrdclement.palette.app.demo.modifiers

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class DemoModifier : CatalogItem {
    ColorInvert,
    ColorSplit,
    Fade,
    Noise,
    Pixelate,
    Warp,
    ;

    override val title: String = this.name
}
