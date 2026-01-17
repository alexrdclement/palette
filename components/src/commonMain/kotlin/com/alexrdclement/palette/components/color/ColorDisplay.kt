package com.alexrdclement.palette.components.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.modifiers.border
import com.alexrdclement.palette.theme.toComposeShape
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ColorDisplay(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(color = color, shape = PaletteTheme.shapeScheme.primary.toComposeShape())
            .border(style = PaletteTheme.styles.border.primary),
    )
}

@Preview
@Composable
fun ColorDisplayPreview() {
    ColorDisplay(
        color = Color.Red,
        modifier = Modifier
            .fillMaxSize(),
    )
}
