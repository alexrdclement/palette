package com.alexrdclement.palette.app.demo.components.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.components.format.FormatComponentScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("format")
data class ComponentRoute(
    // Serializable enums not supported in multiplatform navigation as of 2.8.0-alpha10
    val componentOrdinal: Int,
)

fun NavGraphBuilder.formatComponentScreen(
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<ComponentRoute> { backStackEntry ->
        val componentRoute: ComponentRoute = backStackEntry.toRoute()
        val component = FormatComponent.entries[componentRoute.componentOrdinal]
        FormatComponentScreen(
            component = component,
            onNavigateBack = onNavigateBack,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToFormatComponent(component: FormatComponent) {
    this.navigate(ComponentRoute(component.ordinal)) {
        launchSingleTop = true
    }
}
