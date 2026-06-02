package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.core.DividerDefaults as ComponentDividerDefaults
import com.alexrdclement.palette.components.core.HorizontalDivider as ComponentHorizontalDivider
import com.alexrdclement.palette.components.core.VerticalDivider as ComponentVerticalDivider

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) = ComponentHorizontalDivider(
    modifier = modifier,
    thickness = thickness,
    color = color,
)

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color,
) = ComponentVerticalDivider(
    modifier = modifier,
    thickness = thickness,
    color = color,
)

object DividerDefaults {
    val Thickness: Dp = ComponentDividerDefaults.Thickness
    val color: Color @Composable get() = PaletteTheme.colorScheme.outline
}
