package com.alexrdclement.palette.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alexrdclement.palette.app.navigation.PaletteNavHost
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.rememberThemeController

@Composable
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()
    val themeController = rememberThemeController()

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    PaletteTheme(
        lightColorScheme = themeController.lightColorScheme,
        darkColorScheme = themeController.darkColorScheme,
        isDarkMode = themeController.isDarkMode,
        typography = themeController.typography,
        shapeScheme = themeController.shapeScheme,
        indication = themeController.indication,
        spacing = themeController.spacing,
        styles = themeController.styles,
    ) {
        Surface {
            PaletteNavHost(
                navController = navController,
                themeController = themeController,
            )
        }
    }
}
