package com.alexrdclement.palette.app.theme.semantic.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.semantic.SemanticItem
import com.alexrdclement.palette.app.theme.semantic.color.ColorScreen
import com.alexrdclement.palette.app.theme.semantic.format.navigation.FormatsGraph
import com.alexrdclement.palette.app.theme.semantic.format.navigation.formatNavGraph
import com.alexrdclement.palette.app.theme.semantic.format.navigation.formatsEntryProvider
import com.alexrdclement.palette.app.theme.semantic.interaction.navigation.InteractionGraph
import com.alexrdclement.palette.app.theme.semantic.interaction.navigation.interactionEntryProvider
import com.alexrdclement.palette.app.theme.semantic.interaction.navigation.interactionNavGraph
import com.alexrdclement.palette.app.theme.semantic.padding.PaddingScreen
import com.alexrdclement.palette.app.theme.semantic.shape.ShapeScreen
import com.alexrdclement.palette.app.theme.semantic.spacing.SpacingScreen
import com.alexrdclement.palette.app.theme.semantic.typography.TypographyScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.semanticNavGraph() = navGraph(
    root = SemanticGraph,
    start = SemanticCatalogRoute,
) {
    route(SemanticCatalogRoute)
    route(ColorRoute)
    route(ShapeRoute)
    route(TypographyRoute)
    route(SpacingRoute)
    route(PaddingRoute)
    interactionNavGraph()
    formatNavGraph()
}

fun EntryProviderScope<NavKey>.semanticEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<SemanticCatalogRoute, SemanticItem>(
        onItemClick = { item ->
            when (item) {
                SemanticItem.Color -> navController.navigate(ColorRoute)
                SemanticItem.Typography -> navController.navigate(TypographyRoute)
                SemanticItem.Shape -> navController.navigate(ShapeRoute)
                SemanticItem.Spacing -> navController.navigate(SpacingRoute)
                SemanticItem.Padding -> navController.navigate(PaddingRoute)
                SemanticItem.Interaction -> navController.navigate(InteractionGraph)
                SemanticItem.Format -> navController.navigate(FormatsGraph)
            }
        },
        title = "Semantic",
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

    entry<PaddingRoute> {
        PaddingScreen(
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

    interactionEntryProvider(navController, themeController)
    formatsEntryProvider(navController, themeController)
}
