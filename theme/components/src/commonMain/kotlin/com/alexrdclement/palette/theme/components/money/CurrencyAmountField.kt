package com.alexrdclement.palette.theme.components.money

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.money.CurrencyAmountField as ComponentCurrencyAmountField

@Composable
fun CurrencyAmountField(
    moneyFormat: MoneyFormat,
    textFieldState: TextFieldState = rememberTextFieldState(),
    placeholder: String = "0",
    includeCurrencyPrefix: Boolean = true,
    maxNumDecimalValues: Int = 2,
) {
    ComponentCurrencyAmountField(
        moneyFormat = moneyFormat,
        textFieldState = textFieldState,
        textStyle = PaletteTheme.styles.text.headline,
        placeholder = placeholder,
        placeholderColor = PaletteTheme.colorScheme.primary.copy(alpha = 0.5f),
        includeCurrencyPrefix = includeCurrencyPrefix,
        maxNumDecimalValues = maxNumDecimalValues,
        padding = PaletteTheme.spacing.medium,
        spacing = PaletteTheme.spacing.small,
    )
}
