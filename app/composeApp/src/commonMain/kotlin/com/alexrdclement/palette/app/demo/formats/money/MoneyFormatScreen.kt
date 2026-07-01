package com.alexrdclement.palette.app.demo.formats.money

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.money.navigation.MoneyFormat
import com.alexrdclement.palette.theme.components.Scaffold
import com.alexrdclement.palette.formats.demo.money.MoneyFormatDemo
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun MoneyFormatScreen(
    format: MoneyFormat,
    onNavigateUp: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = format.title,
                onNavigateUp = onNavigateUp,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (format) {
            MoneyFormat.MoneyFormat -> MoneyFormatDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
