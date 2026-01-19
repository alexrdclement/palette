package com.alexrdclement.palette.app.demo.formats.money.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.theme.ThemeButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("money")
object MoneyFormatsGraphRoute

@Serializable
@SerialName("catalog")
object MoneyFormatCatalogRoute

fun NavGraphBuilder.moneyFormatsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<MoneyFormatsGraphRoute>(
        startDestination = MoneyFormatCatalogRoute,
    ) {
        moneyFormatCatalogScreen(
            onItemClick = navController::navigateToMoneyFormat,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        moneyFormatScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToMoneyFormats() {
    this.navigate(MoneyFormatsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.moneyFormatCatalogScreen(
    onItemClick: (MoneyFormat) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<MoneyFormatCatalogRoute> {
        CatalogScreen(
            items = MoneyFormat.entries.toList(),
            onItemClick = onItemClick,
            title = "Money",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
