package com.alexrdclement.palette.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.style.background

// Dividers are thin colored rectangles. Canvas.drawLine was the previous implementation;
// a styled Box is equivalent for a 1dp line and connects naturally with the Style API so
// the divider color participates in theming via token-aware StyleScope.background().

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    style: Style = DividerDefaults.style,
) {
    val styleState = remember { MutableStyleState() }
    Box(modifier.fillMaxWidth().height(thickness).styleable(styleState, style))
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    style: Style = DividerDefaults.style,
) {
    val styleState = remember { MutableStyleState() }
    Box(modifier.fillMaxHeight().width(thickness).styleable(styleState, style))
}

object DividerDefaults {
    val Thickness: Dp = 1.dp
    val style: Style get() = Style { background(ColorToken.Outline) }
}
