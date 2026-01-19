package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.styles.Styles
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object StylesGraphRoute

@Serializable
@SerialName("styles")
object StylesCatalogRoute

fun NavGraphBuilder.stylesGraph(
    navController: NavController,
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    navigation<StylesGraphRoute>(
        startDestination = StylesCatalogRoute,
    ) {
        stylesScreen(
            onItemClick = { style ->
                when (style) {
                    Styles.Border -> navController.navigateToBorderStyles()
                    Styles.Button -> navController.navigateToButtonStyles()
                    Styles.Text -> navController.navigateToTextStyles()
                }
            },
            onNavigateBack = onNavigateBack,
        )
        borderStylesScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
        buttonStylesScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
        textStylesScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavController.navigateToStyles() {
    this.navigate(StylesGraphRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.stylesScreen(
    onItemClick: (Styles) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<StylesCatalogRoute> {
        CatalogScreen(
            items = Styles.entries.toList(),
            onItemClick = onItemClick,
            title = "Styles",
            onNavigateBack = onNavigateBack,
        )
    }
}
