package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.toColor

/** Resolved styles for [com.alexrdclement.palette.components.navigation]; surfaced via [PaletteTheme.styles]. */
object NavigationStyles {

    val backNavigationButton: BackNavigationButtonStyle
        @Composable get() = BackNavigationButtonStyle(
            buttonStyle = ButtonStyle(
                contentColor = ColorToken.Primary.toColor(),
                containerColor = ColorToken.Surface.toColor(),
                indication = PaletteTheme.indication,
            ),
            contentPadding = PaddingValues(PaletteTheme.spacing.medium),
            iconColor = PaletteTheme.colorScheme.primary,
        )
}
