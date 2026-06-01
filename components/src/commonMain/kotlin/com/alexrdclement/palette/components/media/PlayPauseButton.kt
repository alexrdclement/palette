package com.alexrdclement.palette.components.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.PlayPauseButtonContentDescriptionPaused
import com.alexrdclement.palette.components.PlayPauseButtonContentDescriptionPlaying
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider

@Composable
fun PlayPauseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    isPlaying: Boolean = false,
    isEnabled: Boolean = true,
    style: ButtonStyle = ButtonStyle(),
    iconColor: Color = Color.Unspecified,
) {
    Button(
        onClick = onClick,
        onLongClick = onLongClick,
        style = style,
        enabled = isEnabled,
        contentPadding = PaddingValues(vertical = 2.dp, horizontal = 2.dp),
        modifier = modifier
            .aspectRatio(1f)
    ) { shapePadding ->
        Image(
            imageVector = if (isPlaying) Icons.Default.Close else Icons.Default.PlayArrow,
            contentDescription = if (isPlaying) {
                PlayPauseButtonContentDescriptionPlaying
            } else PlayPauseButtonContentDescriptionPaused,
            colorFilter = ColorFilter.tint(iconColor.takeIf { it != Color.Unspecified } ?: LocalContentColor.current),
            modifier = Modifier
                .fillMaxSize()
                .padding(shapePadding)
        )
    }
}

@Preview
@Composable
private fun PlayPreview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isEnabled: Boolean,
) {
    PlayPauseButton(
        isEnabled = isEnabled,
        isPlaying = false,
        onClick = {},
    )
}

@Preview
@Composable
private fun PausePreview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isEnabled: Boolean,
) {
    PlayPauseButton(
        isEnabled = isEnabled,
        isPlaying = true,
        onClick = {},
    )
}
