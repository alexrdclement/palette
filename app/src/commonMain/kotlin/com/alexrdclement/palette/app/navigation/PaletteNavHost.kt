package com.alexrdclement.palette.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alexrdclement.palette.app.catalog.MainCatalogItem
import com.alexrdclement.palette.app.catalog.navigation.CatalogRoute
import com.alexrdclement.palette.app.catalog.navigation.mainCatalogScreen
import com.alexrdclement.palette.app.configuration.ConfigurationController
import com.alexrdclement.palette.app.configuration.navigation.configurationGraph
import com.alexrdclement.palette.app.configuration.navigation.navigateToConfiguration
import com.alexrdclement.palette.app.demo.components.componentsGraph
import com.alexrdclement.palette.app.demo.components.navigateToComponents
import com.alexrdclement.palette.app.demo.experiments.navigation.experimentsGraph
import com.alexrdclement.palette.app.demo.experiments.navigation.navigateToExperiments
import com.alexrdclement.palette.app.demo.modifiers.navigation.navigateToModifiers
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifierScreen
import com.alexrdclement.palette.app.theme.navigation.navigateToTheme
import com.alexrdclement.palette.app.theme.navigation.themeGraph
import com.alexrdclement.palette.theme.control.ThemeController

@Composable
fun PaletteNavHost(
    configurationController: ConfigurationController,
    themeController: ThemeController,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CatalogRoute,
    ) {
        mainCatalogScreen(
            onItemClick = { item ->
                when (item) {
                    MainCatalogItem.Components -> navController.navigateToComponents()
                    MainCatalogItem.Experiments -> navController.navigateToExperiments()
                    MainCatalogItem.Modifiers -> navController.navigateToModifiers()
                }
            },
            onConfigureClick = navController::navigateToConfiguration,
        )
        configurationGraph(
            configurationController = configurationController,
            onConfigureThemeClick = navController::navigateToTheme,
        )
        componentsGraph(
            navController = navController,
            onConfigureClick = navController::navigateToConfiguration,
        )
        experimentsGraph(
            navController = navController,
            onConfigureClick = navController::navigateToConfiguration,
        )
        modifierScreen(
            navController = navController,
            onConfigureClick = navController::navigateToConfiguration,
        )
        themeGraph(
            navController = navController,
            themeController = themeController,
        )
    }
}
