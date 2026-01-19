package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.styles.TextStyleScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("textStyles")
object TextStylesRoute

fun NavController.navigateToTextStyles() {
    this.navigate(TextStylesRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.textStylesScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<TextStylesRoute> {
        TextStyleScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
