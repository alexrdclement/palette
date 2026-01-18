package com.alexrdclement.palette.app.demo.components.money

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.preview.PalettePreview
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.money.CurrencyAmountField
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.formats.money.format
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.modifiers.border

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
                    style = PaletteTheme.typography.headline,
                )
            }
            BoxWithLabel(
                label = "Formatted",
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = moneyFormat.format(text),
                    style = PaletteTheme.typography.headline,
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
    PalettePreview {
        val textFieldState = rememberTextFieldState(initialText = "123.45")
        CurrencyAmountFieldDemo(textFieldState = textFieldState)
    }
}
