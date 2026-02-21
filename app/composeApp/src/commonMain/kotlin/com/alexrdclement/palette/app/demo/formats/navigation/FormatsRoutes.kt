package com.alexrdclement.palette.app.demo.formats.navigation

import com.alexrdclement.palette.app.demo.formats.core.navigation.coreFormatsSerializersModule
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.dateTimeFormatsSerializersModule
import com.alexrdclement.palette.app.demo.formats.money.navigation.moneyFormatsSerializersModule
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface FormatsRoute : NavKey

@Serializable
@SerialName("demo-formats")
data object FormatsGraph : FormatsRoute {
    override val pathSegment = "formats".toPathSegment()
}

@Serializable
@SerialName("demo-format-catalog")
data object FormatCatalogRoute : FormatsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

val formatsSerializersModule = navKeySerializersModule {
    subclass<FormatsGraph>()
    subclass<FormatCatalogRoute>()
    include(coreFormatsSerializersModule)
    include(dateTimeFormatsSerializersModule)
    include(moneyFormatsSerializersModule)
}
