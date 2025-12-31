package com.alexrdclement.palette.app.demo.experiments

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class Experiment : CatalogItem {
    AnimateScrollItemVisible,
    Fade,
    Gradients,
    UiEvent,
    ;

    override val title = this.name
}
