package com.alexrdclement.palette.components.core

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class IconStyle(
    val size: IconSize = IconSize.Fill,
    val color: Color = Color.Unspecified,
)

@Composable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    style: IconStyle = IconStyle(),
) {
    Image(
        imageVector = imageVector,
        contentDescription = contentDescription,
        colorFilter = if (style.color.isSpecified) ColorFilter.tint(style.color) else null,
        modifier = modifier.iconSize(style.size),
    )
}

@Preview
@Composable
private fun IconPreview() {
    Icon(
        imageVector = Icons.Default.Star,
        contentDescription = null,
        style = IconStyle(size = IconSize.Fixed(48.dp)),
    )
}
