package com.alexrdclement.palette.app.theme.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.format.NumberFormatScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("numberFormat")
object NumberFormatRoute

fun NavController.navigateToNumberFormat() {
    this.navigate(NumberFormatRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.numberFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<NumberFormatRoute> {
        NumberFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
