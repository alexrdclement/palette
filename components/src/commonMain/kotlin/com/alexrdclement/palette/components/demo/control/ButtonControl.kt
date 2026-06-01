package com.alexrdclement.palette.components.demo.control

import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.TextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text

@Composable
fun ButtonControl(
    control: Control.Button,
    modifier: Modifier = Modifier,
) {
    val enabled by rememberUpdatedState(control.enabled())
    Button(
        style = ButtonStyle(),
        onClick = control.onClick,
        enabled = enabled,
        modifier = modifier
            .then(control.modifier)
    ) {
        Text(control.name, style = TextStyle())
    }
}
