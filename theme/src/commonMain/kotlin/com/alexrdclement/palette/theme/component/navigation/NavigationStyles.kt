package com.alexrdclement.palette.theme.component.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.IconSize
import com.alexrdclement.palette.components.core.IconStyle
import com.alexrdclement.palette.components.navigation.BackNavigationButtonStyle
import com.alexrdclement.palette.theme.semantic.color.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.shape.ShapeToken
import com.alexrdclement.palette.theme.semantic.color.toColor
import com.alexrdclement.palette.theme.semantic.shape.toShape

object NavigationStyles {

    val backNavigationButton: BackNavigationButtonStyle
        @Composable get() = BackNavigationButtonStyle(
            buttonStyle = ButtonStyle(
                containerColor = ColorToken.Surface.toColor(),
                shape = ShapeToken.Primary.toShape(),
                contentPadding = PaddingValues(PaletteTheme.semantic.dimension.spacing.none),
                disabledContentAlpha = PaletteTheme.semantic.color.disabledContentAlpha,
                disabledContainerAlpha = PaletteTheme.semantic.color.disabledContainerAlpha,
                indication = PaletteTheme.semantic.indication,
            ),
            iconStyle = IconStyle(
                size = IconSize.Fixed(16.dp),
                color = PaletteTheme.semantic.color.primary,
            ),
        )
}
