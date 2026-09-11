package com.alexrdclement.palette.theme.component.core

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.StyledButton
import com.alexrdclement.palette.components.core.Text

/**
 * PROTOTYPE / SPIKE — demonstrates the intended call sites: theme styles are
 * passed as `style` arguments, just like palette does today, and a caller can
 * layer an override that merges on top (styleable is last-wins).
 */
@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
private fun StyledButtonUsage(
    modifier: Modifier = Modifier,
    callerOverride: Style = Style,
) {
    // Plain theme style passed as an argument.
    StyledButton(
        onClick = {},
        modifier = modifier,
        style = ButtonFoundationStyles.primary,
    ) {
        Text("Primary")
    }

    // Look up by token, same as ButtonStyles[token] does today.
    StyledButton(
        onClick = {},
        style = ButtonFoundationStyles[ButtonStyleToken.Secondary],
    ) {
        Text("Secondary")
    }

    // Theme default merged with a caller-provided override (last-wins).
    StyledButton(
        onClick = {},
        style = Style(ButtonFoundationStyles.tertiary, callerOverride),
    ) {
        Text("Tertiary + override")
    }
}
