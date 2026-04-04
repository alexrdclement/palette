package com.alexrdclement.palette.components.demo.media

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.media.PlayPauseButton
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PlayPauseButtonDemo(
    modifier: Modifier = Modifier,
) {
    var isPlaying by remember { mutableStateOf(false) }
    var isEnabled by remember { mutableStateOf(true) }

    val controls = persistentListOf(
        Control.Toggle(
            name = "Playing",
            value = { isPlaying },
            onValueChange = { isPlaying = it },
        ),
        Control.Toggle(
            name = "Enabled",
            value = { isEnabled },
            onValueChange = { isEnabled = it },
        ),
    )

    Demo(
        controls = controls,
        modifier = modifier
            .fillMaxSize()
    ) {
        PlayPauseButtonDemo(
            isPlaying = isPlaying,
            isEnabled = isEnabled,
            onPlayPauseClick = { isPlaying = !isPlaying },
        )
    }
}

@Composable
fun DemoScope.PlayPauseButtonDemo(
    isPlaying: Boolean,
    isEnabled: Boolean,
    onPlayPauseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PlayPauseButton(
        onClick = onPlayPauseClick,
        isPlaying = isPlaying,
        isEnabled = isEnabled,
        modifier = modifier
            .size(52.dp)
            .align(Alignment.Center),
    )
}
