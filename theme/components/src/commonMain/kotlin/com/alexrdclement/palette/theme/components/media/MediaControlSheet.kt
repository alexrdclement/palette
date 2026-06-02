package com.alexrdclement.palette.theme.components.media

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.layout.PeekSheetState
import com.alexrdclement.palette.components.layout.rememberPeekSheetState
import com.alexrdclement.palette.components.media.model.MediaItem
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.components.media.MediaControlSheet as ComponentMediaControlSheet

@Composable
fun MediaControlSheet(
    mediaItem: MediaItem,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    onControlBarClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: PeekSheetState = rememberPeekSheetState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    minContentSize: DpSize = DpSize(64.dp, 64.dp),
    maxContentSize: DpSize = DpSize(Dp.Infinity, 600.dp),
    aboveControlBar: @Composable () -> Unit = {},
    belowControlBar: @Composable () -> Unit = {},
) {
    ComponentMediaControlSheet(
        mediaItem = mediaItem,
        isPlaying = isPlaying,
        onPlayPauseClick = onPlayPauseClick,
        onControlBarClick = onControlBarClick,
        modifier = modifier,
        state = state,
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
        aboveControlBar = aboveControlBar,
        belowControlBar = belowControlBar,
    )
}
