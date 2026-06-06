package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.FloatingActionStyle
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.components.layout.TopBarStyle
import com.alexrdclement.palette.components.layout.catalog.CatalogStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmButtonStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRowStyle
import com.alexrdclement.palette.components.layout.dialog.DialogContentStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

/** Resolved styles for [com.alexrdclement.palette.components.layout]; surfaced via [PaletteTheme.components]. */
object LayoutComponentStyles {

    val scaffold: ScaffoldStyle
        @Composable get() = ScaffoldStyle(
            surfaceStyle = CoreComponentStyles.surface,
        )

    val floatingAction: FloatingActionStyle
        @Composable get() = FloatingActionStyle(
            spacing = PaletteTheme.spacing.small,
        )

    val topBar: TopBarStyle
        @Composable get() = TopBarStyle(
            spacing = PaletteTheme.spacing.small,
        )

    val boxWithLabel: BoxWithLabelStyle
        @Composable get() = BoxWithLabelStyle(
            spacing = PaletteTheme.spacing.small,
            labelPadding = PaletteTheme.spacing.xs,
            labelStyle = PaletteTheme.styles.text.labelSmall,
            borderColor = PaletteTheme.colorScheme.outline,
        )

    val catalog: CatalogStyle
        @Composable get() = CatalogStyle(
            itemSpacing = PaletteTheme.spacing.medium,
            itemStyle = ButtonStyleToken.Secondary.toComponentStyle(),
            itemTextStyle = PaletteTheme.styles.text.bodyMedium,
        )

    val confirmCancelButtonRow: ConfirmCancelButtonRowStyle
        @Composable get() = ConfirmCancelButtonRowStyle(
            buttonStyle = ConfirmButtonStyle(
                buttonStyle = CoreComponentStyles.button(ButtonStyleToken.Secondary),
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
}
