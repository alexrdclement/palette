package com.alexrdclement.palette.app.theme.format.datetime.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alexrdclement.palette.app.demo.components.auth.navigation.ComponentRoute
import com.alexrdclement.palette.app.theme.format.datetime.DateTimeFormatCatalogItem
import com.alexrdclement.palette.app.theme.format.datetime.DateTimeFormatScreen
import com.alexrdclement.palette.theme.control.ThemeController
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("dateTimeFormat")
data class DateTimeFormatRoute(
    val ordinal: Int,
)

fun NavController.navigateToDateTimeFormat(format: DateTimeFormatCatalogItem) {
    this.navigate(DateTimeFormatRoute(format.ordinal)) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.dateTimeFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    composable<DateTimeFormatRoute> { backStackEntry ->
        val formatRoute: DateTimeFormatRoute = backStackEntry.toRoute()
        val format = DateTimeFormatCatalogItem.entries[formatRoute.ordinal]
        DateTimeFormatScreen(
            format = format,
            themeController = themeController,
            onNavigateBack = onNavigateBack,
        )
    }
}
