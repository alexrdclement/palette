package com.alexrdclement.palette.components.demo.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

@Composable
fun ButtonControl(
    control: Control.Button,
    modifier: Modifier = Modifier,
) {
    val enabled by rememberUpdatedState(control.enabled())
    Button(
        style = ButtonStyleToken.Secondary,
        onClick = control.onClick,
        enabled = enabled,
        modifier = modifier
            .then(control.modifier)
    ) {
        Text(control.name, style = PaletteTheme.typography.labelLarge)
    }
}
