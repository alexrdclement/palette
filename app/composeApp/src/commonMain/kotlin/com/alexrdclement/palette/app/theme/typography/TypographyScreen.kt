package com.alexrdclement.palette.app.theme.typography

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.core.ComposeTextStyleDemoControl
import com.alexrdclement.palette.components.demo.core.rememberComposeTextStyleDemoState
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.formats.core.TextFormat
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.copy
import com.alexrdclement.palette.theme.styles.TextStyle
import com.alexrdclement.palette.theme.toComposeTextStyle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TypographyScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberTypographyScreenState(themeState = themeController)
    val control = rememberTypographyScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Typography",
                onNavigateUp = onNavigateUp,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = TypographyToken.entries.toList(),
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { textStyle ->
            BoxWithLabel(
                label = textStyle.name,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = state.text,
                    style = TextStyle(
                        composeTextStyle = textStyle.toComposeTextStyle(),
                        format = TextFormat(),
                    ),
                )
            }
        }
    }
}

@Composable
fun rememberTypographyScreenState(
    themeState: ThemeState,
    initialText: String = "Sphinx of black quartz, judge my vow",
): TypographyScreenState {
    val textFieldState = rememberTextFieldState(initialText = initialText)
    return rememberSaveable(
        themeState,
        saver = TypographyScreenStateSaver(themeState),
    ) {
        TypographyScreenState(
            themeState = themeState,
            textFieldState = textFieldState,
        )
    }
}

@Stable
class TypographyScreenState(
    val themeState: ThemeState,
    val textFieldState: TextFieldState,
) {
    val typography
        get() = themeState.typography

    val text: String
        get() = textFieldState.text.toString()
}

private val textFieldKey = "textField"

fun TypographyScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf(
            textFieldKey to save(state.textFieldState, TextFieldState.Saver, this),
        )
    },
    restore = { map ->
        TypographyScreenState(
            themeState = themeState,
            textFieldState = restore(map[textFieldKey], TextFieldState.Saver)!!,
        )
    }
)

@Composable
fun rememberTypographyScreenControl(
    state: TypographyScreenState,
    themeController: ThemeController,
): TypographyScreenControl {
    val textFieldControl = Control.TextField(
        name = "Sample text",
        includeLabel = false,
        textFieldState = state.textFieldState,
    )

    val tokenControls = TypographyToken.entries.map { token ->
        val baseStyle = token.toComposeTextStyle(state.typography)

        val composeTextStyleState = rememberComposeTextStyleDemoState(
            composeTextStyleInitial = baseStyle,
        )

        LaunchedEffect(composeTextStyleState.composeTextStyle) {
            snapshotFlow { composeTextStyleState.composeTextStyle }.collect { newStyle ->
                val typography = state.typography.copy(
                    token = token,
                    textStyle = newStyle,
                )
                themeController.setTypography(typography)
            }
        }

        val control = ComposeTextStyleDemoControl(state = composeTextStyleState)

        Control.ControlColumn(
            name = token.name,
            controls = { control.controls },
            indent = true,
            expandedInitial = false,
        )
    }

    return remember(textFieldControl, tokenControls) {
        TypographyScreenControl(
            textFieldControl = textFieldControl,
            tokenControls = tokenControls,
        )
    }
}

@Stable
class TypographyScreenControl(
    val textFieldControl: Control.TextField,
    val tokenControls: List<Control.ControlColumn>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        textFieldControl,
        *tokenControls.toTypedArray(),
    )
}
