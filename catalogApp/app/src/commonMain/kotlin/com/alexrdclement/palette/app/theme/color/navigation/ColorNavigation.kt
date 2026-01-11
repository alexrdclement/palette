package com.alexrdclement.palette.app.theme.color.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.color.ColorScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("color")
object ColorRoute

fun NavController.navigateToColor() {
    this.navigate(ColorRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.colorScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<ColorRoute> {
        ColorScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
