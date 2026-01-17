package com.alexrdclement.palette.app.demo.components.auth.navigation

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
object AuthComponentsGraphRoute

@Serializable
@SerialName("auth")
object AuthComponentCatalogRoute

fun NavGraphBuilder.authComponentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<AuthComponentsGraphRoute>(
        startDestination = AuthComponentCatalogRoute,
    ) {
        authComponentCatalogScreen(
            onItemClick = navController::navigateToComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        authComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToAuthComponents() {
    this.navigate(AuthComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.authComponentCatalogScreen(
    onItemClick: (AuthComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<AuthComponentCatalogRoute> {
        CatalogScreen(
            items = AuthComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Auth",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
