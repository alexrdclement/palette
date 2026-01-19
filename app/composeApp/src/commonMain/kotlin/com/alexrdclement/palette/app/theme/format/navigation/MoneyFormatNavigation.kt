package com.alexrdclement.palette.app.theme.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.format.MoneyFormatScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("moneyFormat")
object MoneyFormatRoute

fun NavController.navigateToMoneyFormat() {
    this.navigate(MoneyFormatRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.moneyFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<MoneyFormatRoute> {
        MoneyFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
