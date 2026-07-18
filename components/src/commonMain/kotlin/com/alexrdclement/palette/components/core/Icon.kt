package com.alexrdclement.palette.components.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

/**
 * How an icon sizes itself within the space it is given. Decoupled from the container it sits in
 * (a button, a row, nothing) so an icon carries its own sizing intent.
 *
 * - [Fixed] — an absolute size. The mature default for standard icon buttons; prefer a size token.
 * - [Scale] — a fraction of the available space. For icons that should track a responsive
 *   container (e.g. a media transport control that grows with its player).
 * - [Fill] — all available space. For adornment icons sized by their slot (e.g. a list-row chevron).
 */
sealed interface IconSize {
    data class Fixed(val size: Dp) : IconSize
    data class Scale(val fraction: Float) : IconSize
    data object Fill : IconSize
}

fun Modifier.iconSize(size: IconSize): Modifier = when (size) {
    is IconSize.Fixed -> this.size(size.size)
    is IconSize.Scale -> this.fillMaxSize(size.fraction)
    IconSize.Fill -> this.fillMaxSize()
}

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
