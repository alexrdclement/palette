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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextField
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.components.core.copy
import com.alexrdclement.palette.formats.core.NumberFormatInputTransformation
import com.alexrdclement.palette.formats.core.NumberFormatOutputTransformation
import com.alexrdclement.palette.formats.money.MoneyFormat

@Composable
fun CurrencyAmountField(
    moneyFormat: MoneyFormat,
    textFieldState: TextFieldState = rememberTextFieldState(),
    textStyle: TextStyle = TextStyle(),
    placeholder: String = "0",
    placeholderColor: Color = Color.Unspecified,
    includeCurrencyPrefix: Boolean = true,
    maxNumDecimalValues: Int = 2,
    padding: Dp = 16.dp,
    spacing: Dp = 8.dp,
) {
    TextField(
        state = textFieldState,
        textStyle = textStyle,
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .padding(padding),
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
                horizontalArrangement = Arrangement.spacedBy(spacing),
            ) {
                if (includeCurrencyPrefix) {
                    Text(
                        text = moneyFormat.currencySymbol.orEmpty(),
                        style = textStyle,
                    )
                }

                Box {
                    if (textFieldState.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle.copy(
                                color = placeholderColor,
                            )
                        )
                    }

                    // Min width to ensure cursor still appears
                    Box(modifier = Modifier.widthIn(min = spacing)) {
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
    val textFieldState = rememberTextFieldState(initialText = "")
    CurrencyAmountField(
        moneyFormat = MoneyFormat(),
        textFieldState = textFieldState,
    )
}
