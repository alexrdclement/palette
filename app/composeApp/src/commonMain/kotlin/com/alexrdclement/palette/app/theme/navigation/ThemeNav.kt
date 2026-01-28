package com.alexrdclement.palette.app.theme.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.ThemeItem
import com.alexrdclement.palette.app.theme.color.ColorScreen
import com.alexrdclement.palette.app.theme.format.navigation.FormatRoute
import com.alexrdclement.palette.app.theme.format.navigation.FormatsGraph
import com.alexrdclement.palette.app.theme.shape.ShapeScreen
import com.alexrdclement.palette.app.theme.spacing.SpacingScreen
import com.alexrdclement.palette.app.theme.typography.TypographyScreen
import com.alexrdclement.palette.app.theme.format.navigation.FormatsNav
import com.alexrdclement.palette.app.theme.format.navigation.formatNavGraph
import com.alexrdclement.palette.app.theme.interaction.navigation.InteractionGraph
import com.alexrdclement.palette.app.theme.interaction.navigation.InteractionNav
import com.alexrdclement.palette.app.theme.interaction.navigation.InteractionRoute
import com.alexrdclement.palette.app.theme.interaction.navigation.interactionNavGraph
import com.alexrdclement.palette.app.theme.styles.navigation.StylesGraph
import com.alexrdclement.palette.app.theme.styles.navigation.StylesNav
import com.alexrdclement.palette.app.theme.styles.navigation.StylesRoute
import com.alexrdclement.palette.app.theme.styles.navigation.stylesNavGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.themeNavGraph() = navGraph(
    route = ThemeGraph,
    start = ThemeCatalogRoute,
) {
    route(ColorRoute)
    route(ShapeRoute)
    route(TypographyRoute)
    route(SpacingRoute)
    interactionNavGraph()
    formatNavGraph()
    stylesNavGraph()
}

@Composable
fun ThemeNav(
    route: NavKey,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        is ThemeRoute -> ThemeNav(
            route = route,
            navController = navController,
            themeController = themeController,
        )
    }
    FormatsNav(
        route = route,
        navController = navController,
        themeController = themeController,
    )
    InteractionNav(
        route = route,
        navController = navController,
        themeController = themeController,
    )
    StylesNav(
        route = route,
        navController = navController,
        themeController = themeController,
    )
}

@Composable
private fun ThemeNav(
    route: ThemeRoute,
    navController: NavController,
    themeController: ThemeController,
) {
    when (route) {
        ThemeGraph,
        is ThemeCatalogRoute,
            -> CatalogScreen(
            title = "Theme",
            items = ThemeItem.entries.toList(),
            onItemClick = { item ->
                when (item) {
                    ThemeItem.Color -> navController.navigate(ColorRoute)
                    ThemeItem.Format -> navController.navigate(FormatsGraph)
                    ThemeItem.Interaction -> navController.navigate(InteractionGraph)
                    ThemeItem.Shape -> navController.navigate(ShapeRoute)
                    ThemeItem.Spacing -> navController.navigate(SpacingRoute)
                    ThemeItem.Styles -> navController.navigate(StylesGraph)
                    ThemeItem.Typography -> navController.navigate(TypographyRoute)
                }
            },
            onNavigateBack = navController::goBack,
        )
        is ColorRoute -> ColorScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is ShapeRoute -> ShapeScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is SpacingRoute -> SpacingScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
        is TypographyRoute -> TypographyScreen(
            themeController = themeController,
            onNavigateBack = navController::goBack,
        )
    }
}
