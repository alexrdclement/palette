package com.alexrdclement.palette.app.demo.formats.core

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.core.navigation.CoreFormat
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.formats.demo.core.NumberFormatDemo
import com.alexrdclement.palette.formats.demo.core.TextFormatDemo
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components

@Composable
fun CoreFormatScreen(
    format: CoreFormat,
    onNavigateUp: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        style = PaletteTheme.components.scaffold,
        topBar = {
            DemoTopBar(
                title = format.title,
                onNavigateUp = onNavigateUp,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (format) {
            CoreFormat.Number -> NumberFormatDemo(
                modifier = Modifier.padding(innerPadding)
            )
            CoreFormat.Text -> TextFormatDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
