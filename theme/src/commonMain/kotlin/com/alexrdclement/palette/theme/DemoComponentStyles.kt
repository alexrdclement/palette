package com.alexrdclement.palette.theme

import androidx.compose.runtime.Composable
import com.alexrdclement.palette.components.demo.DemoStyle
import com.alexrdclement.palette.components.demo.control.CharControlStyle
import com.alexrdclement.palette.components.demo.control.ColorControlStyle
import com.alexrdclement.palette.components.demo.control.ControlsStyle
import com.alexrdclement.palette.components.demo.control.DropdownControlStyle
import com.alexrdclement.palette.components.demo.control.DynamicListControlStyle
import com.alexrdclement.palette.components.demo.control.ExpandableHeaderStyle
import com.alexrdclement.palette.components.demo.control.SliderControlStyle
import com.alexrdclement.palette.components.demo.control.TextFieldControlStyle
import com.alexrdclement.palette.components.demo.control.ToggleControlStyle
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

/** Resolved style for the [com.alexrdclement.palette.components.demo] framework; surfaced via [PaletteTheme.components]. */
object DemoComponentStyles {

    val demo: DemoStyle
        @Composable get() = DemoStyle(
            labelStyle = PaletteTheme.styles.text.labelLarge,
            headerStyle = PaletteTheme.styles.text.labelSmall,
            buttonStyle = CoreComponentStyles.button(ButtonStyleToken.Secondary),
            textFieldStyle = CoreComponentStyles.textField,
            borderColor = PaletteTheme.colorScheme.outline,
            colorPickerStyle = ColorComponentStyles.colorPicker,
            surfaceStyle = CoreComponentStyles.surface,
            dividerStyle = CoreComponentStyles.divider,
            colorControl = ColorControlStyle(
                spacing = PaletteTheme.spacing.medium,
                contentSpacing = PaletteTheme.spacing.small,
                dialogPadding = PaletteTheme.spacing.large,
            ),
            sliderControl = SliderControlStyle(
                spacing = PaletteTheme.spacing.small,
            ),
            toggleControl = ToggleControlStyle(
                spacing = PaletteTheme.spacing.small,
                checkboxStyle = CoreComponentStyles.checkbox,
            ),
            charControl = CharControlStyle(
                spacing = PaletteTheme.spacing.small,
                verticalPadding = PaletteTheme.spacing.small,
            ),
            textFieldControl = TextFieldControlStyle(
                spacing = PaletteTheme.spacing.small,
                verticalPadding = PaletteTheme.spacing.small,
            ),
            dropdownControl = DropdownControlStyle(
                labelSpacing = PaletteTheme.spacing.small,
                rowSpacing = PaletteTheme.spacing.medium,
                menuStyle = MenuComponentStyles.dropdownMenu,
            ),
            expandableHeader = ExpandableHeaderStyle(
                spacing = PaletteTheme.spacing.small,
                labelPadding = PaletteTheme.spacing.xs,
                chevronIconStyle = CoreComponentStyles.chevronIcon,
            ),
            dynamicListControl = DynamicListControlStyle(
                spacing = PaletteTheme.spacing.medium,
                itemSpacing = PaletteTheme.spacing.small,
                itemControlSpacing = PaletteTheme.spacing.xs,
                indent = PaletteTheme.spacing.medium,
            ),
            controls = ControlsStyle(
                spacing = PaletteTheme.spacing.medium,
                contentPadding = PaletteTheme.spacing.small,
                rowSpacing = PaletteTheme.spacing.small,
                indent = PaletteTheme.spacing.medium,
            ),
        )
}
