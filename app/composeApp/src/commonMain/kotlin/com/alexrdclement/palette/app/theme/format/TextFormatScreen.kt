package com.alexrdclement.palette.app.theme.format

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.core.TextFormatDemo
import com.alexrdclement.palette.app.demo.formats.core.TextFormatDemoState
import com.alexrdclement.palette.app.demo.formats.core.rememberTextFormatDemoControl
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.core.TextFormatScheme
import com.alexrdclement.palette.theme.format.core.TextFormatToken
import com.alexrdclement.palette.theme.format.core.update
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TextFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberTextFormatScreenState(formats = themeController.formats)
    val control = rememberTextFormatScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Text",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = state.textFormatsByToken.entries.toList(),
            controls = control.controls,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { (token, _) ->
            BoxWithLabel(
                label = token.name,
            ) {
                TextFormatDemo(
                    state = state.textFormatDemoStatesByToken[token]!!,
                )
            }
        }
    }
}

@Composable
fun rememberTextFormatScreenState(
    formats: Formats,
): TextFormatScreenState {
    return rememberSaveable(
        formats,
        saver = TextFormatScreenStateSaver(formats),
    ) {
        TextFormatScreenState(
            formats = formats,
        )
    }
}

@Stable
class TextFormatScreenState(
    val formats: Formats,
) {
    val textFormatScheme: TextFormatScheme
        get() = formats.textFormats

    val textFormatsByToken = TextFormatToken.entries.associateWith { token ->
        when (token) {
            TextFormatToken.Body -> textFormatScheme.body
            TextFormatToken.Display -> textFormatScheme.display
            TextFormatToken.Headline -> textFormatScheme.headline
            TextFormatToken.Title -> textFormatScheme.title
            TextFormatToken.Label -> textFormatScheme.label
        }
    }

    val textFormatDemoStatesByToken = TextFormatToken.entries.associateWith { token ->
        TextFormatDemoState(
            textFormatInitial = textFormatsByToken[token]!!,
            demoTextFieldState = TextFieldState("Sphinx of black quartz, judge my vow"),
        )
    }
}

fun TextFormatScreenStateSaver(formats: Formats) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        TextFormatScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberTextFormatScreenControl(
    state: TextFormatScreenState,
    themeController: ThemeController,
): TextFormatScreenControl {
    val formatControlByToken = TextFormatToken.entries.associateWith { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController) {
        TextFormatScreenControl(
            state = state,
            themeController = themeController,
            formatControlByToken = formatControlByToken,
        )
    }
}

@Stable
class TextFormatScreenControl(
    val state: TextFormatScreenState,
    val themeController: ThemeController,
    formatControlByToken: Map<TextFormatToken, Control>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        *formatControlByToken.values.toTypedArray(),
    )
}

@Composable
private fun makeControlForToken(
    token: TextFormatToken,
    state: TextFormatScreenState,
    themeController: ThemeController,
): Control {
    val demoControl = rememberTextFormatDemoControl(
        state = state.textFormatDemoStatesByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    textFormats = state.textFormatScheme.update(
                        token = token,
                        value = newValue,
                    )
                )
            )
        }
    )
    return Control.ControlColumn(
        name = token.name,
        controls = { demoControl.controls },
        expandedInitial = false,
        indent = true,
    )
}
