package com.alexrdclement.palette.app.theme.format.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.format.Format
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object FormatGraphRoute

@Serializable
@SerialName("formats")
object FormatCatalogRoute

fun NavGraphBuilder.formatsGraph(
    navController: NavController,
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    navigation<FormatGraphRoute>(
        startDestination = FormatCatalogRoute,
    ) {
        formatScreen(
            onItemClick = { format ->
                when (format) {
                    Format.DateTime -> navController.navigateToDateTimeFormat()
                    Format.Money -> navController.navigateToMoneyFormat()
                    Format.Number -> navController.navigateToNumberFormat()
                }
            },
            onNavigateBack = onNavigateBack,
        )
        dateTimeFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
        moneyFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
        numberFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavController.navigateToFormat() {
    this.navigate(FormatGraphRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.formatScreen(
    onItemClick: (Format) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<FormatCatalogRoute> {
        CatalogScreen(
            items = Format.entries.toList(),
            onItemClick = onItemClick,
            title = "Format",
            onNavigateBack = onNavigateBack,
        )
    }
}
