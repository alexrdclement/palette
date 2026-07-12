package com.alexrdclement.palette.app.theme.primitive.navigation

import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface PrimitiveRoute : NavKey

@Serializable
@SerialName("theme-primitive")
data object PrimitiveGraph : PrimitiveRoute, NavGraphRoute {
    override val pathSegment = "primitive".toPathSegment()
}

@Serializable
@SerialName("theme-primitive-catalog")
data object PrimitiveCatalogRoute : PrimitiveRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("theme-primitive-typography")
data object PrimitiveTypographyRoute : PrimitiveRoute {
    override val pathSegment = "typography".toPathSegment()
}

@Serializable
@SerialName("theme-primitive-shape")
data object PrimitiveShapeRoute : PrimitiveRoute {
    override val pathSegment = "shape".toPathSegment()
}
