package com.alexrdclement.palette.app

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.navigation.PaletteNav
import com.alexrdclement.palette.app.navigation.rememberPaletteNavController
import com.alexrdclement.palette.theme.components.core.Surface
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.component.ComponentTokens
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.PrimitiveTokens
import com.alexrdclement.palette.theme.semantic.SemanticTokens

@Composable
fun App(
    navController: NavController = rememberPaletteNavController(),
    themeController: ThemeController = rememberThemeController(),
) {
    PaletteTheme(
        primitive = PrimitiveTokens(
            typography = themeController.primitiveTypography,
        ),
        semantic = SemanticTokens(
            colorScheme = themeController.colorScheme,
            typography = themeController.typography,
            shapeScheme = themeController.shapeScheme,
            spacing = themeController.spacing,
            indication = themeController.indication,
            formats = themeController.formats,
        ),
        component = ComponentTokens(
            styles = themeController.styles,
        ),
    ) {
        Surface {
            PaletteNav(
                themeController = themeController,
                navController = navController,
            )
        }
    }
}
