package com.alexrdclement.palette.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle

/** Resolved styles for [com.alexrdclement.palette.components.navigation]; surfaced via [PaletteTheme.components]. */
object NavigationComponentStyles {

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
