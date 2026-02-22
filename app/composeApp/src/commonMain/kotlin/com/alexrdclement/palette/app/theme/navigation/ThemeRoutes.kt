package com.alexrdclement.palette.app.theme.navigation

import com.alexrdclement.palette.navigation.NavGraphRoute
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface ThemeRoute : NavKey

@Serializable
@SerialName("theme")
data object ThemeGraph : ThemeRoute, NavGraphRoute {
    override val pathSegment = "theme".toPathSegment()
}

@Serializable
@SerialName("theme-catalog")
data object ThemeCatalogRoute : ThemeRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("theme-color")
data object ColorRoute : ThemeRoute {
    override val pathSegment = "color".toPathSegment()
}

@Serializable
@SerialName("shape")
data object ShapeRoute : ThemeRoute {
    override val pathSegment = "shape".toPathSegment()
}

@Serializable
@SerialName("spacing")
data object SpacingRoute : ThemeRoute {
    override val pathSegment = "spacing".toPathSegment()
}

@Serializable
@SerialName("typography")
data object TypographyRoute : ThemeRoute {
    override val pathSegment = "typography".toPathSegment()
}
