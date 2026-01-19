package com.alexrdclement.palette.app.theme.styles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.core.TextAlign
import com.alexrdclement.palette.app.demo.components.core.TextDemo
import com.alexrdclement.palette.app.demo.components.core.TextDemoControl
import com.alexrdclement.palette.app.demo.components.core.TextDemoState
import com.alexrdclement.palette.app.demo.components.core.TextDemoStateSaver
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.Styles
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.copy
import com.alexrdclement.palette.theme.styles.toStyle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ButtonStyleScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberButtonStyleScreenState(themeState = themeController)
    val control = rememberButtonStyleScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Button style",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = state.buttonStylesByToken.keys.toList(),
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { style ->
            Button(
                style = style,
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                this@DemoList.TextDemo(
                    state = state.textDemoState,
                    control = control.textDemoControl,
                )
            }
        }
    }
}

@Composable
fun rememberButtonStyleScreenState(
    themeState: ThemeState,
    textDemoStateInitial: TextDemoState = TextDemoState(
        textAlignInitial = TextAlign.Center,
        autoSizeInitial = true,
    ),
): ButtonStyleScreenState {
    return rememberSaveable(
        themeState,
        textDemoStateInitial,
        saver = ButtonStyleScreenStateSaver(themeState),
    ) {
        ButtonStyleScreenState(
            themeState = themeState,
            textDemoStateInitial = textDemoStateInitial,
        )
    }
}

@Stable
class ButtonStyleScreenState(
    val themeState: ThemeState,
    textDemoStateInitial: TextDemoState,
) {
    val styles: Styles
        get() = themeState.styles

    val buttonStyles
        get() = styles.button

    val buttonStylesByToken = ButtonStyleToken.entries.associateWith { token ->
        token.toStyle(buttonStyles)
    }

    var textDemoState by mutableStateOf(textDemoStateInitial)
        internal set
}

private const val textDemoStateKey = "buttonDemoState"

fun ButtonStyleScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf(
            textDemoStateKey to save(state.textDemoState, TextDemoStateSaver, this)
        )
    },
    restore = { map ->
        ButtonStyleScreenState(
            themeState = themeState,
            textDemoStateInitial = restore(map[textDemoStateKey], TextDemoStateSaver)!!,
        )
    }
)

@Composable
fun rememberButtonStyleScreenControl(
    state: ButtonStyleScreenState,
    themeController: ThemeController,
): ButtonStyleScreenControl {
    return remember(state, themeController) {
        ButtonStyleScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class ButtonStyleScreenControl(
    val state: ButtonStyleScreenState,
    val themeController: ThemeController,
) {
    val buttonStyleControls = ButtonStyleToken.entries.map { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }

    val textDemoControl = TextDemoControl(state.textDemoState)

    val controls: PersistentList<Control> = persistentListOf(
        *buttonStyleControls.toTypedArray(),
        Control.ControlColumn(
            name = "Demo text controls",
            indent = true,
            controls = { textDemoControl.controls },
            expandedInitial = false,
        )
    )
}

private fun makeControlForToken(
    token: ButtonStyleToken,
    state: ButtonStyleScreenState,
    themeController: ThemeController,
): Control {
    val contentColorControl = enumControl(
        name = "Content color",
        values = { ColorToken.entries },
        selectedValue = { state.buttonStylesByToken[token]!!.contentColor },
        onValueChange = { newValue ->
            val buttonStyles = state.buttonStyles.copy(
                token = token,
                value = state.buttonStylesByToken[token]!!.copy(
                    contentColor = newValue
                )
            )
            val styles = state.styles.copy(
                button = buttonStyles,
            )
            themeController.setStyles(styles)
        },
    )

    val containerColorControl = enumControl(
        name = "Container color",
        values = { ColorToken.entries },
        selectedValue = { state.buttonStylesByToken[token]!!.containerColor },
        onValueChange = { newValue ->
            val buttonStyles = state.buttonStyles.copy(
                token = token,
                value = state.buttonStylesByToken[token]!!.copy(
                    containerColor = newValue
                )
            )
            val styles = state.styles.copy(
                button = buttonStyles,
            )
            themeController.setStyles(styles)
        },
    )

    val shapeControl = enumControl(
        name = "Shape",
        values = { ShapeToken.entries },
        selectedValue = { state.buttonStylesByToken[token]!!.shape },
        onValueChange = { newValue ->
            val buttonStyles = state.buttonStyles.copy(
                token = token,
                value = state.buttonStylesByToken[token]!!.copy(
                    shape = newValue,
                )
            )
            val styles = state.styles.copy(
                button = buttonStyles,
            )
            themeController.setStyles(styles)
        },
    )

    val borderStyleToggleControl = Control.Toggle(
        name = "Border",
        value = { state.buttonStylesByToken[token]!!.borderStyle != null },
        onValueChange = { newValue ->
            val border = if (newValue) {
                state.buttonStylesByToken[token]!!.borderStyle ?: when (token) {
                    ButtonStyleToken.Primary -> BorderStyleToken.Primary
                    ButtonStyleToken.Secondary -> BorderStyleToken.Secondary
                    ButtonStyleToken.Tertiary -> BorderStyleToken.Tertiary
                }
            } else {
                null
            }
            val buttonStyles = state.buttonStyles.copy(
                token = token,
                value = state.buttonStylesByToken[token]!!.copy(
                    borderStyle = border,
                )
            )
            val styles = state.styles.copy(
                button = buttonStyles,
            )
            themeController.setStyles(styles)
        },
    )

    val borderStyleControl = enumControl(
        name = "Border style",
        values = { BorderStyleToken.entries },
        selectedValue = {
            state.buttonStylesByToken[token]!!.borderStyle ?: when (token) {
                ButtonStyleToken.Primary -> BorderStyleToken.Primary
                ButtonStyleToken.Secondary -> BorderStyleToken.Secondary
                ButtonStyleToken.Tertiary -> BorderStyleToken.Tertiary
            }
        },
        onValueChange = { newValue ->
            val buttonStyles = state.buttonStyles.copy(
                token = token,
                value = state.buttonStylesByToken[token]!!.copy(
                    borderStyle = newValue,
                )
            )
            val styles = state.styles.copy(
                button = buttonStyles,
            )
            themeController.setStyles(styles)
        },
    )

    return Control.ControlColumn(
        name = token.name,
        controls = {
            val borderControls = if (state.buttonStylesByToken[token]!!.borderStyle != null) {
                listOf(
                    borderStyleToggleControl,
                    borderStyleControl,
                )
            } else {
                listOf(borderStyleToggleControl)
            }
            persistentListOf(
                contentColorControl,
                containerColorControl,
                shapeControl,
                *borderControls.toTypedArray()
            )
        },
    )
}
