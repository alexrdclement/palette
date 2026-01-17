package com.alexrdclement.palette.components.money

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextField
import com.alexrdclement.palette.components.format.NumberFormatInputTransformation
import com.alexrdclement.palette.components.format.NumberFormatOutputTransformation
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.format.MoneyFormat

/**
 * A text field for entering currency amounts with automatic formatting.
 *
 * Features:
 * - Accepts only numeric input and the decimal separator
 * - Automatically formats with grouping separators (e.g., 1,234.56)
 * - Enforces maximum decimal places
 * - Shows currency symbol prefix (optional)
 * - Handles leading zeros intelligently
 *
 * @param moneyFormat The format defining currency symbol and number formatting rules
 * @param textFieldState The state managing the text field's content
 * @param placeholder Placeholder text shown when empty
 * @param includeCurrencyPrefix Whether to show the currency symbol before the field
 * @param maxNumDecimalValues Maximum number of decimal places allowed
 */

@Composable
fun CurrencyAmountField(
    moneyFormat: MoneyFormat,
    textFieldState: TextFieldState = rememberTextFieldState(),
    placeholder: String = "0",
    includeCurrencyPrefix: Boolean = true,
    maxNumDecimalValues: Int = 2
) {
    TextField(
        state = textFieldState,
        textStyle = PaletteTheme.typography.headline,
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .padding(PaletteTheme.spacing.medium),
        inputTransformation = NumberFormatInputTransformation(
            numberFormat = moneyFormat.numberFormat,
            maxNumDecimalValues = maxNumDecimalValues,
        ),
        outputTransformation = NumberFormatOutputTransformation(
            numberFormat = moneyFormat.numberFormat,
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { textField ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.small),
            ) {
                if (includeCurrencyPrefix) {
                    Text(
                        text = moneyFormat.currencySymbol.orEmpty(),
                        style = PaletteTheme.typography.headline
                    )
                }

                Box {
                    if (textFieldState.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = PaletteTheme.typography.headline.copy(
                                color = PaletteTheme.colorScheme.primary.copy(
                                    alpha = 0.5f,
                                ),
                            )
                        )
                    }

                    // Min width to ensure cursor still appears
                    Box(modifier = Modifier.widthIn(min = PaletteTheme.spacing.small)) {
                        textField()
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        val textFieldState = rememberTextFieldState(initialText = "")
        CurrencyAmountField(
            moneyFormat = PaletteTheme.formats.moneyFormats.default,
            textFieldState = textFieldState,
        )
    }
}
