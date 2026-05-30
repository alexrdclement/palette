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
import com.alexrdclement.palette.components.PlayPauseButtonContentDescriptionPaused
import com.alexrdclement.palette.components.PlayPauseButtonContentDescriptionPlaying
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.style.PaletteStyle
import com.alexrdclement.palette.theme.styles.PaletteButtonStyleScheme
import com.alexrdclement.palette.theme.styles.toRenderStyle

@Composable
fun PlayPauseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    isPlaying: Boolean = false,
    isEnabled: Boolean = true,
    style: PaletteStyle = PlayPauseButtonDefaults.style,
    contentColor: Color = PaletteTheme.colorScheme.onPrimary,
) {
    Button(
        onClick = onClick,
        onLongClick = onLongClick,
        style = style,
        contentColor = contentColor,
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
            colorFilter = ColorFilter.tint(contentColor),
            modifier = Modifier
                .fillMaxSize()
                .padding(shapePadding)
        )
    }
}

object PlayPauseButtonDefaults {
    val style: PaletteStyle get() = PaletteButtonStyleScheme.primary.toRenderStyle()
}

@Preview
@Composable
private fun PlayPreview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isEnabled: Boolean,
) {
    PaletteTheme {
        PlayPauseButton(
            isEnabled = isEnabled,
            isPlaying = false,
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun PausePreview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isEnabled: Boolean,
) {
    PaletteTheme {
        PlayPauseButton(
            isEnabled = isEnabled,
            isPlaying = true,
            onClick = {},
        )
    }
}
