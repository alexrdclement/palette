package com.alexrdclement.palette.components.demo.money

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.money.CurrencyAmountField
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.formats.money.format
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun CurrencyAmountFieldDemo(
    moneyFormat: MoneyFormat = PaletteTheme.formats.moneyFormats.default,
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState = rememberTextFieldState(),
) {
    val text by snapshotFlow { textFieldState.text.toString() }
        .collectAsState(initial = textFieldState.text.toString())

    Demo(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
                .align(Alignment.Center),
        ) {
            BoxWithLabel(
                label = "Raw",
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = text,
                    style = PaletteTheme.styles.text.headline,
                )
            }
            BoxWithLabel(
                label = "Formatted",
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = moneyFormat.format(text),
                    style = PaletteTheme.styles.text.headline,
                )
            }
            CurrencyAmountField(
                moneyFormat = PaletteTheme.formats.moneyFormats.default,
                textFieldState = textFieldState,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val textFieldState = rememberTextFieldState(initialText = "123.45")
    CurrencyAmountFieldDemo(textFieldState = textFieldState)
}
