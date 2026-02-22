package com.alexrdclement.palette.app.theme.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.ThemeItem
import com.alexrdclement.palette.app.theme.color.ColorScreen
import com.alexrdclement.palette.app.theme.format.navigation.FormatsGraph
import com.alexrdclement.palette.app.theme.format.navigation.formatNavGraph
import com.alexrdclement.palette.app.theme.format.navigation.formatsEntryProvider
import com.alexrdclement.palette.app.theme.interaction.navigation.InteractionGraph
import com.alexrdclement.palette.app.theme.interaction.navigation.interactionEntryProvider
import com.alexrdclement.palette.app.theme.interaction.navigation.interactionNavGraph
import com.alexrdclement.palette.app.theme.shape.ShapeScreen
import com.alexrdclement.palette.app.theme.spacing.SpacingScreen
import com.alexrdclement.palette.app.theme.styles.navigation.StylesGraph
import com.alexrdclement.palette.app.theme.styles.navigation.stylesEntryProvider
import com.alexrdclement.palette.app.theme.styles.navigation.stylesNavGraph
import com.alexrdclement.palette.app.theme.typography.TypographyScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.themeNavGraph() = navGraph(
    root = ThemeGraph,
    start = ThemeCatalogRoute,
) {
    route(ThemeCatalogRoute)
    route(ColorRoute)
    route(ShapeRoute)
    route(TypographyRoute)
    route(SpacingRoute)
    interactionNavGraph()
    formatNavGraph()
    stylesNavGraph()
}

fun EntryProviderScope<NavKey>.themeEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<ThemeCatalogRoute, ThemeItem>(
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
        title = "Theme",
        onNavigateUp = navController::goBack,
    )

    entry<ColorRoute> {
        ColorScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<ShapeRoute> {
        ShapeScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<SpacingRoute> {
        SpacingScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<TypographyRoute> {
        TypographyScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    formatsEntryProvider(navController, themeController)
    interactionEntryProvider(navController, themeController)
    stylesEntryProvider(navController, themeController)
}
