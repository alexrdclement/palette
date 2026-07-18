package com.alexrdclement.palette.theme.component.navigation

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.semantic.color.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.dimensions.PaddingValueToken
import com.alexrdclement.palette.theme.semantic.dimensions.toPaddingValues
import com.alexrdclement.palette.theme.semantic.shape.ShapeToken
import com.alexrdclement.palette.theme.semantic.color.toColor
import com.alexrdclement.palette.theme.semantic.shape.toShape

object NavigationStyles {

    val backNavigationButton: BackNavigationButtonStyle
        @Composable get() = BackNavigationButtonStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Surface.toColor(),
                shape = ShapeToken.Primary.toShape(),
                contentPadding = PaddingValueToken.Compact.toPaddingValues(),
                disabledContentAlpha = PaletteTheme.semantic.color.disabledContentAlpha,
                disabledContainerAlpha = PaletteTheme.semantic.color.disabledContainerAlpha,
                indication = PaletteTheme.semantic.indication,
            ),
            iconColor = PaletteTheme.semantic.color.primary,
        )
}
