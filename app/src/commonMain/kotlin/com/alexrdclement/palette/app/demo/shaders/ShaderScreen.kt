package com.alexrdclement.palette.app.demo.shaders

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.preview.PalettePreview
import com.alexrdclement.palette.components.layout.Scaffold
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ShaderScreen(
    onNavigateBack: () -> Unit,
    onConfigureClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Shaders",
                onNavigateBack = onNavigateBack,
                onConfigureClick = onConfigureClick,
            )
        },
    ) { innerPadding ->
        ShaderDemo(
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
        ShaderScreen(
            onNavigateBack = {},
            onConfigureClick = {},
        )
    }
}
