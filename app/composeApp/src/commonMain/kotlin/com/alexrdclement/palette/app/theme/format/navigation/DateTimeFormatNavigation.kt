package com.alexrdclement.palette.app.theme.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.format.DateTimeFormatScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("dateTimeFormat")
object DateTimeFormatRoute

fun NavController.navigateToDateTimeFormat() {
    this.navigate(DateTimeFormatRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.dateTimeFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<DateTimeFormatRoute> {
        DateTimeFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
