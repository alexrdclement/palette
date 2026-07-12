package com.alexrdclement.palette.theme.component

import com.alexrdclement.palette.theme.component.auth.AuthStyles
import com.alexrdclement.palette.theme.component.color.ColorStyles
import com.alexrdclement.palette.theme.component.core.CoreStyles
import com.alexrdclement.palette.theme.component.demo.DemoStyles
import com.alexrdclement.palette.theme.component.layout.LayoutStyles
import com.alexrdclement.palette.theme.component.media.MediaStyles
import com.alexrdclement.palette.theme.component.menu.MenuStyles
import com.alexrdclement.palette.theme.component.money.MoneyStyles
import com.alexrdclement.palette.theme.component.navigation.NavigationStyles

/**
 * Component-tier accessor: styles that map closely to components. Reached via
 * [com.alexrdclement.palette.theme.PaletteTheme.component]. The [core] grouping holds the
 * foundational styles (text, button, border, surface) that superseded the former `Styles`
 * grouping; the remaining groupings mirror their component families.
 */
object ComponentStyles {
    val core get() = CoreStyles
    val auth get() = AuthStyles
    val color get() = ColorStyles
    val layout get() = LayoutStyles
    val media get() = MediaStyles
    val menu get() = MenuStyles
    val money get() = MoneyStyles
    val navigation get() = NavigationStyles
    val demo get() = DemoStyles
}
