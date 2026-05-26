package com.alexrdclement.palette.components.menu

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.menu.MenuDefaults.DropdownMenuItemDefaultMaxWidth
import com.alexrdclement.palette.components.menu.MenuDefaults.DropdownMenuItemDefaultMinHeight
import com.alexrdclement.palette.components.menu.MenuDefaults.DropdownMenuItemDefaultMinWidth
import com.alexrdclement.palette.components.menu.MenuDefaults.DropdownMenuVerticalPadding
import com.alexrdclement.palette.components.menu.MenuDefaults.InTransitionDuration
import com.alexrdclement.palette.components.menu.MenuDefaults.OutTransitionDuration
import com.alexrdclement.palette.theme.LocalPaletteIndication
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.style.border
import kotlin.math.max
import kotlin.math.min

// Adapted from Material3's DropdownMenu.

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    scrollState: ScrollState = rememberScrollState(),
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit
) {
    val expandedState = remember { MutableTransitionState(false) }
    expandedState.targetState = expanded

    if (expandedState.currentState || expandedState.targetState) {
        val transformOriginState = remember { mutableStateOf(TransformOrigin.Center) }
        val density = LocalDensity.current
        val popupPositionProvider = remember(offset, density) {
            DropdownMenuPositionProvider(
                offset,
                density
            ) { parentBounds, menuBounds ->
                transformOriginState.value = calculateTransformOrigin(parentBounds, menuBounds)
            }
        }

        Popup(
            onDismissRequest = onDismissRequest,
            popupPositionProvider = popupPositionProvider,
            properties = properties
        ) {
            DropdownMenuContent(
                expandedState = expandedState,
                transformOriginState = transformOriginState,
                scrollState = scrollState,
                modifier = modifier,
                content = content
            )
        }
    }
}

@Composable
internal fun DropdownMenuContent(
    expandedState: MutableTransitionState<Boolean>,
    transformOriginState: MutableState<TransformOrigin>,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // Menu open/close animation.
    val transition = rememberTransition(expandedState, "DropDownMenu")

    val scale by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded
                tween(
                    durationMillis = InTransitionDuration,
                    easing = LinearOutSlowInEasing
                )
            } else {
                // Expanded to dismissed.
                tween(
                    durationMillis = 1,
                    delayMillis = OutTransitionDuration - 1
                )
            }
        },
        label = "DropdownMenuScale"
    ) { expanded ->
        if (expanded) 1f else 0.8f
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (false isTransitioningTo true) {
                // Dismissed to expanded
                tween(durationMillis = 30)
            } else {
                // Expanded to dismissed.
                tween(durationMillis = OutTransitionDuration)
            }
        },
        label = "DropdownMenuAlpha"
    ) { expanded ->
        if (expanded) 1f else 0f
    }

    Surface(
        style = Style { border(BorderStyleToken.Surface) },
        modifier = Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
            transformOrigin = transformOriginState.value
        }
    ) {
        Column(
            modifier = modifier
                .padding(vertical = DropdownMenuVerticalPadding)
                .width(IntrinsicSize.Max)
                .verticalScroll(scrollState),
            content = content
        )
    }
}

// contentColor drives the text/icon color for enabled items.
// disabled items use PaletteTheme.colorScheme.onSurface at disabledContentAlpha — a deliberate
// choice to show a muted neutral rather than a dimmed primary, matching the visual convention
// that disabled menu text recedes further than a simple alpha reduction of the active color.
@Composable
fun DropdownMenuItem(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: Style = MenuDefaults.itemStyle,
    contentColor: Color = MenuDefaults.contentColor,
    contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val resolvedContentColor = if (enabled) {
        contentColor
    } else {
        PaletteTheme.colorScheme.onSurface.copy(
            alpha = PaletteTheme.colorScheme.disabledContentAlpha
        )
    }
    val styleState = rememberUpdatedStyleState(interactionSource) { it.isEnabled = enabled }
    Row(
        modifier = modifier
            .styleable(styleState, style)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = LocalPaletteIndication.current,
            )
            .fillMaxWidth()
            // Preferred min and max width used during the intrinsic measurement.
            .sizeIn(
                minWidth = DropdownMenuItemDefaultMinWidth,
                maxWidth = DropdownMenuItemDefaultMaxWidth,
                minHeight = DropdownMenuItemDefaultMinHeight,
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalContentColor provides resolvedContentColor,
        ) {
            Box(modifier = Modifier.weight(1f)) {
                text()
            }
        }
    }
}

internal fun calculateTransformOrigin(
    anchorBounds: IntRect,
    menuBounds: IntRect
): TransformOrigin {
    val pivotX = when {
        menuBounds.left >= anchorBounds.right -> 0f
        menuBounds.right <= anchorBounds.left -> 1f
        menuBounds.width == 0 -> 0f
        else -> {
            val intersectionCenter =
                (max(anchorBounds.left, menuBounds.left) +
                        min(anchorBounds.right, menuBounds.right)) / 2
            (intersectionCenter - menuBounds.left).toFloat() / menuBounds.width
        }
    }
    val pivotY = when {
        menuBounds.top >= anchorBounds.bottom -> 0f
        menuBounds.bottom <= anchorBounds.top -> 1f
        menuBounds.height == 0 -> 0f
        else -> {
            val intersectionCenter =
                (max(anchorBounds.top, menuBounds.top) +
                        min(anchorBounds.bottom, menuBounds.bottom)) / 2
            (intersectionCenter - menuBounds.top).toFloat() / menuBounds.height
        }
    }
    return TransformOrigin(pivotX, pivotY)
}

object MenuDefaults {

    // No background by default — the menu container Surface provides the background.
    val itemStyle: Style get() = Style { }

    val contentColor: Color
        @Composable get() = PaletteTheme.colorScheme.primary

    // Size defaults.
    internal val MenuVerticalMargin = 48.dp
    private val DropdownMenuItemHorizontalPadding = 12.dp
    internal val DropdownMenuVerticalPadding = 8.dp
    internal val DropdownMenuItemDefaultMinWidth = 112.dp
    internal val DropdownMenuItemDefaultMaxWidth = 280.dp

    // Menu open/close animation.
    internal const val InTransitionDuration = 120
    internal const val OutTransitionDuration = 75

    val DropdownMenuItemContentPadding = PaddingValues(
        horizontal = DropdownMenuItemHorizontalPadding,
        vertical = 0.dp
    )
    val DropdownMenuItemDefaultMinHeight = 48.dp

    val ContainerShape = ShapeToken.Primary
}
