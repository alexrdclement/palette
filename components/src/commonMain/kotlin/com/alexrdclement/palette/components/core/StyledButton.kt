package com.alexrdclement.palette.components.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics

/**
 * PROTOTYPE / SPIKE — Style-API version of [Button].
 *
 * Follows the "Theming with Styles" pattern
 * (https://developer.android.com/develop/ui/compose/styles/theming): the button
 * takes a foundation [Style] as an argument — exactly like palette's existing
 * `style` parameters — defaulting to the empty [Style], and applies it with the
 * `styleable` modifier instead of threading container color / shape / border /
 * padding / graphicsLayer by hand.
 *
 * A theme supplies the concrete style (see ButtonFoundationStyles), and callers
 * can still pass an override that merges on top (styleable is last-wins).
 */
@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun StyledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: Style = Style,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val styleState = remember(interactionSource) { MutableStyleState(interactionSource) }
    styleState.isEnabled = enabled
    Row(
        modifier = modifier
            .semantics { role = Role.Button }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            )
            .styleable(styleState, style)
            .defaultMinSize(
                minWidth = ButtonDefaults.MinWidth,
                minHeight = ButtonDefaults.MinHeight,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}
