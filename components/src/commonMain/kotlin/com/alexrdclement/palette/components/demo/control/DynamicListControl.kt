package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toPersistentList

data class DynamicListControlStyle(
    val spacing: Dp = 16.dp,
    val itemSpacing: Dp = 8.dp,
    val itemControlSpacing: Dp = 4.dp,
    val indent: Dp = 16.dp,
)

@Composable
fun <T> DynamicListControl(
    control: Control.DynamicList<T>,
    modifier: Modifier = Modifier,
    controlStyle: ControlStyle = ControlStyle(),
) {
    val style = controlStyle.dynamicList
    val items by rememberUpdatedState(control.items())
    val onItemsChange by rememberUpdatedState(control.onItemsChange)

    var expanded by remember { mutableStateOf(control.expandedInitial) }
    var addingItem by remember { mutableStateOf<T?>(null) }

    Column(
        verticalArrangement = Arrangement.spacedBy(style.spacing),
        modifier = modifier.fillMaxWidth()
    ) {
        if (control.includeLabel) {
            ExpandableHeader(
                name = control.name,
                expanded = expanded,
                onExpandedChange = { expanded = it },
                style = controlStyle.expandableHeader,
            )
        }

        if (!expanded && control.includeLabel) return@Column

        Column(
            verticalArrangement = Arrangement.spacedBy(style.spacing),
            modifier = modifier
                .fillMaxWidth()
                .then(if (control.indent) Modifier.padding(start = style.indent) else Modifier)
        ) {
            items.forEachIndexed { index, item ->
                DynamicListItem(
                    control = control,
                    item = item,
                    controlStyle = controlStyle,
                    onItemChange = { newItem ->
                        val newList = items.toMutableList()
                        newList[index] = newItem
                        onItemsChange(newList)
                    },
                    onDelete = {
                        val newList = items.toMutableList()
                        newList.removeAt(index)
                        onItemsChange(newList)
                    }
                )
            }

            val currentAddingItem = addingItem
            if (currentAddingItem != null) {
                DynamicListAddItem(
                    control = control,
                    item = currentAddingItem,
                    controlStyle = controlStyle,
                    onItemChange = { addingItem = it },
                    onConfirm = {
                        val newList = items.toMutableList()
                        newList.add(currentAddingItem)
                        onItemsChange(newList)
                        addingItem = null
                    },
                    onCancel = { addingItem = null }
                )
            } else {
                DynamicListAddButton(
                    text = control.addButtonText,
                    style = controlStyle.button,
                    onClick = { addingItem = control.newItemDefault() }
                )
            }
        }
    }
}

@Composable
private fun <T> DynamicListItem(
    control: Control.DynamicList<T>,
    item: T,
    onItemChange: (T) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    controlStyle: ControlStyle = ControlStyle(),
) {
    val style = controlStyle.dynamicList
    val itemControl = control.createControl(item, onItemChange)

    Row(
        horizontalArrangement = Arrangement.spacedBy(style.itemSpacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Controls(
            controls = listOf(itemControl).toPersistentList(),
            controlStyle = controlStyle,
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(style.itemControlSpacing),
            modifier = Modifier.weight(1f)
        )

        ButtonControl(
            control = Control.Button(
                name = "Delete",
                onClick = onDelete
            ),
            style = controlStyle.button,
        )
    }
}

@Composable
private fun <T> DynamicListAddItem(
    control: Control.DynamicList<T>,
    item: T,
    onItemChange: (T) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    controlStyle: ControlStyle = ControlStyle(),
) {
    val style = controlStyle.dynamicList
    val addItemControl = control.createControl(item, onItemChange)

    Column(
        verticalArrangement = Arrangement.spacedBy(style.spacing),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Controls(
            controls = listOf(addItemControl).toPersistentList(),
            controlStyle = controlStyle,
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(style.itemControlSpacing),
        )

        DynamicListActionButtons(
            onConfirm = onConfirm,
            onCancel = onCancel,
            controlStyle = controlStyle,
        )
    }
}

@Composable
private fun DynamicListActionButtons(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    controlStyle: ControlStyle = ControlStyle(),
) {
    val style = controlStyle.dynamicList
    Row(
        horizontalArrangement = Arrangement.spacedBy(style.spacing),
        modifier = modifier.fillMaxWidth()
    ) {
        ButtonControl(
            control = Control.Button(
                name = "Confirm",
                onClick = onConfirm
            ),
            style = controlStyle.button,
        )
        ButtonControl(
            control = Control.Button(
                name = "Cancel",
                onClick = onCancel
            ),
            style = controlStyle.button,
        )
    }
}

@Composable
private fun DynamicListAddButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonControlStyle = ButtonControlStyle(),
) {
    ButtonControl(
        control = Control.Button(
            name = text,
            onClick = onClick
        ),
        style = style,
        modifier = modifier
    )
}
