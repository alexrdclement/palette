package com.alexrdclement.palette.app.theme.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.format.TextFormatScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("textFormat")
object TextFormatRoute

fun NavController.navigateToTextFormat() {
    this.navigate(TextFormatRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.textFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<TextFormatRoute> {
        TextFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
