package com.alexrdclement.palette.app.demo.modifiers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.modifiers.DemoModifier
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.theme.ThemeButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object ModifiersGraphRoute

@Serializable
@SerialName("modifiers")
object ModifierCatalogRoute


fun NavGraphBuilder.modifiersGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<ModifiersGraphRoute>(
        startDestination = ModifierCatalogRoute,
    ) {
        modifierCatalogScreen(
            onItemClick = navController::navigateToModifier,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        modifierScreen(
            navController = navController,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToModifiers() {
    this.navigate(ModifiersGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.modifierCatalogScreen(
    onItemClick: (DemoModifier) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<ModifierCatalogRoute> {
        CatalogScreen(
            items = DemoModifier.entries.toList(),
            onItemClick = onItemClick,
            title = "Modifiers",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
