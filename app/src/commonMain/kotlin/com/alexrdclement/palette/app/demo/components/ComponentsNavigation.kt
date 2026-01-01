package com.alexrdclement.palette.app.demo.components

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.demo.components.auth.navigation.authComponentsGraph
import com.alexrdclement.palette.app.demo.components.auth.navigation.navigateToAuthComponents
import com.alexrdclement.palette.app.demo.components.color.navigation.colorComponentsGraph
import com.alexrdclement.palette.app.demo.components.color.navigation.navigateToColorComponents
import com.alexrdclement.palette.app.demo.components.core.navigation.coreComponentsGraph
import com.alexrdclement.palette.app.demo.components.core.navigation.navigateToCoreComponents
import com.alexrdclement.palette.app.demo.components.datetime.navigation.dateTimeComponentsGraph
import com.alexrdclement.palette.app.demo.components.datetime.navigation.navigateToDateTimeComponents
import com.alexrdclement.palette.app.demo.components.geometry.navigation.geometryComponentsGraph
import com.alexrdclement.palette.app.demo.components.geometry.navigation.navigateToGeometryComponents
import com.alexrdclement.palette.app.demo.components.media.navigation.mediaComponentsGraph
import com.alexrdclement.palette.app.demo.components.media.navigation.navigateToMediaComponents
import com.alexrdclement.palette.app.demo.components.money.navigation.moneyComponentsGraph
import com.alexrdclement.palette.app.demo.components.money.navigation.navigateToMoneyComponents
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object ComponentsGraphRoute

@Serializable
@SerialName("components")
object ComponentCatalogRoute

fun NavGraphBuilder.componentsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<ComponentsGraphRoute>(
        startDestination = ComponentCatalogRoute,
    ) {
        componentCatalogScreen(
            onItemClick = { componentGroup ->
                when (componentGroup) {
                    ComponentCategory.Auth -> navController.navigateToAuthComponents()
                    ComponentCategory.Color -> navController.navigateToColorComponents()
                    ComponentCategory.Core -> navController.navigateToCoreComponents()
                    ComponentCategory.DateTime -> navController.navigateToDateTimeComponents()
                    ComponentCategory.Geometry -> navController.navigateToGeometryComponents()
                    ComponentCategory.Media -> navController.navigateToMediaComponents()
                    ComponentCategory.Money -> navController.navigateToMoneyComponents()
                }
            },
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        authComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        colorComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        coreComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        dateTimeComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        geometryComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        mediaComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        moneyComponentsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToComponents() {
    this.navigate(ComponentsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.componentCatalogScreen(
    onItemClick: (ComponentCategory) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<ComponentCatalogRoute> {
        CatalogScreen(
            items = ComponentCategory.entries.toList(),
            onItemClick = onItemClick,
            title = "Components",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
