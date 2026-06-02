package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.ChevronDirection
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.components.core.ChevronButton as ComponentChevronButton
import com.alexrdclement.palette.components.core.ChevronIcon as ComponentChevronIcon

@Composable
fun ChevronButton(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(PaletteTheme.spacing.medium),
    onClick: () -> Unit,
) {
    ComponentChevronButton(
        direction = direction,
        modifier = modifier,
        style = ButtonStyle(
            contentColor = ColorToken.Primary.toColor(),
            containerColor = ColorToken.Surface.toColor(),
        ),
        contentPadding = contentPadding,
        color = ColorToken.Primary.toColor(),
        onClick = onClick,
    )
}

@Composable
fun ChevronIcon(
    direction: ChevronDirection,
    modifier: Modifier = Modifier,
) {
    ComponentChevronIcon(
        direction = direction,
        modifier = modifier,
        color = PaletteTheme.colorScheme.primary,
    )
}
