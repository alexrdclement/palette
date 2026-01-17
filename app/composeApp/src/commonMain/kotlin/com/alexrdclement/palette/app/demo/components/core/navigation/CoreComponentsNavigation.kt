package com.alexrdclement.palette.app.demo.components.core.navigation

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
object CoreComponentsGraphRoute

@Serializable
@SerialName("core")
object CoreComponentCatalogRoute

fun NavGraphBuilder.coreComponentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<CoreComponentsGraphRoute>(
        startDestination = CoreComponentCatalogRoute,
    ) {
        coreComponentCatalogScreen(
            onItemClick = navController::navigateToComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        coreComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToCoreComponents() {
    this.navigate(CoreComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.coreComponentCatalogScreen(
    onItemClick: (CoreComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<CoreComponentCatalogRoute> {
        CatalogScreen(
            items = CoreComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Core",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
