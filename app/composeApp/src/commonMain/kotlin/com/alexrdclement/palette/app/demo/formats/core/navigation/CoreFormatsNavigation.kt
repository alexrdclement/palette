package com.alexrdclement.palette.app.demo.formats.core.navigation

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
object CoreFormatsGraphRoute

@Serializable
@SerialName("catalog")
object CoreFormatCatalogRoute

fun NavGraphBuilder.coreFormatsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<CoreFormatsGraphRoute>(
        startDestination = CoreFormatCatalogRoute,
    ) {
        coreFormatCatalogScreen(
            onItemClick = navController::navigateToCoreFormat,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        formatScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToCoreFormats() {
    this.navigate(CoreFormatsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.coreFormatCatalogScreen(
    onItemClick: (CoreFormat) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<CoreFormatCatalogRoute> {
        CatalogScreen(
            items = CoreFormat.entries.toList(),
            onItemClick = onItemClick,
            title = "Core",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
