package com.alexrdclement.palette.theme.component.demo

import androidx.compose.foundation.layout.PaddingValues
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
import com.alexrdclement.palette.theme.component.color.ColorStyles
import com.alexrdclement.palette.theme.component.core.CoreStyles
import com.alexrdclement.palette.theme.component.core.TextStyles
import com.alexrdclement.palette.theme.component.menu.MenuStyles

object DemoStyles {

    val list: DemoListStyle
        @Composable get() = DemoListStyle(
            demoStyle = style,
            itemSpacing = PaletteTheme.semantic.dimension.spacing.large,
            contentPadding = PaddingValues(PaletteTheme.semantic.dimension.spacing.medium),
        )

    val style: DemoStyle
        @Composable get() {
            val label = TextStyles.labelLarge
            val button = CoreStyles.button.secondary
            val textField = CoreStyles.textField
            return DemoStyle(
                dividerStyle = CoreStyles.divider,
                controlsPadding = PaletteTheme.semantic.dimension.spacing.medium,
                controlsStyle = ControlsStyle(
                    spacing = PaletteTheme.semantic.dimension.spacing.medium,
                    contentPadding = PaletteTheme.semantic.dimension.spacing.small,
                    rowSpacing = PaletteTheme.semantic.dimension.spacing.small,
                    indent = PaletteTheme.semantic.dimension.spacing.medium,
                    button = ButtonControlStyle(
                        labelStyle = label,
                        buttonStyle = button,
                    ),
                    slider = SliderControlStyle(
                        labelStyle = label,
                        sliderStyle = CoreStyles.slider,
                        spacing = PaletteTheme.semantic.dimension.spacing.small,
                    ),
                    color = ColorControlStyle(
                        labelStyle = label,
                        buttonStyle = button,
                        colorDisplayStyle = ColorStyles.colorDisplay,
                        colorPickerDialogContentStyle = ColorStyles.colorPickerDialogContent,
                        surfaceStyle = CoreStyles.surface.container,
                        spacing = PaletteTheme.semantic.dimension.spacing.medium,
                        contentSpacing = PaletteTheme.semantic.dimension.spacing.small,
                    ),
                    toggle = ToggleControlStyle(
                        labelStyle = label,
                        checkboxStyle = CoreStyles.checkbox,
                        spacing = PaletteTheme.semantic.dimension.spacing.small,
                    ),
                    char = CharControlStyle(
                        labelStyle = label,
                        textFieldStyle = textField,
                        spacing = PaletteTheme.semantic.dimension.spacing.small,
                        verticalPadding = PaletteTheme.semantic.dimension.spacing.small,
                    ),
                    textField = TextFieldControlStyle(
                        labelStyle = label,
                        textFieldStyle = textField,
                        spacing = PaletteTheme.semantic.dimension.spacing.small,
                        verticalPadding = PaletteTheme.semantic.dimension.spacing.small,
                    ),
                    dropdown = DropdownControlStyle(
                        labelStyle = label,
                        buttonStyle = button,
                        menuStyle = MenuStyles.dropdownMenu,
                        labelSpacing = PaletteTheme.semantic.dimension.spacing.small,
                        rowSpacing = PaletteTheme.semantic.dimension.spacing.medium,
                    ),
                    expandableHeader = ExpandableHeaderStyle(
                        headerStyle = TextStyles.labelSmall,
                        borderColor = PaletteTheme.semantic.color.outline,
                        chevronIconStyle = CoreStyles.chevronIcon,
                        spacing = PaletteTheme.semantic.dimension.spacing.small,
                        labelPadding = PaddingValues(PaletteTheme.semantic.dimension.spacing.xs),
                        indication = PaletteTheme.semantic.indication,
                    ),
                    dynamicList = DynamicListControlStyle(
                        spacing = PaletteTheme.semantic.dimension.spacing.medium,
                        itemSpacing = PaletteTheme.semantic.dimension.spacing.small,
                        itemControlSpacing = PaletteTheme.semantic.dimension.spacing.xs,
                        indent = PaletteTheme.semantic.dimension.spacing.medium,
                    ),
                ),
            )
        }
}
