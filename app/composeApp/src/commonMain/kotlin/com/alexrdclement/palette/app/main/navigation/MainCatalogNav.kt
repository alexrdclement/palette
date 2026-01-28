package com.alexrdclement.palette.app.main.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.app.catalog.CatalogScreen
import com.alexrdclement.palette.app.demo.components.navigation.ComponentsGraph
import com.alexrdclement.palette.app.demo.components.navigation.componentsNavGraph
import com.alexrdclement.palette.app.demo.formats.navigation.FormatsGraph
import com.alexrdclement.palette.app.demo.formats.navigation.formatsNavGraph
import com.alexrdclement.palette.app.demo.modifiers.navigation.ModifiersGraph
import com.alexrdclement.palette.app.demo.modifiers.navigation.modifiersNavGraph
import com.alexrdclement.palette.app.main.MainCatalogItem
import com.alexrdclement.palette.app.theme.ThemeButton
import com.alexrdclement.palette.app.theme.navigation.ThemeGraph
import com.alexrdclement.palette.navigation.NavController
import com.alexrdclement.palette.navigation.NavGraphBuilder
import com.alexrdclement.palette.navigation.NavKey

fun NavGraphBuilder.mainCatalogNavGraph() {
    route(MainCatalogRoute)
    componentsNavGraph()
    formatsNavGraph()
    modifiersNavGraph()
}

@Composable
fun MainCatalogNav(
    route: NavKey,
    navController: NavController,
) {
    when (route) {
        is MainCatalogRoute -> CatalogScreen(
            items = MainCatalogItem.entries.toList(),
            onItemClick = { item ->
                when (item) {
                    MainCatalogItem.Components -> navController.navigate(ComponentsGraph)
                    MainCatalogItem.Formats -> navController.navigate(FormatsGraph)
                    MainCatalogItem.Modifiers -> navController.navigate(ModifiersGraph)
                }
            },
            actions = {
                ThemeButton(
                    onClick = { navController.navigate(ThemeGraph) },
                )
            },
        )
    }
}
