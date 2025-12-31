package com.alexrdclement.palette.app.demo.components.geometry.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class GeometryComponent : CatalogItem {
    CurveStitch,
    Grid,
    Sphere,
    ;

    override val title = this.name
}
