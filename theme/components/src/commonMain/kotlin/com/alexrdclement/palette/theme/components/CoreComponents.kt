package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.core.Surface as BaseSurface

/**
 * [com.alexrdclement.palette.components.core.Surface] pre-bound to the palette theme: `style`
 * defaults to [PaletteTheme.styles]`.core.surface`. Pass `style` to override.
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    style: SurfaceStyle = PaletteTheme.styles.core.surface,
    content: @Composable (PaddingValues) -> Unit,
) = BaseSurface(
    modifier = modifier,
    style = style,
    content = content,
)

@Composable
fun Surface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: SurfaceStyle = PaletteTheme.styles.core.surface,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    hapticFeedbackEnabled: Boolean = true,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable (PaddingValues) -> Unit,
) = BaseSurface(
    onClick = onClick,
    modifier = modifier,
    style = style,
    onLongClickLabel = onLongClickLabel,
    onLongClick = onLongClick,
    onDoubleClick = onDoubleClick,
    hapticFeedbackEnabled = hapticFeedbackEnabled,
    enabled = enabled,
    interactionSource = interactionSource,
    content = content,
)
