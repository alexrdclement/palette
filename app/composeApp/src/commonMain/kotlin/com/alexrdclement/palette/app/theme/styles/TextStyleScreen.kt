package com.alexrdclement.palette.app.theme.styles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.theme.components.layout.BoxWithLabel
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.format.core.TextFormatToken
import com.alexrdclement.palette.theme.styles.TextStyleToken
import com.alexrdclement.palette.theme.styles.TextStyleTokenSet
import com.alexrdclement.palette.theme.styles.toTextStyle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TextStyleScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberTextStyleScreenState(themeState = themeController)
    val control = rememberTextStyleScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Text",
                onNavigateUp = onNavigateUp,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = TextStyleToken.entries,
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { token ->
            val textStyle = state.tokenSet(token).toTextStyle()
            BoxWithLabel(
                label = token.name,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = state.text,
                    style = textStyle,
                )
            }
        }
    }
}

@Composable
fun rememberTextStyleScreenState(
    themeState: ThemeState,
    demoTextFieldState: TextFieldState = TextFieldState("Sphinx of black quartz, judge my vow"),
): TextStyleScreenState {
    return rememberSaveable(
        themeState,
        demoTextFieldState,
        saver = TextStyleScreenStateSaver(themeState),
    ) {
        TextStyleScreenState(
            themeState = themeState,
            demoTextFieldState = demoTextFieldState,
        )
    }
}

@Stable
class TextStyleScreenState(
    val themeState: ThemeState,
    val demoTextFieldState: TextFieldState = TextFieldState("Sphinx of black quartz, judge my vow"),
) {
    val text by derivedStateOf {
        demoTextFieldState.text.toString()
    }

    fun tokenSet(token: TextStyleToken): TextStyleTokenSet =
        themeState.styles.text.getValue(token)
}

private const val demoTextFieldStateKey = "demoTextFieldState"

fun TextStyleScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf(
            demoTextFieldStateKey to save(state.demoTextFieldState, TextFieldState.Saver, this)
        )
    },
    restore = { map ->
        TextStyleScreenState(
            themeState = themeState,
            demoTextFieldState = restore(map[demoTextFieldStateKey], TextFieldState.Saver)!!,
        )
    }
)

@Composable
fun rememberTextStyleScreenControl(
    state: TextStyleScreenState,
    themeController: ThemeController,
): TextStyleScreenControl {
    return remember(state, themeController) {
        TextStyleScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class TextStyleScreenControl(
    val state: TextStyleScreenState,
    val themeController: ThemeController,
) {
    val textStyleControls = TextStyleToken.entries.map { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }

    val demoTextControl = Control.TextField(
        name = "Demo text",
        textFieldState = state.demoTextFieldState,
        includeLabel = false,
    )

    val controls: PersistentList<Control> = persistentListOf(
        demoTextControl,
        *textStyleControls.toTypedArray(),
    )
}

private fun makeControlForToken(
    token: TextStyleToken,
    state: TextStyleScreenState,
    themeController: ThemeController,
): Control {
    fun setTokenSet(value: TextStyleTokenSet) {
        val styles = state.themeState.styles
        themeController.setStyles(
            styles.copy(text = styles.text + (token to value))
        )
    }

    val typographyTokenControl = enumControl(
        name = "Typography token",
        values = { TypographyToken.entries },
        selectedValue = { state.tokenSet(token).typographyToken },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).copy(typographyToken = newValue))
        },
    )

    val textFormatTokenControl = enumControl(
        name = "Text format token",
        values = { TextFormatToken.entries },
        selectedValue = { state.tokenSet(token).textFormatToken },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).copy(textFormatToken = newValue))
        },
    )

    return Control.ControlColumn(
        name = token.name,
        controls = {
            persistentListOf(
                typographyTokenControl,
                textFormatTokenControl,
            )
        },
    )
}
