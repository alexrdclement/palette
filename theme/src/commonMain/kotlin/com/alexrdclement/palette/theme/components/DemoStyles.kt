package com.alexrdclement.palette.theme.components

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.demo.DemoListStyle
import com.alexrdclement.palette.components.demo.DemoStyle
import com.alexrdclement.palette.components.demo.control.ButtonControlStyle
import com.alexrdclement.palette.components.demo.control.CharControlStyle
import com.alexrdclement.palette.components.demo.control.ColorControlStyle
import com.alexrdclement.palette.components.demo.control.ControlsStyle
import com.alexrdclement.palette.components.demo.control.DropdownControlStyle
import com.alexrdclement.palette.components.demo.control.DynamicListControlStyle
import com.alexrdclement.palette.components.demo.control.ExpandableHeaderStyle
import com.alexrdclement.palette.components.demo.control.SliderControlStyle
import com.alexrdclement.palette.components.demo.control.TextFieldControlStyle
import com.alexrdclement.palette.components.demo.control.ToggleControlStyle
import com.alexrdclement.palette.theme.PaletteTheme

/** Resolved style for the [com.alexrdclement.palette.components.demo] framework; surfaced via [PaletteTheme.styles]. */
object DemoStyles {

    val list: DemoListStyle
        @Composable get() = DemoListStyle(demoStyle = style)

    val style: DemoStyle
        @Composable get() {
            val label = TextStyles.labelLarge
            val button = CoreStyles.button.secondary
            val textField = CoreStyles.textField
            return DemoStyle(
                dividerStyle = CoreStyles.divider,
                controlsStyle = ControlsStyle(
                    spacing = PaletteTheme.spacing.medium,
                    contentPadding = PaletteTheme.spacing.small,
                    rowSpacing = PaletteTheme.spacing.small,
                    indent = PaletteTheme.spacing.medium,
                    button = ButtonControlStyle(
                        labelStyle = label,
                        buttonStyle = button,
                    ),
                    slider = SliderControlStyle(
                        labelStyle = label,
                        sliderStyle = CoreStyles.slider,
                        spacing = PaletteTheme.spacing.small,
                    ),
                    color = ColorControlStyle(
                        labelStyle = label,
                        buttonStyle = button,
                        colorPickerStyle = ColorStyles.colorPicker,
                        surfaceStyle = CoreStyles.surface.default,
                        spacing = PaletteTheme.spacing.medium,
                        contentSpacing = PaletteTheme.spacing.small,
                        dialogPadding = PaletteTheme.spacing.large,
                    ),
                    toggle = ToggleControlStyle(
                        labelStyle = label,
                        checkboxStyle = CoreStyles.checkbox,
                        spacing = PaletteTheme.spacing.small,
                    ),
                    char = CharControlStyle(
                        labelStyle = label,
                        textFieldStyle = textField,
                        spacing = PaletteTheme.spacing.small,
                        verticalPadding = PaletteTheme.spacing.small,
                    ),
                    textField = TextFieldControlStyle(
                        labelStyle = label,
                        textFieldStyle = textField,
                        spacing = PaletteTheme.spacing.small,
                        verticalPadding = PaletteTheme.spacing.small,
                    ),
                    dropdown = DropdownControlStyle(
                        labelStyle = label,
                        buttonStyle = button,
                        menuStyle = MenuStyles.dropdownMenu,
                        labelSpacing = PaletteTheme.spacing.small,
                        rowSpacing = PaletteTheme.spacing.medium,
                    ),
                    expandableHeader = ExpandableHeaderStyle(
                        headerStyle = TextStyles.labelSmall,
                        borderColor = PaletteTheme.colorScheme.outline,
                        chevronIconStyle = CoreStyles.chevronIcon,
                        spacing = PaletteTheme.spacing.small,
                        labelPadding = PaletteTheme.spacing.xs,
                    ),
                    dynamicList = DynamicListControlStyle(
                        spacing = PaletteTheme.spacing.medium,
                        itemSpacing = PaletteTheme.spacing.small,
                        itemControlSpacing = PaletteTheme.spacing.xs,
                        indent = PaletteTheme.spacing.medium,
                    ),
                ),
            )
        }
}
