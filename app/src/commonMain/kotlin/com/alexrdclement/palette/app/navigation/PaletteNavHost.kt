package com.alexrdclement.palette.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alexrdclement.palette.app.catalog.MainCatalogItem
import com.alexrdclement.palette.app.catalog.navigation.CatalogRoute
import com.alexrdclement.palette.app.catalog.navigation.mainCatalogScreen
import com.alexrdclement.palette.app.demo.components.componentsGraph
import com.alexrdclement.palette.app.demo.components.navigateToComponents
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifierScreen
import com.alexrdclement.palette.app.demo.modifiers.navigation.navigateToModifiers
import com.alexrdclement.palette.app.theme.navigation.navigateToTheme
import com.alexrdclement.palette.app.theme.navigation.themeGraph
import com.alexrdclement.palette.theme.control.ThemeController

@Composable
fun PaletteNavHost(
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
                    MainCatalogItem.Modifiers -> navController.navigateToModifiers()
                }
            },
            onThemeClick = navController::navigateToTheme,
        )
        componentsGraph(
            navController = navController,
            onThemeClick = navController::navigateToTheme,
        )
        modifierScreen(
            navController = navController,
            onThemeClick = navController::navigateToTheme,
        )
        themeGraph(
            navController = navController,
            themeController = themeController,
        )
    }
}
