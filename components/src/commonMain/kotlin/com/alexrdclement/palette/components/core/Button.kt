package com.alexrdclement.palette.components.core

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.Style
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
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.Shape
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.toRenderStyle
import com.alexrdclement.palette.theme.styles.toStyle
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape

// Public token overload — unchanged external signature. Callers using ButtonStyleToken.Primary,
// Secondary, or Tertiary continue to compile without modification.
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
    content: @Composable RowScope.(PaddingValues) -> Unit
) {
    val buttonStyle = style.toStyle()
    val contentColor = buttonStyle.contentColor.toColor().copy(
        alpha = if (enabled) 1f else PaletteTheme.colorScheme.disabledContentAlpha,
    )
    Button(
        onClick = onClick,
        modifier = modifier,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        style = buttonStyle.toRenderStyle(),
        shape = buttonStyle.shape.toShape(),
        contentColor = contentColor,
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

// Style escape hatch — callers who need appearance beyond the three built-in tokens can pass any
// Foundation Style. The style handles background and border (including disabled-state dimming);
// contentColor sets LocalContentColor for Palette's Text components.
// shape defaults to the primary shape (Circle in the default palette) to match the established
// visual language for interactive controls. Override when a different shape is needed.
@Composable
fun Button(
    onClick: () -> Unit,
    style: Style,
    contentColor: Color,
    modifier: Modifier = Modifier,
    shape: Shape = PaletteTheme.shapeScheme.primary,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    hapticFeedbackEnabled: Boolean = true,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPaddingDefault,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.(PaddingValues) -> Unit
) {
    Surface(
        onClick = onClick,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        enabled = enabled,
        shape = shape,
        contentColor = contentColor,
        style = style,
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
                    .padding(contentPadding),
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

    val ContentPaddingDefault: PaddingValues
        @Composable
        get() = PaddingValues(
            horizontal = PaletteTheme.spacing.large,
            vertical = PaletteTheme.spacing.small
        )

    val ContentPaddingCompact: PaddingValues
        @Composable
        get() = PaddingValues(
            horizontal = PaletteTheme.spacing.medium,
            vertical = 0.dp
        )
}

@Preview
@Composable
private fun PreviewPrimaryStyle(
    @PreviewParameter(BoolPreviewParameterProvider::class) isDarkMode: Boolean,
    @PreviewParameter(BoolPreviewParameterProvider::class) enabled: Boolean,
) {
    PaletteTheme(isDarkMode = isDarkMode) {
        Button(
            style = ButtonStyleToken.Primary,
            enabled = enabled,
            onClick = {},
        ) {
            Text("Button")
        }
    }
}

@Preview
@Composable
private fun PreviewSecondaryStyle(
    @PreviewParameter(BoolPreviewParameterProvider::class) isDarkMode: Boolean,
    @PreviewParameter(BoolPreviewParameterProvider::class) enabled: Boolean,
) {
    PaletteTheme(isDarkMode = isDarkMode) {
        Button(
            style = ButtonStyleToken.Secondary,
            enabled = enabled,
            onClick = {},
        ) {
            Text("Button")
        }
    }
}

@Preview
@Composable
private fun PreviewTertiaryStyle(
    @PreviewParameter(BoolPreviewParameterProvider::class) isDarkMode: Boolean,
    @PreviewParameter(BoolPreviewParameterProvider::class) enabled: Boolean,
) {
    PaletteTheme(isDarkMode = isDarkMode) {
        Surface {
            Button(
                style = ButtonStyleToken.Tertiary,
                enabled = enabled,
                onClick = {},
            ) {
                Text("Button")
            }
        }
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    val isDarkMode = true
    val enabled = true
    val interactionSource = MutableInteractionSource().apply {
        this.tryEmit(PressInteraction.Press(Offset.Zero))
    }

    PaletteTheme(isDarkMode = isDarkMode) {
        Surface {
            Button(
                enabled = enabled,
                interactionSource = interactionSource,
                onClick = {},
            ) {
                Text("Button")
            }
        }
    }
}
