package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.styles.BorderStyleScreen
import com.alexrdclement.palette.app.theme.styles.ButtonStyleScreen
import com.alexrdclement.palette.app.theme.styles.Styles
import com.alexrdclement.palette.app.theme.styles.TextStyleScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.stylesNavGraph() = navGraph(
    route = StylesGraph,
    start = StylesCatalogRoute,
) {
    route(StylesCatalogRoute)
    route(ButtonStylesRoute)
    route(BorderStylesRoute)
    route(TextStylesRoute)
}

@Composable
fun StylesNav(
    route: NavKey,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        is StylesRoute -> StylesNav(
            route = route,
            navController = navController,
            themeController = themeController,
        )
    }
}

@Composable
private fun StylesNav(
    route: StylesRoute,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        StylesGraph,
        StylesCatalogRoute,
        -> CatalogScreen(
            items = Styles.entries.toList(),
            onItemClick = { style ->
                when (style) {
                    Styles.Border -> navController.navigate(BorderStylesRoute)
                    Styles.Button -> navController.navigate(ButtonStylesRoute)
                    Styles.Text -> navController.navigate(TextStylesRoute)
                }
            },
            title = "Styles",
            onNavigateBack = navController::goBack,
        )
        is BorderStylesRoute -> BorderStyleScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is ButtonStylesRoute -> ButtonStyleScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is TextStylesRoute -> TextStyleScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
    }
}
