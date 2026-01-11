package com.alexrdclement.palette.app.theme.styles.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alexrdclement.palette.app.theme.styles.OutlineStyleScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("outlineStyles")
object OutlineStylesRoute

fun NavController.navigateToOutlineStyles() {
    this.navigate(OutlineStylesRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.outlineStylesScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<OutlineStylesRoute> {
        OutlineStyleScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
