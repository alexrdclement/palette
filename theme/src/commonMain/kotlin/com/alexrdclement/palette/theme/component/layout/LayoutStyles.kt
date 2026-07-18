package com.alexrdclement.palette.theme.component.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.alexrdclement.palette.components.layout.BoxWithLabelStyle
import com.alexrdclement.palette.components.layout.FloatingActionStyle
import com.alexrdclement.palette.components.layout.ScaffoldStyle
import com.alexrdclement.palette.components.layout.TopBarStyle
import com.alexrdclement.palette.components.layout.catalog.CatalogStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmButtonStyle
import com.alexrdclement.palette.components.layout.dialog.ConfirmCancelButtonRowStyle
import com.alexrdclement.palette.components.layout.dialog.DialogContentStyle
import com.alexrdclement.palette.components.core.copy
import com.alexrdclement.palette.theme.semantic.color.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.component.core.CoreStyles
import com.alexrdclement.palette.theme.component.core.TextStyles
import com.alexrdclement.palette.theme.semantic.color.toColor

object LayoutStyles {

    val scaffold: ScaffoldStyle
        @Composable get() = ScaffoldStyle(
            surfaceStyle = CoreStyles.surface.default,
        )

    val floatingAction: FloatingActionStyle
        @Composable get() = FloatingActionStyle(
            spacing = PaletteTheme.semantic.spacing.small,
        )

    val topBar: TopBarStyle
        @Composable get() = TopBarStyle(
            spacing = PaletteTheme.semantic.spacing.small,
        )

    val boxWithLabel: BoxWithLabelStyle
        @Composable get() = BoxWithLabelStyle(
            spacing = PaletteTheme.semantic.spacing.small,
            labelPadding = PaletteTheme.semantic.spacing.xs,
            labelStyle = TextStyles.labelSmall,
            borderColor = PaletteTheme.semantic.color.outline,
        )

    val catalog: CatalogStyle
        @Composable get() = CatalogStyle(
            itemSpacing = PaletteTheme.semantic.spacing.medium,
            itemStyle = CoreStyles.button.secondary,
            itemTextStyle = TextStyles.bodyMedium.copy(
                color = ColorToken.Secondary.toColor(),
            ),
        )

    val confirmCancelButtonRow: ConfirmCancelButtonRowStyle
        @Composable get() = ConfirmCancelButtonRowStyle(
            buttonStyle = ConfirmButtonStyle(
                buttonStyle = CoreStyles.button.secondary,
                textStyle = TextStyles.bodyMedium.copy(
                    color = ColorToken.Secondary.toColor(),
                ),
            ),
            spacing = PaletteTheme.semantic.spacing.medium,
        )

    val dialogContent: DialogContentStyle
        @Composable get() = DialogContentStyle(
            titleStyle = TextStyles.titleLarge.copy(textAlign = TextAlign.Center),
            messageStyle = TextStyles.bodyLarge.copy(textAlign = TextAlign.Center),
            surfaceStyle = CoreStyles.surface.container,
            buttonRowStyle = confirmCancelButtonRow,
            spacing = PaletteTheme.semantic.spacing.medium,
            padding = PaletteTheme.semantic.spacing.large,
            titleBottomPadding = PaletteTheme.semantic.spacing.medium,
            messageBottomPadding = PaletteTheme.semantic.spacing.large,
        )
}
