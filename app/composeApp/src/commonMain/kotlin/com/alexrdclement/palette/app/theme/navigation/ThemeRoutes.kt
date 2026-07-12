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
