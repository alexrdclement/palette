package com.alexrdclement.palette.components.media

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.MediaControlBarStateDescriptionExpanded
import com.alexrdclement.palette.components.MediaControlBarStateDescriptionPartiallyExpanded
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.layout.PeekSheet
import com.alexrdclement.palette.components.layout.PeekSheetAnchor
import com.alexrdclement.palette.components.layout.PeekSheetState
import com.alexrdclement.palette.components.layout.rememberPeekSheetState
import com.alexrdclement.palette.components.media.model.Artist
import com.alexrdclement.palette.components.media.model.MediaItem
import com.alexrdclement.palette.components.util.calculateHorizontalPaddingValues
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.coroutines.launch

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
    PeekSheet(
        peekHeight = minContentSize.height,
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        above = aboveControlBar,
        bar = { progress ->
            MediaControlBar(
                mediaItem = mediaItem,
                isPlaying = isPlaying,
                onPlayPauseClick = onPlayPauseClick,
                onClick = onControlBarClick,
                progress = progress,
                contentPadding = contentPadding.calculateHorizontalPaddingValues(),
                minContentSize = minContentSize,
                maxContentSize = maxContentSize,
                stateDescription = when (state.currentValue) {
                    PeekSheetAnchor.Peek -> MediaControlBarStateDescriptionPartiallyExpanded
                    PeekSheetAnchor.Expanded -> MediaControlBarStateDescriptionExpanded
                },
                modifier = Modifier.fillMaxWidth(),
            )
        },
        content = belowControlBar,
    )
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        val state = rememberPeekSheetState(initialValue = PeekSheetAnchor.Peek)
        val coroutineScope = rememberCoroutineScope()
        var isPlaying by remember { mutableStateOf(false) }
        Surface {
            MediaControlSheet(
                mediaItem = MediaItem(
                    artworkThumbnailUrl = null,
                    artworkLargeUrl = null,
                    title = "Title",
                    artists = listOf(Artist("Artist 1"), Artist("Artist 2")),
                ),
                isPlaying = isPlaying,
                onPlayPauseClick = { isPlaying = !isPlaying },
                onControlBarClick = {
                    coroutineScope.launch {
                        if (state.isExpanded) state.peek() else state.expand()
                    }
                },
                state = state,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text("Content")
                }
            }
        }
    }
}
