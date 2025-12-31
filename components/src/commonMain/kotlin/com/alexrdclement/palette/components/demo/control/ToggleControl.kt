package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.Checkbox
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.PaletteTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ToggleControl(
    control: Control.Toggle,
    modifier: Modifier = Modifier,
    includeTitle: Boolean = true,
) {
    val checked by rememberUpdatedState(control.value())

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (includeTitle) {
            Text(control.name, style = PaletteTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(PaletteTheme.spacing.small))
        }

        Checkbox(checked, onCheckedChange = control.onValueChange)
    }
}

@Composable
fun ToggleControlRow(
    control: Control.Toggle,
    modifier: Modifier = Modifier,
    includeTitle: Boolean = true,
) {
    val isChecked by rememberUpdatedState(control.value())

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (includeTitle) {
            Text(control.name, style = PaletteTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(PaletteTheme.spacing.small))
        }

        Checkbox(isChecked, onCheckedChange = control.onValueChange)
    }
}

@Preview
@Composable
private fun ToggleControlPreview() {
    PaletteTheme {
        var on by remember { mutableStateOf(false) }
        val control = Control.Toggle(
            name = "Color",
            value = { on },
            onValueChange = { on = it }
        )
        ToggleControl(control = control)
    }
}

@Preview
@Composable
private fun ToggleControlRowPreview() {
    PaletteTheme {
        var on by remember { mutableStateOf(false) }
        val control = Control.Toggle(
            name = "Color",
            value = { on },
            onValueChange = { on = it }
        )
        ToggleControlRow(control = control)
    }
}
