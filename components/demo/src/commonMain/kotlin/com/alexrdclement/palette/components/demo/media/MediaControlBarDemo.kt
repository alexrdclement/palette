package com.alexrdclement.palette.components.demo.media

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.media.MediaControlBar
import com.alexrdclement.palette.components.media.model.Artist
import com.alexrdclement.palette.components.media.model.MediaItem
import kotlinx.collections.immutable.persistentListOf

private val PreviewMediaItem = MediaItem(
    artworkThumbnailUrl = null,
    artworkLargeUrl = null,
    title = "Title",
    artists = listOf(Artist("Artist 1"), Artist("Artist 2")),
)

@Composable
fun MediaControlBarDemo(
    modifier: Modifier = Modifier,
) {
    var progress by remember { mutableFloatStateOf(0f) }
    var isPlaying by remember { mutableStateOf(false) }

    val controls = persistentListOf(
        Control.Slider(
            name = "Progress",
            value = { progress },
            onValueChange = { progress = it },
        ),
        Control.Toggle(
            name = "Playing",
            value = { isPlaying },
            onValueChange = { isPlaying = it },
        ),
    )

    Demo(
        controls = controls,
        modifier = modifier
            .fillMaxSize()
    ) {
        MediaControlBarDemo(
            progress = { progress },
            isPlaying = isPlaying,
            onPlayPauseClick = { isPlaying = !isPlaying },
        )
    }
}

@Composable
fun DemoScope.MediaControlBarDemo(
    progress: () -> Float,
    isPlaying: Boolean,
    onPlayPauseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val maxHeight = constraints.maxHeight
        MediaControlBar(
            mediaItem = PreviewMediaItem,
            isPlaying = isPlaying,
            onPlayPauseClick = onPlayPauseClick,
            progress = progress,
            maxContentSize = DpSize(
                width = maxWidth,
                height = with(LocalDensity.current) { maxHeight.toDp() / 2f },
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
