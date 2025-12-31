package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.styles.ButtonStyleScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("buttonStyles")
object ButtonStylesRoute

fun NavController.navigateToButtonStyles() {
    this.navigate(ButtonStylesRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.buttonStylesScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<ButtonStylesRoute> {
        ButtonStyleScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
