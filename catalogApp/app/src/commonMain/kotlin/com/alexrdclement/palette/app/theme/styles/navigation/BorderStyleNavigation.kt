package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.styles.BorderStyleScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("borderStyles")
object BorderStylesRoute

fun NavController.navigateToBorderStyles() {
    this.navigate(BorderStylesRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.borderStylesScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<BorderStylesRoute> {
        BorderStyleScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
