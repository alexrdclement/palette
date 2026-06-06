package com.alexrdclement.palette.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.auth.AuthButtonStyle
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.CheckboxStyle
import com.alexrdclement.palette.components.core.ChevronButtonStyle
import com.alexrdclement.palette.components.core.ChevronIconStyle
import com.alexrdclement.palette.components.core.DividerStyle
import com.alexrdclement.palette.components.core.SliderColors
import com.alexrdclement.palette.components.core.SliderStyle
import com.alexrdclement.palette.components.core.ProgressIndicatorStyle
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.components.core.withContentPadding
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerControlsStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.components.demo.DemoStyle
import com.alexrdclement.palette.components.demo.control.CharControlStyle
import com.alexrdclement.palette.components.demo.control.ColorControlStyle
import com.alexrdclement.palette.components.demo.control.ControlsStyle
import com.alexrdclement.palette.components.demo.control.DropdownControlStyle
import com.alexrdclement.palette.components.demo.control.DynamicListControlStyle
import com.alexrdclement.palette.components.demo.control.ExpandableHeaderStyle
import com.alexrdclement.palette.components.demo.control.SliderControlStyle
import com.alexrdclement.palette.components.demo.control.TextFieldControlStyle
import com.alexrdclement.palette.components.demo.control.ToggleControlStyle
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.FloatingActionStyle
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.components.layout.TopBarStyle
import com.alexrdclement.palette.components.layout.catalog.CatalogStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmButtonStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRowStyle
import com.alexrdclement.palette.components.layout.dialog.DialogContentStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.money.CurrencyAmountFieldStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.AuthButtonStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.toButtonStyleToken
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle

object PaletteComponentStyles {

    @Composable
    fun button(token: ButtonStyleToken = ButtonStyleToken.Primary): ButtonStyle =
        token.toComponentStyle()

    @Composable
    fun authButton(token: AuthButtonStyleToken = AuthButtonStyleToken.Secondary): AuthButtonStyle =
        AuthButtonStyle(
            buttonStyle = button(token.toButtonStyleToken()),
            textStyle = when (token) {
                AuthButtonStyleToken.Primary -> PaletteTheme.styles.text.labelLarge
                AuthButtonStyleToken.Secondary -> PaletteTheme.styles.text.labelSmall
                AuthButtonStyleToken.Tertiary -> PaletteTheme.styles.text.labelSmall
            },
        )

    val surface: SurfaceStyle
        @Composable get() = SurfaceStyle(
            shape = PaletteTheme.shapeScheme.surface,
            color = PaletteTheme.colorScheme.surface,
            contentColor = PaletteTheme.colorScheme.contentColorFor(PaletteTheme.colorScheme.surface),
            indication = PaletteTheme.indication,
        )

    val scaffold: ScaffoldStyle
        @Composable get() = ScaffoldStyle(
            surfaceStyle = surface,
        )

    val divider: DividerStyle
        @Composable get() = DividerStyle(
            color = PaletteTheme.colorScheme.outline,
        )

    val progressIndicator: ProgressIndicatorStyle
        @Composable get() = ProgressIndicatorStyle(
            textStyle = PaletteTheme.styles.text.bodyMedium,
        )

    val checkbox: CheckboxStyle
        @Composable get() = CheckboxStyle(
            buttonStyle = ButtonStyle.Default(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
            ),
            textStyle = PaletteTheme.styles.text.titleLarge,
        )

    val chevronButton: ChevronButtonStyle
        @Composable get() = ChevronButtonStyle(
            buttonStyle = ButtonStyle.Default(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
            ),
            iconColor = PaletteTheme.colorScheme.primary,
            contentPadding = PaddingValues(PaletteTheme.spacing.medium),
        )

    val chevronIcon: ChevronIconStyle
        @Composable get() = ChevronIconStyle(
            color = PaletteTheme.colorScheme.primary,
        )

    val colorDisplay: ColorDisplayStyle
        @Composable get() = ColorDisplayStyle(
            shape = PaletteTheme.shapeScheme.primary,
            borderStyle = BorderStyleToken.Primary.toComponentStyle(),
            buttonStyle = button(ButtonStyleToken.Secondary).withContentPadding(PaddingValues(0.dp)),
        )

    val colorPicker: ColorPickerStyle
        @Composable get() = ColorPickerStyle(
            spacing = PaletteTheme.spacing.medium,
            controlsStyle = ColorPickerControlsStyle(
                labelStyle = PaletteTheme.styles.text.labelLarge,
                sliderStyle = slider,
                spacing = PaletteTheme.spacing.small,
            ),
        )

    val confirmCancelButtonRow: ConfirmCancelButtonRowStyle
        @Composable get() = ConfirmCancelButtonRowStyle(
            buttonStyle = ConfirmButtonStyle(
                buttonStyle = button(ButtonStyleToken.Secondary),
                textStyle = PaletteTheme.styles.text.bodyMedium,
            ),
            spacing = PaletteTheme.spacing.medium,
        )

    val dialogContent: DialogContentStyle
        @Composable get() = DialogContentStyle(
            titleStyle = PaletteTheme.styles.text.titleLarge,
            messageStyle = PaletteTheme.styles.text.bodyLarge,
            borderStyle = BorderStyleToken.Surface.toComponentStyle(),
            spacing = PaletteTheme.spacing.medium,
            padding = PaletteTheme.spacing.large,
            titleBottomPadding = PaletteTheme.spacing.medium,
            messageBottomPadding = PaletteTheme.spacing.large,
        )

    val slider: SliderStyle
        @Composable get() = SliderStyle(
            colors = SliderColors(
                trackColor = PaletteTheme.colorScheme.primary,
                thumbColor = PaletteTheme.colorScheme.primary,
                thumbPointColor = PaletteTheme.colorScheme.primary,
                thumbBackgroundColor = PaletteTheme.colorScheme.surface,
            ),
        )

    val floatingAction: FloatingActionStyle
        @Composable get() = FloatingActionStyle(
            spacing = PaletteTheme.spacing.small,
        )

    val topBar: TopBarStyle
        @Composable get() = TopBarStyle(
            spacing = PaletteTheme.spacing.small,
        )

    val textField: TextFieldStyle
        @Composable get() = TextFieldStyle(
            textStyle = PaletteTheme.styles.text.bodyMedium,
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
            buttonStyle = ButtonStyle.Default(
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
            itemTextStyle = PaletteTheme.styles.text.bodyMedium,
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
            buttonStyle = ButtonStyle.Default(
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
            buttonStyle = button(ButtonStyleToken.Secondary),
            textFieldStyle = textField,
            borderColor = PaletteTheme.colorScheme.outline,
            colorPickerStyle = colorPicker,
            surfaceStyle = surface,
            dividerStyle = divider,
            colorControl = ColorControlStyle(
                spacing = PaletteTheme.spacing.medium,
                contentSpacing = PaletteTheme.spacing.small,
                dialogPadding = PaletteTheme.spacing.large,
            ),
            sliderControl = SliderControlStyle(
                spacing = PaletteTheme.spacing.small,
            ),
            toggleControl = ToggleControlStyle(
                spacing = PaletteTheme.spacing.small,
                checkboxStyle = checkbox,
            ),
            charControl = CharControlStyle(
                spacing = PaletteTheme.spacing.small,
                verticalPadding = PaletteTheme.spacing.small,
            ),
            textFieldControl = TextFieldControlStyle(
                spacing = PaletteTheme.spacing.small,
                verticalPadding = PaletteTheme.spacing.small,
            ),
            dropdownControl = DropdownControlStyle(
                labelSpacing = PaletteTheme.spacing.small,
                rowSpacing = PaletteTheme.spacing.medium,
            ),
            expandableHeader = ExpandableHeaderStyle(
                spacing = PaletteTheme.spacing.small,
                labelPadding = PaletteTheme.spacing.xs,
                chevronIconStyle = chevronIcon,
            ),
            dynamicListControl = DynamicListControlStyle(
                spacing = PaletteTheme.spacing.medium,
                itemSpacing = PaletteTheme.spacing.small,
                itemControlSpacing = PaletteTheme.spacing.xs,
                indent = PaletteTheme.spacing.medium,
            ),
            controls = ControlsStyle(
                spacing = PaletteTheme.spacing.medium,
                contentPadding = PaletteTheme.spacing.small,
                rowSpacing = PaletteTheme.spacing.small,
                indent = PaletteTheme.spacing.medium,
            ),
        )
}

val PaletteTheme.components: PaletteComponentStyles
    get() = PaletteComponentStyles
