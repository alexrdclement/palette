package com.alexrdclement.palette.components.core

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider

data class ButtonStyle(
    val contentColor: Color = Color.Unspecified,
    val containerColor: Color = Color.Unspecified,
    val shape: Shape = Shape.Rectangle(),
    val borderStyle: BorderStyle? = null,
    val disabledContentAlpha: Float = 1f,
    val disabledContainerAlpha: Float = 1f,
    val contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    val indication: Indication? = null,
)

/** Returns a copy of this [ButtonStyle] with [contentPadding] overridden. */
fun ButtonStyle.withContentPadding(contentPadding: PaddingValues): ButtonStyle =
    copy(contentPadding = contentPadding)

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle(),
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    hapticFeedbackEnabled: Boolean = true,
    enabled: Boolean = true,
    indication: Indication? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.(PaddingValues) -> Unit
) {
    val containerColor = style.containerColor.copy(
        alpha = if (enabled) style.containerColor.alpha else style.disabledContainerAlpha,
    )
    val contentColor = style.contentColor.copy(
        alpha = if (enabled) style.contentColor.alpha else style.disabledContentAlpha,
    )
    Surface(
        onClick = onClick,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        enabled = enabled,
        style = SurfaceStyle(
            shape = style.shape,
            color = containerColor,
            contentColor = contentColor,
            borderStyle = style.borderStyle,
            indication = indication ?: style.indication,
        ),
        interactionSource = interactionSource,
        modifier = modifier.semantics { role = Role.Button }
    ) { shapePadding ->
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
        ) {
            Row(
                Modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = ButtonDefaults.MinHeight
                    )
                    .padding(style.contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                content(shapePadding)
            }
        }
    }
}

object ButtonDefaults {
    val MinWidth = 58.dp
    val MinHeight = 40.dp

    val ContentPadding: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 8.dp,
    )
}

@Preview
@Composable
private fun ButtonPreview(
    @PreviewParameter(BoolPreviewParameterProvider::class) isEnabled: Boolean,
) {
    val interactionSource = MutableInteractionSource().apply {
        this.tryEmit(PressInteraction.Press(Offset.Zero))
    }
    Surface {
        Button(
            enabled = isEnabled,
            interactionSource = interactionSource,
            onClick = {},
        ) {
            Text("Button")
        }
    }
}
