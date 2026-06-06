package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.auth.AuthButtonStyle
import com.alexrdclement.palette.components.color.ColorDisplayStyle
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.CheckboxStyle
import com.alexrdclement.palette.components.core.ChevronButtonStyle
import com.alexrdclement.palette.components.core.ChevronIconStyle
import com.alexrdclement.palette.components.core.DividerStyle
import com.alexrdclement.palette.components.core.ProgressIndicatorStyle
import com.alexrdclement.palette.components.core.SliderStyle
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.components.demo.DemoStyle
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.FloatingActionStyle
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.components.layout.TopBarStyle
import com.alexrdclement.palette.components.layout.catalog.CatalogStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRowStyle
import com.alexrdclement.palette.components.layout.dialog.DialogContentStyle
import com.alexrdclement.palette.components.media.MediaControlBarStyle
import com.alexrdclement.palette.components.media.MediaControlSheetStyle
import com.alexrdclement.palette.components.media.MediaItemArtworkStyle
import com.alexrdclement.palette.components.media.PlayPauseButtonStyle
import com.alexrdclement.palette.components.menu.DropdownMenuStyle
import com.alexrdclement.palette.components.money.CurrencyAmountFieldStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.styles.AuthButtonStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

/**
 * Theme-resolved default [androidx.compose.runtime.Composable] styles for
 * [com.alexrdclement.palette.components] widgets. Clients pass these explicitly, e.g.
 * `MediaControlSheet(style = PaletteTheme.components.mediaControlSheet)`.
 *
 * Each style is built from the current [PaletteTheme]; composite styles reuse their children's
 * resolved styles so a widget looks the same standalone or nested.
 *
 * This is a flat facade: every accessor delegates to a per-package object ([CoreComponentStyles],
 * [LayoutComponentStyles], …) where the actual style is built. Add a new component style to the
 * relevant package object and expose a one-line delegate here.
 */
object PaletteComponentStyles {

    // core
    @Composable
    fun button(token: ButtonStyleToken = ButtonStyleToken.Primary): ButtonStyle =
        CoreComponentStyles.button(token)
    val surface: SurfaceStyle @Composable get() = CoreComponentStyles.surface
    val divider: DividerStyle @Composable get() = CoreComponentStyles.divider
    val progressIndicator: ProgressIndicatorStyle @Composable get() = CoreComponentStyles.progressIndicator
    val checkbox: CheckboxStyle @Composable get() = CoreComponentStyles.checkbox
    val chevronButton: ChevronButtonStyle @Composable get() = CoreComponentStyles.chevronButton
    val chevronIcon: ChevronIconStyle @Composable get() = CoreComponentStyles.chevronIcon
    val slider: SliderStyle @Composable get() = CoreComponentStyles.slider
    val textField: TextFieldStyle @Composable get() = CoreComponentStyles.textField

    // auth
    @Composable
    fun authButton(token: AuthButtonStyleToken = AuthButtonStyleToken.Secondary): AuthButtonStyle =
        AuthComponentStyles.authButton(token)

    // color
    val colorDisplay: ColorDisplayStyle @Composable get() = ColorComponentStyles.colorDisplay
    val colorPicker: ColorPickerStyle @Composable get() = ColorComponentStyles.colorPicker

    // layout
    val scaffold: ScaffoldStyle @Composable get() = LayoutComponentStyles.scaffold
    val floatingAction: FloatingActionStyle @Composable get() = LayoutComponentStyles.floatingAction
    val topBar: TopBarStyle @Composable get() = LayoutComponentStyles.topBar
    val boxWithLabel: BoxWithLabelStyle @Composable get() = LayoutComponentStyles.boxWithLabel
    val catalog: CatalogStyle @Composable get() = LayoutComponentStyles.catalog
    val confirmCancelButtonRow: ConfirmCancelButtonRowStyle
        @Composable get() = LayoutComponentStyles.confirmCancelButtonRow
    val dialogContent: DialogContentStyle @Composable get() = LayoutComponentStyles.dialogContent

    // menu
    val dropdownMenu: DropdownMenuStyle @Composable get() = MenuComponentStyles.dropdownMenu

    // media
    val playPauseButton: PlayPauseButtonStyle @Composable get() = MediaComponentStyles.playPauseButton
    val mediaItemArtwork: MediaItemArtworkStyle @Composable get() = MediaComponentStyles.mediaItemArtwork
    val mediaControlBar: MediaControlBarStyle @Composable get() = MediaComponentStyles.mediaControlBar
    val mediaControlSheet: MediaControlSheetStyle @Composable get() = MediaComponentStyles.mediaControlSheet

    // money
    val currencyAmountField: CurrencyAmountFieldStyle
        @Composable get() = MoneyComponentStyles.currencyAmountField

    // navigation
    val backNavigationButton: BackNavigationButtonStyle
        @Composable get() = NavigationComponentStyles.backNavigationButton

    // demo
    val demo: DemoStyle @Composable get() = DemoComponentStyles.demo
}

val PaletteTheme.components: PaletteComponentStyles
    get() = PaletteComponentStyles
