package com.alexrdclement.palette.components.core

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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.preview.BoolPreviewParameterProvider
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.toStyle
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.toStyle
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.theme.toShape

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
    content: @Composable RowScope.() -> Unit
) {
    val style = style.toStyle()
    Button(
        onClick = onClick,
        modifier = modifier,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        enabled = enabled,
        contentColor = style.contentColor,
        contentPadding = contentPadding,
        containerColor = style.containerColor,
        shape = style.shape,
        borderStyle = style.borderStyle,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
internal fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    hapticFeedbackEnabled: Boolean = true,
    enabled: Boolean = true,
    contentColor: ColorToken = ColorToken.Primary,
    contentPadding: PaddingValues = ButtonDefaults.ContentPaddingDefault,
    containerColor: ColorToken = ColorToken.Surface,
    shape: ShapeToken = ShapeToken.Primary,
    borderStyle: BorderStyleToken? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val containerColor = containerColor.toColor().copy(
        alpha = if (enabled) 1f else PaletteTheme.colorScheme.disabledContainerAlpha,
    )
    val contentColor = contentColor.toColor().copy(
        alpha = if (enabled) 1f else PaletteTheme.colorScheme.disabledContentAlpha,
    )
    Surface(
        onClick = onClick,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        hapticFeedbackEnabled = hapticFeedbackEnabled,
        enabled = enabled,
        shape = shape.toShape(),
        color = containerColor,
        contentColor = contentColor,
        borderStyle = borderStyle?.toStyle(),
        interactionSource = interactionSource,
        modifier = modifier.semantics { role = Role.Button }
    ) {
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
                content = content
            )
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
