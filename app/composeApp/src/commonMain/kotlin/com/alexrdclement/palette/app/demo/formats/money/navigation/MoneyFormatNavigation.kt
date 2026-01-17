package com.alexrdclement.palette.app.demo.formats.money.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("format")
data class FormatRoute(
    // Serializable enums not supported in multiplatform navigation as of 2.8.0-alpha10
    val ordinal: Int,
)

fun NavGraphBuilder.moneyFormatScreen(
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<FormatRoute> { backStackEntry ->
        val formatRoute: FormatRoute = backStackEntry.toRoute()
        val format = MoneyFormat.entries[formatRoute.ordinal]
        MoneyFormatScreen(
            format = format,
            onNavigateBack = onNavigateBack,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToMoneyFormat(format: MoneyFormat) {
    this.navigate(FormatRoute(format.ordinal)) {
        launchSingleTop = true
    }
}
