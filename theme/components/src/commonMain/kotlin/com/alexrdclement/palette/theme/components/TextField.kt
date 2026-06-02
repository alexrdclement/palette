package com.alexrdclement.palette.theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.components.core.TextField as ComponentTextField
import com.alexrdclement.palette.components.core.TextFieldDefaults as ComponentTextFieldDefaults

@Composable
fun TextField(
    state: TextFieldState,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    inputTransformation: InputTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onTextLayout: (Density.(getResult: () -> TextLayoutResult?) -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = TextFieldDefaults.CursorBrush,
    outputTransformation: OutputTransformation? = null,
    decorator: TextFieldDecorator? = TextFieldDefaults.TextFieldDecorator,
    scrollState: ScrollState = rememberScrollState(),
) {
    ComponentTextField(
        state = state,
        textStyle = textStyle,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = inputTransformation,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        outputTransformation = outputTransformation,
        decorator = decorator,
        scrollState = scrollState,
    )
}

object TextFieldDefaults {
    val CursorBrush: Brush
        @Composable
        get() = SolidColor(PaletteTheme.colorScheme.primary)

    val BorderStroke: BorderStroke
        @Composable
        get() = BorderStroke(
            width = 1.dp,
            color = PaletteTheme.colorScheme.outline,
        )

    val TextFieldDecorator: TextFieldDecorator
        @Composable
        get() = ComponentTextFieldDefaults.textFieldDecorator(
            borderStroke = BorderStroke,
            padding = PaletteTheme.spacing.small,
        )
}
