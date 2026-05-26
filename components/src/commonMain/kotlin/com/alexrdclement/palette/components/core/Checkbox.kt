package com.alexrdclement.palette.components.core

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.style.Style
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.LocalPaletteColorScheme
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.style.background
import com.alexrdclement.palette.theme.toColor

@Composable
fun Checkbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: Style = CheckboxDefaults.style,
    contentColor: Color = PaletteTheme.colorScheme.primary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val resolvedContentColor = contentColor.copy(
        alpha = if (enabled) 1f else PaletteTheme.colorScheme.disabledContentAlpha
    )
    Button(
        style = style,
        contentColor = resolvedContentColor,
        onClick = { onCheckedChange(!isChecked) },
        modifier = modifier.semantics(mergeDescendants = true) {
            role = Role.Checkbox
        },
        enabled = enabled,
        interactionSource = interactionSource,
    ) {
        Text(
            text = if (isChecked) "☑︎" else "☐",
            style = PaletteTheme.styles.text.titleLarge
        )
    }
}

object CheckboxDefaults {
    val style: Style get() = Style {
        background(ColorToken.Surface)
        disabled {
            val scheme = LocalPaletteColorScheme.currentValue
            background(ColorToken.Surface.toColor(scheme).copy(alpha = scheme.disabledContainerAlpha))
        }
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
