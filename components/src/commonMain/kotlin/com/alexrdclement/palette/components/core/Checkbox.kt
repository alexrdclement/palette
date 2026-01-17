package com.alexrdclement.palette.components.core

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme

@Composable
fun Checkbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Button(
        contentColor = ColorToken.Primary,
        containerColor = ColorToken.Surface,
        onClick = { onCheckedChange(!isChecked) },
        modifier = modifier.semantics(mergeDescendants = true) {
            role = Role.Checkbox
        },
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        Text(
            text = if (isChecked) "☑︎" else "☐",
            style = PaletteTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isChecked: Boolean,
) {
    PaletteTheme {
        Surface {
            var isChecked by remember { mutableStateOf(isChecked) }

            Checkbox(
                isChecked = isChecked,
                onCheckedChange = { isChecked = !it },
            )
        }
    }
}
