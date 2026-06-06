package com.alexrdclement.palette.components.demo.control

import com.alexrdclement.palette.components.demo.LocalDemoStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

data class TextFieldControlStyle(
    val spacing: Dp = 8.dp,
    val verticalPadding: Dp = 8.dp,
)

@Composable
fun TextFieldControl(
    control: Control.TextField,
    modifier: Modifier = Modifier,
) {
    val style = LocalDemoStyle.current.textFieldControl
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
        horizontalArrangement = Arrangement.spacedBy(style.spacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .then(
                if (control.includeLabel) Modifier.padding(vertical = style.verticalPadding)
                else Modifier.padding(bottom = style.verticalPadding)
            ),
    ) {
        if (control.includeLabel) {
            Text(
                text = control.name,
                style = LocalDemoStyle.current.labelStyle,
                modifier = Modifier.weight(1f, fill = false),
            )
            Spacer(modifier = Modifier.width(style.spacing))
        }
        TextField(
            state = control.textFieldState,
            style = LocalDemoStyle.current.textFieldStyle,
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
    TextFieldControl(
        control = Control.TextField(
            name = "Label",
            textFieldState = textFieldState,
            includeLabel = true,
        ),
    )
}
