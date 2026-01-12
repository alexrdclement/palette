package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.byValue
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextField
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharControl(
    control: Control.CharField,
    modifier: Modifier = Modifier,
) {
    val value by rememberUpdatedState(control.value())
    val enabled by rememberUpdatedState(control.enabled())
    val onValueChange by rememberUpdatedState(control.onValueChange)

    val textFieldState = rememberTextFieldState(initialText = value.toString())

    LaunchedEffect(value) {
        textFieldState.edit {
            replace(0, length, value.toString())
        }
    }

    LaunchedEffect(textFieldState) {
        snapshotFlow { textFieldState.text.toString() }
            .distinctUntilChanged()
            .collect { text ->
                onValueChange(text.lastOrNull() ?: ' ')
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
                style = PaletteTheme.typography.labelLarge,
                modifier = Modifier.weight(1f, fill = false),
            )
            Spacer(modifier = Modifier.width(PaletteTheme.spacing.small))
        }
        TextField(
            state = textFieldState,
            textStyle = PaletteTheme.typography.labelLarge,
            inputTransformation = InputTransformation.byValue { _, proposed ->
                proposed.lastOrNull()?.toString() ?: ""
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            enabled = enabled,
        )
    }
}

@Preview
@Composable
fun CharControlPreview() {
    PaletteTheme {
        CharControl(
            control = Control.CharField(
                name = "Label",
                value = { 'a' },
                includeLabel = true,
            ),
        )
    }
}
