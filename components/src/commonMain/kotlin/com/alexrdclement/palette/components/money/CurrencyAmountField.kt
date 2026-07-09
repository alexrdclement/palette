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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextField
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.formats.core.NumberFormatInputTransformation
import com.alexrdclement.palette.formats.core.NumberFormatOutputTransformation
import com.alexrdclement.palette.formats.money.MoneyFormat

data class CurrencyAmountFieldStyle(
    val textStyle: TextStyle = TextStyle(),
    val placeholderStyle: TextStyle = TextStyle(),
    val cursorBrush: Brush = SolidColor(Color.Unspecified),
    val padding: Dp = 16.dp,
    val spacing: Dp = 8.dp,
)

@Composable
fun CurrencyAmountField(
    moneyFormat: MoneyFormat,
    textFieldState: TextFieldState = rememberTextFieldState(),
    style: CurrencyAmountFieldStyle = CurrencyAmountFieldStyle(),
    placeholder: String = "0",
    includeCurrencyPrefix: Boolean = true,
    maxNumDecimalValues: Int = 2,
) {
    val textStyle = style.textStyle
    TextField(
        state = textFieldState,
        style = TextFieldStyle(textStyle = textStyle, cursorBrush = style.cursorBrush),
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .padding(style.padding),
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
                horizontalArrangement = Arrangement.spacedBy(style.spacing),
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
                            style = style.placeholderStyle,
                        )
                    }

                    // Min width to ensure cursor still appears
                    Box(modifier = Modifier.widthIn(min = style.spacing)) {
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
