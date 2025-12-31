package com.alexrdclement.palette.app.demo.components.money.navigation

import com.alexrdclement.palette.components.layout.catalog.CatalogItem
import kotlinx.serialization.Serializable

@Serializable
enum class MoneyComponent : CatalogItem {
    CurrencyAmountField,
    ;

    override val title = this.name
}
