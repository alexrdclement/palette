package com.alexrdclement.palette.app.demo.components.geometry.navigation

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
object GeometryComponentsGraphRoute

@Serializable
@SerialName("geometry")
object GeometryComponentCatalogRoute

fun NavGraphBuilder.geometryComponentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<GeometryComponentsGraphRoute>(
        startDestination = GeometryComponentCatalogRoute,
    ) {
        geometryComponentCatalogScreen(
            onItemClick = navController::navigateToComponent,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        geometryComponentScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToGeometryComponents() {
    this.navigate(GeometryComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.geometryComponentCatalogScreen(
    onItemClick: (GeometryComponent) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<GeometryComponentCatalogRoute> {
        CatalogScreen(
            items = GeometryComponent.entries.toList(),
            onItemClick = onItemClick,
            title = "Geometry",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
