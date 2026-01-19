package com.alexrdclement.palette.components.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.formats.core.inputTransformation
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.TextStyle

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
    val color = textStyle.composeTextStyle.color.takeOrElse { LocalContentColor.current }

    val formatInputTransformation = textStyle.format.inputTransformation
    val combinedInputTransformation = when {
        inputTransformation != null -> inputTransformation.then(formatInputTransformation)
        else -> formatInputTransformation
    }

    BasicTextField(
        state = state,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = combinedInputTransformation,
        textStyle = textStyle.composeTextStyle.copy(color = color),
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
        get() = TextFieldDecorator { innerTextField ->
            Box(
                modifier = Modifier
                    .border(BorderStroke)
                    .padding(PaletteTheme.spacing.small),
            ) {
                innerTextField()
            }
        }
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        Surface {
            TextField(
                state = rememberTextFieldState("text"),
                textStyle = PaletteTheme.styles.text.bodyMedium,
            )
        }
    }
}
