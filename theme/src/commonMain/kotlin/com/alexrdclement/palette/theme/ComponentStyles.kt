package com.alexrdclement.palette.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.CheckboxStyle
import com.alexrdclement.palette.components.core.ChevronButtonStyle
import com.alexrdclement.palette.components.core.DividerStyle
import com.alexrdclement.palette.components.core.SliderColors
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.components.demo.DemoStyle
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.catalog.CatalogStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.money.CurrencyAmountFieldStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle

/**
 * Theme-resolved default [androidx.compose.runtime.Composable] styles for [com.alexrdclement.palette.components]
 * widgets. Clients pass these explicitly, e.g.
 * `MediaControlSheet(style = PaletteTheme.components.mediaControlSheet)`.
 *
 * Each style is built from the current [PaletteTheme]; composite styles reuse their children's
 * resolved styles so a widget looks the same standalone or nested.
 */
object PaletteComponentStyles {

    /** Resolved [ButtonStyle] for the given [ButtonStyleToken] variant. */
    @Composable
    fun button(token: ButtonStyleToken = ButtonStyleToken.Primary): ButtonStyle =
        token.toComponentStyle()

    val surface: SurfaceStyle
        @Composable get() = SurfaceStyle(
            shape = PaletteTheme.shapeScheme.surface,
            color = PaletteTheme.colorScheme.surface,
            contentColor = PaletteTheme.colorScheme.contentColorFor(PaletteTheme.colorScheme.surface),
            indication = PaletteTheme.indication,
        )

    val divider: DividerStyle
        @Composable get() = DividerStyle(
            color = PaletteTheme.colorScheme.outline,
        )

    val checkbox: CheckboxStyle
        @Composable get() = CheckboxStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
            ),
            textStyle = PaletteTheme.styles.text.titleLarge,
        )

    val chevronButton: ChevronButtonStyle
        @Composable get() = ChevronButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
            ),
            iconColor = PaletteTheme.colorScheme.primary,
            contentPadding = PaddingValues(PaletteTheme.spacing.medium),
        )

    val colorDisplay: ColorDisplayStyle
        @Composable get() = ColorDisplayStyle(
            shape = PaletteTheme.shapeScheme.primary,
            borderStyle = BorderStyleToken.Primary.toComponentStyle(),
        )

    val colorPicker: ColorPickerStyle
        @Composable get() = ColorPickerStyle(
            spacing = PaletteTheme.spacing.medium,
            labelStyle = PaletteTheme.styles.text.labelLarge,
        )

    val sliderColors: SliderColors
        @Composable get() = SliderColors(
            trackColor = PaletteTheme.colorScheme.primary,
            thumbColor = PaletteTheme.colorScheme.primary,
            thumbPointColor = PaletteTheme.colorScheme.primary,
            thumbBackgroundColor = PaletteTheme.colorScheme.surface,
        )

    val textField: TextFieldStyle
        @Composable get() = TextFieldStyle(
            cursorBrush = SolidColor(PaletteTheme.colorScheme.primary),
            borderStroke = BorderStroke(1.dp, PaletteTheme.colorScheme.outline),
            contentPadding = PaletteTheme.spacing.small,
        )

    val currencyAmountField: CurrencyAmountFieldStyle
        @Composable get() = CurrencyAmountFieldStyle(
            textStyle = PaletteTheme.styles.text.headline,
            placeholderColor = PaletteTheme.colorScheme.primary.copy(alpha = 0.5f),
            cursorBrush = SolidColor(PaletteTheme.colorScheme.primary),
            padding = PaletteTheme.spacing.medium,
            spacing = PaletteTheme.spacing.small,
        )

    val backNavigationButton: BackNavigationButtonStyle
        @Composable get() = BackNavigationButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
            ),
            contentPadding = PaddingValues(PaletteTheme.spacing.medium),
            iconColor = PaletteTheme.colorScheme.primary,
        )

    val catalog: CatalogStyle
        @Composable get() = CatalogStyle(
            itemSpacing = PaletteTheme.spacing.medium,
            itemStyle = ButtonStyleToken.Secondary.toComponentStyle(),
        )

    val boxWithLabel: BoxWithLabelStyle
        @Composable get() = BoxWithLabelStyle(
            spacing = PaletteTheme.spacing.small,
            labelPadding = PaletteTheme.spacing.xs,
            labelStyle = PaletteTheme.styles.text.labelSmall,
            borderColor = PaletteTheme.colorScheme.outline,
        )

    val playPauseButton: PlayPauseButtonStyle
        @Composable get() = PlayPauseButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.OnPrimary.toColor(),
                containerColor = ColorToken.Primary.toColor(),
                shape = ShapeToken.Primary.toShape(),
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

    val demo: DemoStyle
        @Composable get() = DemoStyle(
            labelStyle = PaletteTheme.styles.text.labelLarge,
            headerStyle = PaletteTheme.styles.text.labelSmall,
            fieldTextStyle = PaletteTheme.styles.text.bodyMedium,
            buttonStyle = button(ButtonStyleToken.Secondary),
            textFieldStyle = textField,
            borderColor = PaletteTheme.colorScheme.outline,
            colorPickerStyle = colorPicker,
            surfaceStyle = surface,
        )
}

val PaletteTheme.components: PaletteComponentStyles
    get() = PaletteComponentStyles
