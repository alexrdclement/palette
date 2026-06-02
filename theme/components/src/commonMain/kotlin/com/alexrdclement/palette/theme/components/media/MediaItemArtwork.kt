package com.alexrdclement.palette.theme.components.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.media.MediaItemArtwork as ComponentMediaItemArtwork

@Composable
fun MediaItemArtwork(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    ComponentMediaItemArtwork(
        imageUrl = imageUrl,
        modifier = modifier,
        isEnabled = isEnabled,
        fallbackTextStyle = PaletteTheme.styles.text.labelLarge,
    )
}
