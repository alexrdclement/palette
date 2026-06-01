package com.alexrdclement.palette.components.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.BorderStyle
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.components.core.border
import com.alexrdclement.palette.components.core.toComposeShape

@Composable
fun ColorDisplay(
    color: Color,
    modifier: Modifier = Modifier,
    shape: Shape = Shape.Circle,
    borderStyle: BorderStyle? = null,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(color = color, shape = shape.toComposeShape())
            .then(if (borderStyle != null) Modifier.border(style = borderStyle) else Modifier),
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
