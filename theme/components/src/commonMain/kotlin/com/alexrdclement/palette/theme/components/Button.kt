package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.toComponentStyle
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.components.core.Button as ComponentButton
import com.alexrdclement.palette.components.core.ButtonDefaults as ComponentButtonDefaults

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    hapticFeedbackEnabled: Boolean = true,
    style: ButtonStyleToken = ButtonStyleToken.Primary,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPaddingDefault,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.(PaddingValues) -> Unit,
) {
    ComponentButton(
        onClick = onClick,
        modifier = modifier,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        style = style.toComponentStyle(),
        enabled = enabled,
        contentPadding = contentPadding,
        indication = PaletteTheme.indication,
        interactionSource = interactionSource,
        content = content,
    )
}

object ButtonDefaults {
    val MinWidth = ComponentButtonDefaults.MinWidth
    val MinHeight = ComponentButtonDefaults.MinHeight

    val ContentPaddingDefault: PaddingValues
        @Composable
        get() = PaddingValues(
            horizontal = PaletteTheme.spacing.large,
            vertical = PaletteTheme.spacing.small,
        )

    val ContentPaddingCompact: PaddingValues
        @Composable
        get() = PaddingValues(
            horizontal = PaletteTheme.spacing.medium,
            vertical = 0.dp,
        )
}
