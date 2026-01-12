package com.alexrdclement.palette.app.theme.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.theme.ThemeItem
import com.alexrdclement.palette.app.theme.color.navigation.colorScreen
import com.alexrdclement.palette.app.theme.color.navigation.navigateToColor
import com.alexrdclement.palette.app.theme.format.navigation.formatsGraph
import com.alexrdclement.palette.app.theme.format.navigation.navigateToFormat
import com.alexrdclement.palette.app.theme.interaction.navigation.interactionGraph
import com.alexrdclement.palette.app.theme.interaction.navigation.navigateToInteraction
import com.alexrdclement.palette.app.theme.shape.navigation.navigateToShape
import com.alexrdclement.palette.app.theme.shape.navigation.shapeScreen
import com.alexrdclement.palette.app.theme.spacing.navigation.navigateToSpacing
import com.alexrdclement.palette.app.theme.spacing.navigation.spacingScreen
import com.alexrdclement.palette.app.theme.styles.navigation.navigateToStyles
import com.alexrdclement.palette.app.theme.styles.navigation.stylesGraph
import com.alexrdclement.palette.app.theme.typography.navigation.navigateToTypography
import com.alexrdclement.palette.app.theme.typography.navigation.typographyScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object ThemeGraphRoute

@Serializable
@SerialName("theme")
object ThemeRoute

fun NavGraphBuilder.themeGraph(
    navController: NavController,
    themeController: ThemeController,
) {
    navigation<ThemeGraphRoute>(
        startDestination = ThemeRoute,
    ) {
        themeScreen(
            onItemClick = {
                when (it) {
                    ThemeItem.Color -> navController.navigateToColor()
                    ThemeItem.Format -> navController.navigateToFormat()
                    ThemeItem.Interaction -> navController.navigateToInteraction()
                    ThemeItem.Shape -> navController.navigateToShape()
                    ThemeItem.Spacing -> navController.navigateToSpacing()
                    ThemeItem.Styles -> navController.navigateToStyles()
                    ThemeItem.Typography -> navController.navigateToTypography()
                }
            },
            onNavigateBack = navController::popBackStackIfResumed,
        )
        typographyScreen(
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
        colorScreen(
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
        formatsGraph(
            navController = navController,
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
        shapeScreen(
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
        spacingScreen(
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
        stylesGraph(
            navController = navController,
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
        interactionGraph(
            navController = navController,
            themeController = themeController,
            onNavigateBack = navController::popBackStackIfResumed,
        )
    }
}

fun NavController.navigateToTheme() {
    this.navigate(ThemeRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.themeScreen(
    onItemClick: (ThemeItem) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<ThemeRoute> {
        CatalogScreen(
            title = "Theme",
            items = ThemeItem.entries.toList(),
            onItemClick = onItemClick,
            onNavigateBack = onNavigateBack,
        )
    }
}
