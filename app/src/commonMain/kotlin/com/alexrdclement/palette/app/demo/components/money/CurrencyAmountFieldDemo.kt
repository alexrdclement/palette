package com.alexrdclement.palette.app.demo.components.money

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.app.preview.PalettePreview
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.money.CurrencyAmountField
import com.alexrdclement.palette.theme.PaletteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CurrencyAmountFieldDemo(
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
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = text,
                style = PaletteTheme.typography.headline,
            )
            CurrencyAmountField(
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
