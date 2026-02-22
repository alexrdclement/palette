package com.alexrdclement.palette.app

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.navigation.PaletteNav
import com.alexrdclement.palette.app.navigation.rememberPaletteNavController
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController

@Composable
fun App(
    navController: NavController = rememberPaletteNavController(),
    themeController: ThemeController = rememberThemeController(),
) {
    PaletteTheme(
        lightColorScheme = themeController.lightColorScheme,
        darkColorScheme = themeController.darkColorScheme,
        isDarkMode = themeController.isDarkMode,
        typography = themeController.typography,
        shapeScheme = themeController.shapeScheme,
        indication = themeController.indication,
        spacing = themeController.spacing,
        styles = themeController.styles,
        formats = themeController.formats,
    ) {
        Surface {
            PaletteNav(
                themeController = themeController,
                navController = navController,
            )
        }
    }
}
