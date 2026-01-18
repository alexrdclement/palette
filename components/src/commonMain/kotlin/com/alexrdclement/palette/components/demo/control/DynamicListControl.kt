package com.alexrdclement.palette.components.demo.control

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.collections.immutable.toPersistentList

@Composable
fun <T> DynamicControlColumn(
    control: Control.DynamicControlColumn<T>,
    modifier: Modifier = Modifier,
) {
    val items by rememberUpdatedState(control.items())
    val onItemsChange by rememberUpdatedState(control.onItemsChange)

    var expanded by remember { mutableStateOf(control.expandedInitial) }
    var addingItem by remember { mutableStateOf<T?>(null) }

    Column(
        verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
        modifier = modifier.fillMaxWidth()
    ) {
        if (control.includeLabel) {
            ExpandableHeader(
                name = control.name,
                expanded = expanded,
                onExpandedChange = { expanded = it }
            )
        }

        if (!expanded && control.includeLabel) return@Column

        Column(
            verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
            modifier = modifier
                .fillMaxWidth()
                .then(if (control.indent) Modifier.padding(start = PaletteTheme.spacing.medium) else Modifier)
        ) {
            items.forEachIndexed { index, item ->
                DynamicListItem(
                    control = control,
                    item = item,
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
                    onClick = { addingItem = control.newItemDefault() }
                )
            }
        }
    }
}

@Composable
private fun <T> DynamicListItem(
    control: Control.DynamicControlColumn<T>,
    item: T,
    onItemChange: (T) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val itemControl = control.createControl(item, onItemChange)

    Row(
        horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Controls(
            controls = listOf(itemControl).toPersistentList(),
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.xs),
            modifier = Modifier.weight(1f)
        )

        ButtonControl(
            control = Control.Button(
                name = "Delete",
                onClick = onDelete
            )
        )
    }
}

@Composable
private fun <T> DynamicListAddItem(
    control: Control.DynamicControlColumn<T>,
    item: T,
    onItemChange: (T) -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val addItemControl = control.createControl(item, onItemChange)

    Column(
        verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Controls(
            controls = listOf(addItemControl).toPersistentList(),
            contentPadding = PaddingValues(0.dp),
            verticalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.xs),
        )

        DynamicListActionButtons(
            onConfirm = onConfirm,
            onCancel = onCancel
        )
    }
}

@Composable
private fun DynamicListActionButtons(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(PaletteTheme.spacing.medium),
        modifier = modifier.fillMaxWidth()
    ) {
        ButtonControl(
            control = Control.Button(
                name = "Confirm",
                onClick = onConfirm
            )
        )
        ButtonControl(
            control = Control.Button(
                name = "Cancel",
                onClick = onCancel
            )
        )
    }
}

@Composable
private fun DynamicListAddButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ButtonControl(
        control = Control.Button(
            name = text,
            onClick = onClick
        ),
        modifier = modifier
    )
}
