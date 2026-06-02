package com.alexrdclement.palette.theme.components.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape
import com.alexrdclement.palette.components.media.PlayPauseButton as ComponentPlayPauseButton

@Composable
fun PlayPauseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    isPlaying: Boolean = false,
    isEnabled: Boolean = true,
) {
    ComponentPlayPauseButton(
        onClick = onClick,
        modifier = modifier,
        onLongClick = onLongClick,
        isPlaying = isPlaying,
        isEnabled = isEnabled,
        style = ButtonStyle(
            contentColor = ColorToken.OnPrimary.toColor(),
            containerColor = ColorToken.Primary.toColor(),
            shape = ShapeToken.Primary.toShape(),
        ),
        iconColor = PaletteTheme.colorScheme.onPrimary,
    )
}
