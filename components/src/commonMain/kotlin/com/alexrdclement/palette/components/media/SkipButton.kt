package com.alexrdclement.palette.components.media

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.withContentPadding
import com.alexrdclement.palette.components.core.ButtonStyle

@Composable
fun SkipButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle.Default(),
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(6.dp),
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        style = style.withContentPadding(contentPadding),
        modifier = modifier.aspectRatio(1f),
    ) { shapePadding ->
        SkipIcon(
            modifier = Modifier
                .fillMaxSize()
                .padding(shapePadding),
        )
    }
}

@Composable
fun SkipIcon(
    modifier: Modifier = Modifier,
) {
    val color = LocalContentColor.current
    Canvas(modifier = modifier) {
        val barWidth = size.width * 0.15f
        val gap = size.width * 0.08f

        drawRect(
            color = color,
            topLeft = Offset(size.width - barWidth, 0f),
            size = Size(barWidth, size.height),
        )

        val path = Path().apply {
            moveTo(0f, 0f)                                         // top left (base)
            lineTo(0f, size.height)                               // bottom left (base)
            lineTo(size.width - barWidth - gap, size.height / 2f) // right tip
            close()
        }
        drawPath(path, color = color)
    }
}

@Preview
@Composable
private fun SkipButtonPreview() {
    SkipButton(onClick = {})
}

@Preview
@Composable
private fun SkipIconPreview() {
    SkipIcon()
}
