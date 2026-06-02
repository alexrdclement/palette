package com.alexrdclement.palette.theme.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.toColor
import com.alexrdclement.palette.components.navigation.BackNavigationButton as ComponentBackNavigationButton

@Composable
fun BackNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ComponentBackNavigationButton(
        onClick = onClick,
        modifier = modifier,
        style = ButtonStyle(
            contentColor = ColorToken.Primary.toColor(),
            containerColor = ColorToken.Surface.toColor(),
        ),
        contentPadding = PaddingValues(PaletteTheme.spacing.medium),
        iconColor = PaletteTheme.colorScheme.primary,
    )
}
