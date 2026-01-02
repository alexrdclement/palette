package com.alexrdclement.palette.app.demo.modifiers.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.modifiers.DemoModifier
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.demo.modifiers.ModifierScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("modifier")
data class ModifierRoute(
    // Serializable enums not supported in multiplatform navigation as of 2.8.0-alpha10
    val modifierTypeOrdinal: Int,
)

fun NavGraphBuilder.modifierScreen(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    composable<ModifierRoute> { backStackEntry ->
        val modifierRoute: ModifierRoute = backStackEntry.toRoute()
        val modifierType = DemoModifier.entries[modifierRoute.modifierTypeOrdinal]
        ModifierScreen(
            modifierType = modifierType,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToModifier(modifierType: DemoModifier) {
    this.navigate(ModifierRoute(modifierType.ordinal)) {
        launchSingleTop = true
    }
}
