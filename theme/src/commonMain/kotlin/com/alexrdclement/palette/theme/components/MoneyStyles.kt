package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.SolidColor
import com.alexrdclement.palette.components.money.CurrencyAmountFieldStyle
import com.alexrdclement.palette.theme.PaletteTheme

/** Resolved styles for [com.alexrdclement.palette.components.money]; surfaced via [PaletteTheme.styles]. */
object MoneyStyles {

    val currencyAmountField: CurrencyAmountFieldStyle
        @Composable get() = CurrencyAmountFieldStyle(
            textStyle = TextStyles.headline,
            placeholderColor = PaletteTheme.colorScheme.primary.copy(alpha = 0.5f),
            cursorBrush = SolidColor(PaletteTheme.colorScheme.primary),
            padding = PaletteTheme.spacing.medium,
            spacing = PaletteTheme.spacing.small,
        )
}
