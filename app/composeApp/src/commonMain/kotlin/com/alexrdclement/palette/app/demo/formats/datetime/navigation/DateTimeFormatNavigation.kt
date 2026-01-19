package com.alexrdclement.palette.app.demo.formats.datetime.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.formats.datetime.DateTimeFormatScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Format")
data class FormatRoute(
    // Serializable enums not supported in multiplatform navigation as of 2.8.0-alpha10
    val componentOrdinal: Int,
)

fun NavGraphBuilder.dateTimeFormatScreen(
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<FormatRoute> { backStackEntry ->
        val componentRoute: FormatRoute = backStackEntry.toRoute()
        val component = DateTimeFormat.entries[componentRoute.componentOrdinal]
        DateTimeFormatScreen(
            format = component,
            onNavigateBack = onNavigateBack,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToFormat(component: DateTimeFormat) {
    this.navigate(FormatRoute(component.ordinal)) {
        launchSingleTop = true
    }
}
