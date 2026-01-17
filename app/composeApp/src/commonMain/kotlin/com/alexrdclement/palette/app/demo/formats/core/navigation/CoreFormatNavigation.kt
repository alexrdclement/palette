package com.alexrdclement.palette.app.demo.formats.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.formats.core.CoreFormatScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("format")
data class FormatRoute(
    // Serializable enums not supported in multiplatform navigation as of 2.8.0-alpha10
    val ordinal: Int,
)

fun NavGraphBuilder.formatScreen(
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<FormatRoute> { backStackEntry ->
        val formatRoute: FormatRoute = backStackEntry.toRoute()
        val format = CoreFormat.entries[formatRoute.ordinal]
        CoreFormatScreen(
            format = format,
            onNavigateBack = onNavigateBack,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToCoreFormat(format: CoreFormat) {
    this.navigate(FormatRoute(format.ordinal)) {
        launchSingleTop = true
    }
}
