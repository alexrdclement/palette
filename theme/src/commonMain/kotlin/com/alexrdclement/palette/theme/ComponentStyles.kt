package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle

/**
 * Theme-resolved default [androidx.compose.runtime.Composable] styles for [com.alexrdclement.palette.components]
 * widgets. Clients pass these explicitly, e.g.
 * `MediaControlSheet(style = PaletteTheme.components.mediaControlSheetStyle)`.
 *
 * Each style is built from the current [PaletteTheme]; composite styles reuse their children's
 * resolved styles so a widget looks the same standalone or nested.
 */
object PaletteComponentStyles {

    val surfaceStyle: SurfaceStyle
        @Composable get() = SurfaceStyle(
            shape = PaletteTheme.shapeScheme.surface,
            color = PaletteTheme.colorScheme.surface,
            contentColor = PaletteTheme.colorScheme.contentColorFor(PaletteTheme.colorScheme.surface),
            indication = PaletteTheme.indication,
        )

    val playPauseButtonStyle: PlayPauseButtonStyle
        @Composable get() = PlayPauseButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.OnPrimary.toColor(),
                containerColor = ColorToken.Primary.toColor(),
                shape = ShapeToken.Primary.toShape(),
            ),
            iconColor = PaletteTheme.colorScheme.onPrimary,
        )

    val mediaItemArtworkStyle: MediaItemArtworkStyle
        @Composable get() = MediaItemArtworkStyle(
            fallbackTextStyle = PaletteTheme.styles.text.labelLarge,
        )

    val mediaControlBarStyle: MediaControlBarStyle
        @Composable get() = MediaControlBarStyle(
            titleStyle = PaletteTheme.styles.text.titleMedium,
            artistStyle = PaletteTheme.styles.text.bodyMedium,
            contentSpacing = PaletteTheme.spacing.small,
            artworkStyle = mediaItemArtworkStyle,
            playPauseButtonStyle = playPauseButtonStyle,
        )

    val mediaControlSheetStyle: MediaControlSheetStyle
        @Composable get() = MediaControlSheetStyle(
            controlBarStyle = mediaControlBarStyle,
        )
}

val PaletteTheme.components: PaletteComponentStyles
    get() = PaletteComponentStyles
