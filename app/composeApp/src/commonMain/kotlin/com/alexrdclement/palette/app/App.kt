package com.alexrdclement.palette.app

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.navigation.PaletteNav
import com.alexrdclement.palette.app.navigation.rememberPaletteNavController
import com.alexrdclement.palette.theme.components.core.Surface
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController

@Composable
fun App(
    navController: NavController = rememberPaletteNavController(),
    themeController: ThemeController = rememberThemeController(),
) {
    PaletteTheme(
        primitive = themeController.primitive,
        semantic = themeController.semantic,
        component = themeController.component,
        isDarkMode = themeController.isDarkMode,
    ) {
        Surface {
            PaletteNav(
                themeController = themeController,
                navController = navController,
            )
        }
    }
}
