package com.alexrdclement.palette.theme.components.media

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.media.model.MediaItem
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.components.media.MediaControlBar as ComponentMediaControlBar

@Composable
fun MediaControlBar(
    mediaItem: MediaItem,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    minContentSize: DpSize = DpSize(width = 64.dp, height = 64.dp),
    maxContentSize: DpSize = DpSize(width = Dp.Infinity, height = Dp.Infinity),
    progress: () -> Float = { 0f },
    onClick: () -> Unit = {},
    stateDescription: String? = null,
) {
    ComponentMediaControlBar(
        mediaItem = mediaItem,
        isPlaying = isPlaying,
        onPlayPauseClick = onPlayPauseClick,
        modifier = modifier,
        contentPadding = contentPadding,
        minContentSize = minContentSize,
        maxContentSize = maxContentSize,
        titleStyle = PaletteTheme.styles.text.titleMedium,
        artistStyle = PaletteTheme.styles.text.bodyMedium,
        contentSpacing = PaletteTheme.spacing.small,
        playPauseButtonStyle = ButtonStyle(
            contentColor = ColorToken.OnPrimary.toColor(),
            containerColor = ColorToken.Primary.toColor(),
            shape = ShapeToken.Primary.toShape(),
        ),
        playPauseIconColor = PaletteTheme.colorScheme.onPrimary,
        progress = progress,
        onClick = onClick,
        stateDescription = stateDescription,
    )
}
