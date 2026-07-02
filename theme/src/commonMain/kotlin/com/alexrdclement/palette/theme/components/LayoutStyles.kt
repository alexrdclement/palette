package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.FloatingActionStyle
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.components.layout.TopBarStyle
import com.alexrdclement.palette.components.layout.catalog.CatalogStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmButtonStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRowStyle
import com.alexrdclement.palette.components.layout.dialog.DialogContentStyle
import com.alexrdclement.palette.theme.PaletteTheme

/** Resolved styles for [com.alexrdclement.palette.components.layout]; surfaced via [PaletteTheme.styles]. */
object LayoutStyles {

    val scaffold: ScaffoldStyle
        @Composable get() = ScaffoldStyle(
            surfaceStyle = CoreStyles.surface.default,
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
            labelStyle = TextStyles.labelSmall,
            borderColor = PaletteTheme.colorScheme.outline,
        )

    val catalog: CatalogStyle
        @Composable get() = CatalogStyle(
            itemSpacing = PaletteTheme.spacing.medium,
            itemStyle = CoreStyles.button.secondary,
            itemTextStyle = TextStyles.bodyMedium,
        )

    val confirmCancelButtonRow: ConfirmCancelButtonRowStyle
        @Composable get() = ConfirmCancelButtonRowStyle(
            buttonStyle = ConfirmButtonStyle(
                buttonStyle = CoreStyles.button.secondary,
                textStyle = TextStyles.bodyMedium,
            ),
            spacing = PaletteTheme.spacing.medium,
        )

    val dialogContent: DialogContentStyle
        @Composable get() = DialogContentStyle(
            titleStyle = TextStyles.titleLarge,
            messageStyle = TextStyles.bodyLarge,
            surfaceStyle = CoreStyles.surface.container,
            spacing = PaletteTheme.spacing.medium,
            padding = PaletteTheme.spacing.large,
            titleBottomPadding = PaletteTheme.spacing.medium,
            messageBottomPadding = PaletteTheme.spacing.large,
        )
}
