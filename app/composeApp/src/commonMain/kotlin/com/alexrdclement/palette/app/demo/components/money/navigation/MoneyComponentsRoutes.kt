package com.alexrdclement.palette.app.demo.components.money.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface MoneyComponentsRoute : NavKey

@Serializable
@SerialName("money-components")
data object MoneyComponentsGraph : MoneyComponentsRoute {
    override val pathSegment = "money".toPathSegment()
}

@Serializable
@SerialName("money-catalog")
data object MoneyComponentCatalogRoute : MoneyComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("money-component")
data class MoneyComponentRoute(
    override val ordinal: Int,
) : MoneyComponentsRoute, EnumNavKey<MoneyComponent> {
    override val entries = MoneyComponent.entries

    val component get() = value
    constructor(component: MoneyComponent) : this(component.ordinal)
    constructor(pathSegment: PathSegment) : this(component = pathSegment.toEnumEntry(MoneyComponent.entries))
}

val moneyComponentsSerializersModule = navKeySerializersModule {
    subclass<MoneyComponentsGraph>()
    subclass<MoneyComponentCatalogRoute>()
    subclass<MoneyComponentRoute>()
}
