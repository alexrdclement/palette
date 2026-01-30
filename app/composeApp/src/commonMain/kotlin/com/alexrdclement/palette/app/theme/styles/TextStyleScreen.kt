package com.alexrdclement.palette.app.theme.styles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.Styles
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.format.core.TextFormatToken
import com.alexrdclement.palette.theme.styles.TextStyleScheme
import com.alexrdclement.palette.theme.styles.TextStyleToken
import com.alexrdclement.palette.theme.styles.copy
import com.alexrdclement.palette.theme.styles.toStyle
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
            items = state.textStylesByToken.keys.toList(),
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { token ->
            val tokenSet = state.textStylesByToken[token]!!
            val textStyle = tokenSet.toTextStyle()
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
    val styles: Styles
        get() = themeState.styles

    val textStyleScheme: TextStyleScheme
        get() = styles.textStyleScheme

    val textStylesByToken = TextStyleToken.entries.associateWith { token ->
        token.toStyle(textStyleScheme)
    }

    val text by derivedStateOf {
        demoTextFieldState.text.toString()
    }
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
    val typographyTokenControl = enumControl(
        name = "Typography token",
        values = { TypographyToken.entries },
        selectedValue = { state.textStylesByToken[token]!!.typographyToken },
        onValueChange = { newValue ->
            val textStyleScheme = state.textStyleScheme.copy(
                token = token,
                value = state.textStylesByToken[token]!!.copy(
                    typographyToken = newValue
                )
            )
            val styles = state.styles.copy(
                text = textStyleScheme,
            )
            themeController.setStyles(styles)
        },
    )

    val textFormatTokenControl = enumControl(
        name = "Text format token",
        values = { TextFormatToken.entries },
        selectedValue = { state.textStylesByToken[token]!!.textFormatToken },
        onValueChange = { newValue ->
            val textStyleScheme = state.textStyleScheme.copy(
                token = token,
                value = state.textStylesByToken[token]!!.copy(
                    textFormatToken = newValue
                )
            )
            val styles = state.styles.copy(
                text = textStyleScheme,
            )
            themeController.setStyles(styles)
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
