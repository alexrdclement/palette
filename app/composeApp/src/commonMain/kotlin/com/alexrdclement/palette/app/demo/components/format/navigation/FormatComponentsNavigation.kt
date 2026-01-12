package com.alexrdclement.palette.app.demo.components.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("format")
object FormatComponentsGraphRoute

@Serializable
@SerialName("catalog")
object FormatComponentCatalogRoute

fun NavGraphBuilder.formatComponentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<FormatComponentsGraphRoute>(
        startDestination = FormatComponentCatalogRoute,
    ) {
        formatComponentCatalogScreen(
            onItemClick = navController::navigateToFormatComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        formatComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToFormatComponents() {
    this.navigate(FormatComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.formatComponentCatalogScreen(
    onItemClick: (FormatComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<FormatComponentCatalogRoute> {
        CatalogScreen(
            items = FormatComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Format",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
