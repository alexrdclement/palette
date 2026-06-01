package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Controls(
    controls: ImmutableList<Control>,
    modifier: Modifier = Modifier,
    name: String? = null,
    indent: Boolean = false,
    expandedInitial: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp)
) {
    Column(
        verticalArrangement = verticalArrangement,
        modifier = modifier
            .padding(contentPadding)
    ) {
        var expanded by remember { mutableStateOf(expandedInitial) }
        name?.let {
            ExpandableHeader(
                name = it,
                expanded = expanded,
                onExpandedChange = { expanded = it }
            )
        }

        if (!expanded && name != null) return@Column

        for (control in controls) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (indent)
                            Modifier.padding(start = 16.dp)
                        else Modifier
                    )
            ) {
                when (control) {
                    is Control.Button -> ButtonControl(control = control)
                    is Control.Color -> ColorControl(control = control)
                    is Control.Slider -> SliderControl(control = control)
                    is Control.Dropdown<*> -> DropdownControlRow(control = control)
                    is Control.Toggle -> ToggleControlRow(control = control)
                    is Control.CharField -> CharControl(control = control)
                    is Control.TextField -> TextFieldControl(control = control)
                    is Control.DynamicList<*> -> DynamicListControl(control = control)
                    is Control.ControlColumn -> {
                        val controls by rememberUpdatedState(control.controls())
                        Controls(
                            controls = controls,
                            name = control.name,
                            indent = control.indent,
                            expandedInitial = control.expandedInitial,
                            contentPadding = PaddingValues(vertical = 8.dp),
                        )
                    }
                    is Control.ControlRow -> {
                        val controls by rememberUpdatedState(control.controls())
                        ControlsRow(controls = controls)
                    }
                }
            }
        }
    }
}

@Composable
fun ControlsRow(
    controls: ImmutableList<Control>,
    modifier: Modifier = Modifier,
    equalWeight: Boolean = true,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (control in controls) {
            Box(
                modifier = Modifier
                    .then(if (equalWeight) Modifier.weight(1f, fill = false) else Modifier)
            ) {
                when (control) {
                    is Control.Button -> ButtonControl(control = control)
                    is Control.Color -> ColorControl(control = control)
                    is Control.Slider -> SliderControl(control = control)
                    is Control.Dropdown<*> -> DropdownControl(control = control)
                    is Control.Toggle -> ToggleControl(control = control)
                    is Control.CharField -> CharControl(control = control)
                    is Control.TextField -> TextFieldControl(control = control)
                    is Control.DynamicList<*> -> DynamicListControl(control = control)
                    is Control.ControlColumn -> {
                        val controls by rememberUpdatedState(control.controls())
                        Controls(
                            controls = controls,
                            name = control.name,
                            indent = control.indent,
                            expandedInitial = control.expandedInitial,
                            contentPadding = PaddingValues(horizontal = 8.dp),
                        )
                    }
                    is Control.ControlRow -> {
                        val controls by rememberUpdatedState(control.controls())
                        Controls(controls = controls)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    run {
        var expanded by remember { mutableStateOf(true) }
        Controls(
            controls = persistentListOf(
                Control.Slider(
                    name = "Amount",
                    value = { 0.5f },
                    onValueChange = {},
                ),
                Control.Slider(
                    name = "Amount 2",
                    value = { 0.5f },
                    onValueChange = {},
                )
            ),
            expandedInitial = expanded,
            name = "Sliders",
        )
    }
}
