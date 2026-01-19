package com.alexrdclement.palette.app.theme.format.datetime.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.theme.format.datetime.DateTimeFormatCatalogItem
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
object DateTimeFormatGraphRoute

@Serializable
@SerialName("datetime")
object DateTimeFormatCatalogRoute

fun NavGraphBuilder.dateTimeFormatsGraph(
    navController: NavController,
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    navigation<DateTimeFormatGraphRoute>(
        startDestination = DateTimeFormatCatalogRoute,
    ) {
        dateTimeFormatsScreen(
            onItemClick = navController::navigateToDateTimeFormat,
            onNavigateBack = onNavigateBack,
        )
        dateTimeFormatScreen(
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavController.navigateToDateTimeFormats() {
    this.navigate(DateTimeFormatGraphRoute) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.dateTimeFormatsScreen(
    onItemClick: (DateTimeFormatCatalogItem) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<DateTimeFormatCatalogRoute> {
        CatalogScreen(
            items = DateTimeFormatCatalogItem.entries.toList(),
            onItemClick = onItemClick,
            title = "Datetime",
            onNavigateBack = onNavigateBack,
        )
    }
}
