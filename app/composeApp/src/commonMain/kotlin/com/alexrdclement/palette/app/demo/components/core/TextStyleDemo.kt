package com.alexrdclement.palette.app.demo.components.core

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.alexrdclement.palette.app.demo.formats.core.TextFormatDemoControl
import com.alexrdclement.palette.app.demo.formats.core.TextFormatDemoState
import com.alexrdclement.palette.app.demo.formats.core.TextFormatDemoStateSaver
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.styles.TextStyle
import kotlinx.collections.immutable.persistentListOf

val TextStyleDemoDefault = TextStyle(
    composeTextStyle = ComposeTextStyleDemoDefault,
)

@Composable
fun rememberTextStyleDemoState(
    textStyleInitial: TextStyle = TextStyleDemoDefault,
    demoTextFieldState: TextFieldState = TextFieldState("Hello world"),
) = rememberSaveable(saver = TextStyleDemoStateSaver) {
    TextStyleDemoState(
        textStyleInitial = textStyleInitial,
        demoTextFieldState = demoTextFieldState,
    )
}

@Stable
class TextStyleDemoState(
    textStyleInitial: TextStyle = TextStyleDemoDefault,
    val demoTextFieldState: TextFieldState = TextFieldState("Hello world"),
) {
    val composeTextStyleDemoState = ComposeTextStyleDemoState(
        composeTextStyleInitial = textStyleInitial.composeTextStyle,
    )

    val textFormatDemoState = TextFormatDemoState(
        textFormatInitial = textStyleInitial.format,
        demoTextFieldState = demoTextFieldState,
    )

    val textStyle: TextStyle by derivedStateOf {
        TextStyle(
            composeTextStyle = composeTextStyleDemoState.composeTextStyle,
            format = textFormatDemoState.textFormat,
        )
    }
}

private const val composeTextStyleDemoStateKey = "composeTextStyleDemoState"
private const val textFormatDemoStateKey = "textFormatDemoState"

val TextStyleDemoStateSaver = mapSaverSafe(
    save = { value ->
        mapOf(
            composeTextStyleDemoStateKey to save(
                value.composeTextStyleDemoState,
                ComposeTextStyleDemoStateSaver,
                this
            ),
            textFormatDemoStateKey to save(
                value.textFormatDemoState,
                TextFormatDemoStateSaver(),
                this
            ),
        )
    },
    restore = { map ->
        val composeTextStyleDemoState: ComposeTextStyleDemoState = restore(
            map[composeTextStyleDemoStateKey],
            ComposeTextStyleDemoStateSaver
        )!!

        val textFormatDemoState: TextFormatDemoState = restore(
            map[textFormatDemoStateKey],
            TextFormatDemoStateSaver()
        )!!

        TextStyleDemoState(
            textStyleInitial = TextStyle(
                composeTextStyle = composeTextStyleDemoState.composeTextStyle,
                format = textFormatDemoState.textFormat,
            ),
        )
    }
)

@Composable
fun rememberTextStyleDemoControl(
    state: TextStyleDemoState,
    includeTextFieldControl: Boolean = false,
) = remember(state, includeTextFieldControl) {
    TextStyleDemoControl(
        state = state,
        includeTextFieldControl = includeTextFieldControl,
    )
}

@Stable
class TextStyleDemoControl(
    private val state: TextStyleDemoState,
    includeTextFieldControl: Boolean = false,
) {
    val composeTextStyleControl = ComposeTextStyleDemoControl(
        state = state.composeTextStyleDemoState,
    )

    val composeTextStyleControlColumn = Control.ControlColumn(
        name = "ComposeTextStyle",
        controls = { composeTextStyleControl.controls },
        indent = true,
        expandedInitial = true,
    )

    val textFormatControl = TextFormatDemoControl(
        state = state.textFormatDemoState,
        includeTextFieldControl = includeTextFieldControl,
    )

    val textFormatControlColumn = Control.ControlColumn(
        name = "TextFormat",
        controls = { textFormatControl.controls },
        indent = true,
        expandedInitial = false,
    )

    val controls = persistentListOf(
        composeTextStyleControlColumn,
        textFormatControlColumn,
    )
}
