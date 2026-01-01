package com.alexrdclement.palette.app.demo.components.color.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.components.color.ColorComponentScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("component")
data class ComponentRoute(
    // Serializable enums not supported in multiplatform navigation as of 2.8.0-alpha10
    val componentOrdinal: Int,
)

fun NavGraphBuilder.colorComponentScreen(
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<ComponentRoute> { backStackEntry ->
        val componentRoute: ComponentRoute = backStackEntry.toRoute()
        val component = ColorComponent.entries[componentRoute.componentOrdinal]
        ColorComponentScreen(
            component = component,
            onNavigateBack = onNavigateBack,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToComponent(component: ColorComponent) {
    this.navigate(ComponentRoute(component.ordinal)) {
        launchSingleTop = true
    }
}
