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
    modifierType: DemoModifier,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = modifierType.name,
                onNavigateBack = onNavigateBack,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        val modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        when (modifierType) {
            DemoModifier.ColorInvert -> ColorInvertDemo(
                modifier = modifier,
            )
            DemoModifier.ColorSplit -> ColorSplitDemo(
                modifier = modifier,
            )
            DemoModifier.Noise -> NoiseDemo(
                modifier = modifier,
            )
            DemoModifier.Pixelate -> PixelateDemo(
                modifier = modifier,
            )
            DemoModifier.Warp -> WarpDemo(
                modifier = modifier,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PalettePreview {
        ModifierScreen(
            modifierType = DemoModifier.ColorSplit,
            onNavigateBack = {},
            onThemeClick = {},
        )
    }
}
