package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.menu.DropdownMenuStyle
import com.alexrdclement.palette.components.menu.MenuDefaults
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.contentColorFor

/** Resolved styles for [com.alexrdclement.palette.components.menu]; surfaced via [PaletteTheme.styles]. */
object MenuStyles {

    val dropdownMenu: DropdownMenuStyle
        @Composable get() {
            val contentColor = PaletteTheme.colorScheme.contentColorFor(PaletteTheme.colorScheme.surface)
            return DropdownMenuStyle(
                surfaceStyle = CoreStyles.surface.container,
                itemColors = MenuDefaults.itemColors(
                    textColor = contentColor,
                    disabledTextColor = contentColor.copy(
                        alpha = PaletteTheme.colorScheme.disabledContentAlpha,
                    ),
                ),
            )
        }
}
