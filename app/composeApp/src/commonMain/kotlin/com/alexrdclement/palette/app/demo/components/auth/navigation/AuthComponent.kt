package com.alexrdclement.palette.app.demo.components.auth.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class AuthComponent : CatalogItem {
    AuthButton,
    ;

    override val title = this.name
}
