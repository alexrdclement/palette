package com.alexrdclement.palette.components.demo.control

import com.alexrdclement.palette.components.demo.LocalDemoStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextField
import kotlinx.coroutines.flow.distinctUntilChanged

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
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .then(
                if (control.includeLabel) Modifier.padding(vertical = 8.dp)
                else Modifier.padding(bottom = 8.dp)
            ),
    ) {
        if (control.includeLabel) {
            Text(
                text = control.name,
                style = LocalDemoStyle.current.labelStyle,
                modifier = Modifier.weight(1f, fill = false),
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        TextField(
            state = textFieldState,
            textStyle = LocalDemoStyle.current.fieldTextStyle,
            style = LocalDemoStyle.current.textFieldStyle,
            inputTransformation = InputTransformation {
                val text = asCharSequence().toString()
                val newText = text.lastOrNull()?.toString() ?: ""
                if (text != newText) {
                    replace(0, length, newText)
                }
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            enabled = enabled,
        )
    }
}

@Preview
@Composable
fun CharControlPreview() {
    CharControl(
        control = Control.CharField(
            name = "Label",
            value = { 'a' },
            includeLabel = true,
        ),
    )
}
