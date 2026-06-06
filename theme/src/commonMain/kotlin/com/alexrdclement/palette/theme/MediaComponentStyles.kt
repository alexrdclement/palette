package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle

/** Resolved styles for [com.alexrdclement.palette.components.media]; surfaced via [PaletteTheme.components]. */
object MediaComponentStyles {

    val playPauseButton: PlayPauseButtonStyle
        @Composable get() = PlayPauseButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.OnPrimary.toColor(),
                containerColor = ColorToken.Primary.toColor(),
                shape = ShapeToken.Primary.toShape(),
                indication = PaletteTheme.indication,
            ),
            iconColor = PaletteTheme.colorScheme.onPrimary,
        )

    val mediaItemArtwork: MediaItemArtworkStyle
        @Composable get() = MediaItemArtworkStyle(
            fallbackTextStyle = PaletteTheme.styles.text.labelLarge,
        )

    val mediaControlBar: MediaControlBarStyle
        @Composable get() = MediaControlBarStyle(
            titleStyle = PaletteTheme.styles.text.titleMedium,
            artistStyle = PaletteTheme.styles.text.bodyMedium,
            contentSpacing = PaletteTheme.spacing.small,
            artworkStyle = mediaItemArtwork,
            playPauseButtonStyle = playPauseButton,
        )

    val mediaControlSheet: MediaControlSheetStyle
        @Composable get() = MediaControlSheetStyle(
            controlBarStyle = mediaControlBar,
        )
}
