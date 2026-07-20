package com.alexrdclement.palette.theme.semantic.dimension

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Element/glyph sizes — how big a thing is (distinct from spacing *between* things and padding
 * *inside* things). Named by role rather than magnitude (like `ColorToken`, not `SpacingToken`),
 * because sizes serve distinct purposes at very different scales. Only tokens with a real consumer
 * live here; add more (e.g. `iconMedium`, a hero size) when a component needs them.
 */
data class Size(
    val touchTargetMin: Dp,
    val iconSmall: Dp,
)

val PaletteSize = Size(
    touchTargetMin = 48.dp,
    iconSmall = 16.dp,
)

fun Size.copy(
    token: SizeToken,
    value: Dp,
) = this.copy(
    touchTargetMin = if (token == SizeToken.TouchTargetMin) value else this.touchTargetMin,
    iconSmall = if (token == SizeToken.IconSmall) value else this.iconSmall,
)
