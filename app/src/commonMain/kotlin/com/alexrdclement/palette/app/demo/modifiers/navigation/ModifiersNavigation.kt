package com.alexrdclement.palette.app.demo.modifiers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.demo.modifiers.ModifierScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("modifiers")
object ModifiersRoute

fun NavGraphBuilder.modifierScreen(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    composable<ModifiersRoute> {
        ModifierScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToModifiers() {
    this.navigate(ModifiersRoute) {
        launchSingleTop = true
    }
}
