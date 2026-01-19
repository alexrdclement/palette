package com.alexrdclement.palette.app.demo.formats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.formats.core.navigation.coreFormatsGraph
import com.alexrdclement.palette.app.demo.formats.core.navigation.navigateToCoreFormats
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.dateTimeFormatsGraph
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.navigateToDateTimeFormats
import com.alexrdclement.palette.app.demo.formats.money.navigation.moneyFormatsGraph
import com.alexrdclement.palette.app.demo.formats.money.navigation.navigateToMoneyFormats
import com.alexrdclement.palette.app.demo.popBackStackIfResumed
import com.alexrdclement.palette.app.theme.ThemeButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object FormatsGraphRoute

@Serializable
@SerialName("formats")
object FormatCatalogRoute

fun NavGraphBuilder.formatsGraph(
    navController: NavController,
    onThemeClick: () -> Unit,
) {
    navigation<FormatsGraphRoute>(
        startDestination = FormatCatalogRoute,
    ) {
        formatCatalogScreen(
            onItemClick = { formatGroup ->
                when (formatGroup) {
                    FormatCategory.Core -> navController.navigateToCoreFormats()
                    FormatCategory.DateTime -> navController.navigateToDateTimeFormats()
                    FormatCategory.Money -> navController.navigateToMoneyFormats()
                }
            },
            onNavigateBack = navController::popBackStackIfResumed,
            onThemeClick = onThemeClick,
        )
        coreFormatsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        dateTimeFormatsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
        moneyFormatsGraph(
            navController = navController,
            onThemeClick = onThemeClick,
        )
    }
}

fun NavController.navigateToFormats() {
    this.navigate(FormatsGraphRoute) {
        launchSingleTop = true
    }
}

private fun NavGraphBuilder.formatCatalogScreen(
    onItemClick: (FormatCategory) -> Unit,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    composable<FormatCatalogRoute> {
        CatalogScreen(
            items = FormatCategory.entries.toList(),
            onItemClick = onItemClick,
            title = "Formats",
            onNavigateBack = onNavigateBack,
            actions = {
                ThemeButton(onClick = onThemeClick)
            },
        )
    }
}
