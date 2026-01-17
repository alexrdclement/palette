package com.alexrdclement.palette.app.demo.formats.core

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.core.navigation.CoreFormat
import com.alexrdclement.palette.components.layout.Scaffold

@Composable
fun CoreFormatScreen(
    format: CoreFormat,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = format.title,
                onNavigateBack = onNavigateBack,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (format) {
            CoreFormat.Number -> NumberFormatDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
