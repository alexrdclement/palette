package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.menu.DropdownMenuStyle
import com.alexrdclement.palette.components.menu.MenuDefaults
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.contentColorFor

/** Resolved styles for [com.alexrdclement.palette.components.menu]; surfaced via [PaletteTheme.components]. */
object MenuComponentStyles {

    val dropdownMenu: DropdownMenuStyle
        @Composable get() {
            val contentColor = PaletteTheme.colorScheme.contentColorFor(PaletteTheme.colorScheme.surface)
            return DropdownMenuStyle(
                surfaceStyle = CoreComponentStyles.surface.copy(
                    borderStyle = PaletteTheme.styles.border.surface.toComponentStyle(),
                ),
                itemColors = MenuDefaults.itemColors(
                    textColor = contentColor,
                    disabledTextColor = contentColor.copy(
                        alpha = PaletteTheme.colorScheme.disabledContentAlpha,
                    ),
                ),
            )
        }
}
