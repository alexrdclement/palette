package com.alexrdclement.palette.app.demo.formats.datetime.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.theme.ThemeButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object DateTimeFormatsGraphRoute

@Serializable
@SerialName("dateTime")
object DateTimeFormatCatalogRoute

fun NavGraphBuilder.dateTimeFormatsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<DateTimeFormatsGraphRoute>(
        startDestination = DateTimeFormatCatalogRoute,
    ) {
        dateTimeFormatCatalogScreen(
            onItemClick = navController::navigateToFormat,
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        dateTimeFormatScreen(
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToDateTimeFormats() {
    this.navigate(DateTimeFormatsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.dateTimeFormatCatalogScreen(
    onItemClick: (DateTimeFormat) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<DateTimeFormatCatalogRoute> {
        CatalogScreen(
            items = DateTimeFormat.entries.toList(),
            onItemClick = onItemClick,
            title = "DateTime",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
