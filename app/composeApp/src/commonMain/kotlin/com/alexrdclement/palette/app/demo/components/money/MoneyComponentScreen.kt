package com.alexrdclement.palette.app.demo.components.money

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.money.navigation.MoneyComponent
import com.alexrdclement.palette.components.demo.money.CurrencyAmountFieldDemo
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components

@Composable
fun MoneyComponentScreen(
    component: MoneyComponent,
    onNavigateUp: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        style = PaletteTheme.components.scaffold,
        topBar = {
            DemoTopBar(
                title = component.title,
                onNavigateUp = onNavigateUp,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (component) {
            MoneyComponent.CurrencyAmountField -> CurrencyAmountFieldDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
