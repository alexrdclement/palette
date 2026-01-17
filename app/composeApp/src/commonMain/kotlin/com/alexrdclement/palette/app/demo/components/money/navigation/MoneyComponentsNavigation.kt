package com.alexrdclement.palette.app.demo.components.money.navigation

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
object MoneyComponentsGraphRoute

@Serializable
@SerialName("catalog")
object MoneyComponentCatalogRoute

fun NavGraphBuilder.moneyComponentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<MoneyComponentsGraphRoute>(
        startDestination = MoneyComponentCatalogRoute,
    ) {
        moneyComponentCatalogScreen(
            onItemClick = navController::navigateToMoneyComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        moneyComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToMoneyComponents() {
    this.navigate(MoneyComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.moneyComponentCatalogScreen(
    onItemClick: (MoneyComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<MoneyComponentCatalogRoute> {
        CatalogScreen(
            items = MoneyComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Money",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
