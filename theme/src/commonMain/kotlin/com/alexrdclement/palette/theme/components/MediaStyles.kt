package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle
import com.alexrdclement.palette.components.media.SkipButtonStyle
import com.alexrdclement.palette.components.media.SkipIconStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape

object MediaStyles {

    val playPauseButton: PlayPauseButtonStyle
        @Composable get() = PlayPauseButtonStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Primary.toColor(),
                shape = ShapeToken.Primary.toShape(),
                indication = PaletteTheme.indication,
            ),
            iconColor = PaletteTheme.colorScheme.onPrimary,
        )

    val skipButton: SkipButtonStyle
        @Composable get() = SkipButtonStyle(
            buttonStyle = CoreStyles.button.secondary,
            iconStyle = SkipIconStyle(
                color = PaletteTheme.colorScheme.secondary,
            ),
        )

    val mediaItemArtwork: MediaItemArtworkStyle
        @Composable get() = MediaItemArtworkStyle(
            fallbackTextStyle = TextStyles.labelLarge,
        )

    val mediaControlBar: MediaControlBarStyle
        @Composable get() = MediaControlBarStyle(
            titleStyle = TextStyles.titleMedium,
            artistStyle = TextStyles.bodyMedium,
            contentSpacing = PaletteTheme.spacing.small,
            artworkStyle = mediaItemArtwork,
            playPauseButtonStyle = playPauseButton,
            surfaceStyle = CoreStyles.surface.default,
        )

    val mediaControlSheet: MediaControlSheetStyle
        @Composable get() = MediaControlSheetStyle(
            controlBarStyle = mediaControlBar,
        )
}
