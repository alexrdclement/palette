package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.menu.DropdownMenuItemStyle
import com.alexrdclement.palette.components.menu.DropdownMenuStyle
import com.alexrdclement.palette.theme.PaletteTheme

object MenuStyles {

    val dropdownMenu: DropdownMenuStyle
        @Composable get() = DropdownMenuStyle(
            surfaceStyle = CoreStyles.surface.container,
            itemStyle = DropdownMenuItemStyle(
                indication = PaletteTheme.indication,
            ),
        )
}
