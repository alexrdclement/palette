package com.alexrdclement.palette.app.demo.components.color.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.configuration.ConfigureButton
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object ColorComponentsGraphRoute

@Serializable
@SerialName("color")
object ColorComponentCatalogRoute

fun NavGraphBuilder.colorComponentsGraph(
    navController: NavController,
    onConfigureClick: () -> Unit,
) {
    navigation<ColorComponentsGraphRoute>(
        startDestination = ColorComponentCatalogRoute,
    ) {
        colorComponentCatalogScreen(
            onItemClick = navController::navigateToComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onConfigureClick = onConfigureClick,
        )
        colorComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onConfigureClick = onConfigureClick,
        )
    }
}

fun NavController.navigateToColorComponents() {
    this.navigate(ColorComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.colorComponentCatalogScreen(
    onItemClick: (ColorComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onConfigureClick: () -> Unit,
) {
    composable<ColorComponentCatalogRoute> {
        CatalogScreen(
            items = ColorComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Color",
            onNavigateBack = onNavigateBack,
            actions = {
                ConfigureButton(onClick = onConfigureClick)
            },
        )
    }
}
