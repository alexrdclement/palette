package com.alexrdclement.palette.theme.component.media

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle
import com.alexrdclement.palette.components.media.SkipButtonStyle
import com.alexrdclement.palette.components.media.SkipIconStyle
import com.alexrdclement.palette.theme.semantic.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.component.core.CoreStyles
import com.alexrdclement.palette.theme.component.core.TextStyles
import com.alexrdclement.palette.theme.semantic.ShapeToken
import com.alexrdclement.palette.theme.semantic.toColor
import com.alexrdclement.palette.theme.semantic.toShape

object MediaStyles {

    val playPauseButton: PlayPauseButtonStyle
        @Composable get() = PlayPauseButtonStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Primary.toColor(),
                shape = ShapeToken.Primary.toShape(),
                contentPadding = PaddingValues(2.dp),
                disabledContentAlpha = PaletteTheme.semantic.color.disabledContentAlpha,
                disabledContainerAlpha = PaletteTheme.semantic.color.disabledContainerAlpha,
                indication = PaletteTheme.semantic.indication,
            ),
            iconColor = PaletteTheme.semantic.color.onPrimary,
        )

    val skipButton: SkipButtonStyle
        @Composable get() = SkipButtonStyle(
            buttonStyle = CoreStyles.button.secondary.copy(
                contentPadding = PaddingValues(6.dp),
            ),
            iconStyle = SkipIconStyle(
                color = PaletteTheme.semantic.color.secondary,
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
            contentSpacing = PaletteTheme.semantic.spacing.small,
            artworkStyle = mediaItemArtwork,
            playPauseButtonStyle = playPauseButton,
            surfaceStyle = CoreStyles.surface.default,
            maxContentSize = DpSize(width = Dp.Infinity, height = 600.dp),
        )

    val mediaControlSheet: MediaControlSheetStyle
        @Composable get() = MediaControlSheetStyle(
            controlBarStyle = mediaControlBar,
        )
}
