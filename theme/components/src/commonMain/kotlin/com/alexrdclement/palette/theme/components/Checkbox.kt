package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.components.core.Checkbox as ComponentCheckbox

@Composable
fun Checkbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ComponentCheckbox(
        isChecked = isChecked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        style = ButtonStyle(
            contentColor = ColorToken.Primary.toColor(),
            containerColor = ColorToken.Surface.toColor(),
        ),
        textStyle = PaletteTheme.styles.text.titleLarge,
        enabled = enabled,
        interactionSource = interactionSource,
    )
}
