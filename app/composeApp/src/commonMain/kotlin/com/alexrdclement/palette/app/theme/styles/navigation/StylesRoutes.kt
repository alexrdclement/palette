package com.alexrdclement.palette.app.theme.styles.navigation

import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface StylesRoute : NavKey

@Serializable
@SerialName("styles")
data object StylesGraph : StylesRoute {
    override val pathSegment = "styles".toPathSegment()
}

@Serializable
@SerialName("styles-catalog")
data object StylesCatalogRoute : StylesRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("borderStyles")
data object BorderStylesRoute : StylesRoute {
    override val pathSegment = "border".toPathSegment()
}

@Serializable
@SerialName("buttonStyles")
data object ButtonStylesRoute : StylesRoute {
    override val pathSegment = "button".toPathSegment()
}

@Serializable
@SerialName("textStyles")
data object TextStylesRoute : StylesRoute {
    override val pathSegment = "text".toPathSegment()
}

val stylesSerializersModule = navKeySerializersModule {
    subclass<StylesGraph>()
    subclass<StylesCatalogRoute>()
    subclass<BorderStylesRoute>()
    subclass<ButtonStylesRoute>()
    subclass<TextStylesRoute>()
}
