package com.alexrdclement.palette.app.configuration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.alexrdclement.palette.app.configuration.ConfigurationController
import com.alexrdclement.palette.app.configuration.ConfigurationDialogContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object ConfigurationGraphRoute

@Serializable
@SerialName("configuration")
object ConfigurationRoute

fun NavGraphBuilder.configurationGraph(
    configurationController: ConfigurationController,
    onConfigureThemeClick: () -> Unit,
) {
    navigation<ConfigurationGraphRoute>(
        startDestination = ConfigurationRoute,
    ) {
        configurationDialog(
            configurationController = configurationController,
            onConfigureThemeClick = onConfigureThemeClick,
        )
    }
}

fun NavController.navigateToConfiguration() {
    this.navigate(ConfigurationRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.configurationDialog(
    configurationController: ConfigurationController,
    onConfigureThemeClick: () -> Unit,
) {
    dialog<ConfigurationRoute> {
        ConfigurationDialogContent(
            configurationController = configurationController,
            onConfigureThemeClick = onConfigureThemeClick,
        )
    }
}
