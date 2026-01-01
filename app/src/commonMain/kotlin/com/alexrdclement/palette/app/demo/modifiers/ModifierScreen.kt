package com.alexrdclement.palette.app.demo.modifiers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.preview.PalettePreview
import com.alexrdclement.palette.components.layout.Scaffold
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ModifierScreen(
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Modifiers",
                onNavigateBack = onNavigateBack,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        ModifierDemo(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PalettePreview {
        ModifierScreen(
            onNavigateBack = {},
            onThemeClick = {},
        )
    }
}
