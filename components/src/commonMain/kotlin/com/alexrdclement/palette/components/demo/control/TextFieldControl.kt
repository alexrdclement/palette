package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextField
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun TextFieldControl(
    control: Control.TextField,
    modifier: Modifier = Modifier,
) {
    val enabled by rememberUpdatedState(control.enabled())
    val keyboardOptions by rememberUpdatedState(control.keyboardOptions())
    val inputTransformation by rememberUpdatedState(control.inputTransformation())
    val onValueChange by rememberUpdatedState(control.onValueChange)

    LaunchedEffect(control.textFieldState) {
        snapshotFlow { control.textFieldState.text.toString() }
            .distinctUntilChanged()
            .collect { text ->
                onValueChange(text)
            }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .then(
                if (control.includeLabel) Modifier.padding(vertical = PaletteTheme.spacing.small)
                else Modifier.padding(bottom = PaletteTheme.spacing.small)
            ),
    ) {
        if (control.includeLabel) {
            Text(
                text = control.name,
                style = PaletteTheme.styles.text.labelLarge,
                modifier = Modifier.weight(1f, fill = false),
            )
            Spacer(modifier = Modifier.width(PaletteTheme.spacing.small))
        }
        TextField(
            state = control.textFieldState,
            textStyle = PaletteTheme.styles.text.bodyMedium,
            enabled = enabled,
            inputTransformation = inputTransformation,
            keyboardOptions = keyboardOptions,
        )
    }
}

@Preview
@Composable
fun TextFieldControlPreview() {
    val textFieldState = rememberTextFieldState(initialText = "Text Field")
    PaletteTheme {
        TextFieldControl(
            control = Control.TextField(
                name = "Label",
                textFieldState = textFieldState,
                includeLabel = true,
            ),
        )
    }
}
