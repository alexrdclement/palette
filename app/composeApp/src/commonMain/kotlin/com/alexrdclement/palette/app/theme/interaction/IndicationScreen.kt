package com.alexrdclement.palette.app.theme.interaction

import androidx.compose.foundation.layout.fillMaxSize
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
import com.alexrdclement.palette.components.demo.core.ButtonDemo
import com.alexrdclement.palette.components.demo.core.ButtonDemoControl
import com.alexrdclement.palette.components.demo.core.ButtonDemoState
import com.alexrdclement.palette.components.demo.core.ButtonDemoStateSaver
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.PaletteIndicationType
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.toIndication
import com.alexrdclement.palette.theme.toPaletteIndicationType
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun IndicationScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberIndicationScreenState(themeState = themeController)
    val control = rememberIndicationScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Indication",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        Demo(
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            this@Demo.ButtonDemo(
                state = state.buttonDemoState,
                control = control.buttonDemoControl,
            )
        }
    }
}

@Composable
fun rememberIndicationScreenState(
    themeState: ThemeState,
    buttonDemoStateInitial: ButtonDemoState = ButtonDemoState(),
): IndicationScreenState {
    return rememberSaveable(
        themeState,
        buttonDemoStateInitial,
        saver = IndicationScreenStateSaver(themeState),
    ) {
        IndicationScreenState(
            themeState = themeState,
            buttonDemoStateInitial = buttonDemoStateInitial,
        )
    }
}

@Stable
class IndicationScreenState(
    val themeState: ThemeState,
    buttonDemoStateInitial: ButtonDemoState,
) {
    val indicationType
        get() = themeState.indication.toPaletteIndicationType()

    var buttonDemoState by mutableStateOf(buttonDemoStateInitial)
        internal set
}

private const val buttonDemoStateKey = "buttonDemoState"

fun IndicationScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf(
            buttonDemoStateKey to save(state.buttonDemoState, ButtonDemoStateSaver, this)
        )
    },
    restore = { map ->
        IndicationScreenState(
            themeState = themeState,
            buttonDemoStateInitial = restore(map[buttonDemoStateKey], ButtonDemoStateSaver)!!,
        )
    }
)

@Composable
fun rememberIndicationScreenControl(
    state: IndicationScreenState,
    themeController: ThemeController,
): IndicationScreenControl {
    return remember(state, themeController) {
        IndicationScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class IndicationScreenControl(
    val state: IndicationScreenState,
    val themeController: ThemeController,
) {
    val indicationControl = enumControl(
        name = "Indication",
        values = { PaletteIndicationType.entries },
        selectedValue = { state.indicationType },
        onValueChange = { themeController.setIndication(it.toIndication()) },
    )

    val buttonDemoControl = ButtonDemoControl(state = state.buttonDemoState)

    val controls: PersistentList<Control> = persistentListOf(
        indicationControl,
        Control.ControlColumn(
            name = "Demo button controls",
            indent = true,
            controls = { buttonDemoControl.controls },
            expandedInitial = false,
        )
    )
}
