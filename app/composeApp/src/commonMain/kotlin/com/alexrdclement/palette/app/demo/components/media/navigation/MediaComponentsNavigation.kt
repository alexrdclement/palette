package com.alexrdclement.palette.app.demo.components.media.navigation

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
object MediaComponentsGraphRoute

@Serializable
@SerialName("media")
object MediaComponentCatalogRoute

fun NavGraphBuilder.mediaComponentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<MediaComponentsGraphRoute>(
        startDestination = MediaComponentCatalogRoute,
    ) {
        mediaComponentCatalogScreen(
            onItemClick = navController::navigateToComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        mediaComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToMediaComponents() {
    this.navigate(MediaComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.mediaComponentCatalogScreen(
    onItemClick: (MediaComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<MediaComponentCatalogRoute> {
        CatalogScreen(
            items = MediaComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Media",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
