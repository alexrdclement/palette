package com.alexrdclement.palette.theme.component.menu

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.menu.DropdownMenuItemStyle
import com.alexrdclement.palette.components.menu.DropdownMenuStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.component.core.CoreStyles

object MenuStyles {

    val dropdownMenu: DropdownMenuStyle
        @Composable get() = DropdownMenuStyle(
            surfaceStyle = CoreStyles.surface.container,
            itemStyle = DropdownMenuItemStyle(
                indication = PaletteTheme.semantic.indication,
            ),
        )
}
