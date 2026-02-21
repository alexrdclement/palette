package com.alexrdclement.palette.app.demo.components.auth.navigation

import com.alexrdclement.palette.navigation.EnumNavKey
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.navigation.PathSegment
import com.alexrdclement.palette.navigation.navKeySerializersModule
import com.alexrdclement.palette.navigation.toEnumEntry
import com.alexrdclement.palette.navigation.toPathSegment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AuthComponentsRoute : NavKey

@Serializable
@SerialName("auth")
data object AuthComponentsGraph : AuthComponentsRoute {
    override val pathSegment = "auth".toPathSegment()
}

@Serializable
@SerialName("auth-component-catalog")
data object AuthComponentCatalogRoute : AuthComponentsRoute {
    override val pathSegment = "catalog".toPathSegment()
}

@Serializable
@SerialName("auth-component")
data class AuthComponentRoute(
    override val ordinal: Int,
) : AuthComponentsRoute, EnumNavKey<AuthComponent> {
    override val entries = AuthComponent.entries

    val component get() = value
    constructor(component: AuthComponent) : this(component.ordinal)
    constructor(pathSegment: PathSegment) : this(component = pathSegment.toEnumEntry(AuthComponent.entries))
}

val authComponentsSerializersModule = navKeySerializersModule {
    subclass<AuthComponentsGraph>()
    subclass<AuthComponentCatalogRoute>()
    subclass<AuthComponentRoute>()
}
