package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.alexrdclement.palette.app.navigation.catalogEntry
import com.alexrdclement.palette.app.theme.styles.BorderStyleScreen
import com.alexrdclement.palette.app.theme.styles.ButtonStyleScreen
import com.alexrdclement.palette.app.theme.styles.Styles
import com.alexrdclement.palette.app.theme.styles.TextStyleScreen
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey
import com.alexrdclement.palette.theme.control.ThemeController

fun NavGraphBuilder.stylesNavGraph() = navGraph(
    root = StylesGraph,
    start = StylesCatalogRoute,
) {
    route(StylesCatalogRoute)
    route(ButtonStylesRoute)
    route(BorderStylesRoute)
    route(TextStylesRoute)
}

fun EntryProviderScope<NavKey>.stylesEntryProvider(
    navController: NavController,
    themeController: ThemeController,
) {
    catalogEntry<StylesCatalogRoute, Styles>(
        onItemClick = { style ->
            when (style) {
                Styles.Border -> navController.navigate(BorderStylesRoute)
                Styles.Button -> navController.navigate(ButtonStylesRoute)
                Styles.Text -> navController.navigate(TextStylesRoute)
            }
        },
        title = "Styles",
        onNavigateUp = navController::goBack,
    )

    entry<BorderStylesRoute> {
        BorderStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<ButtonStylesRoute> {
        ButtonStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }

    entry<TextStylesRoute> {
        TextStyleScreen(
            themeController = themeController,
            onNavigateUp = navController::goBack,
        )
    }
}
