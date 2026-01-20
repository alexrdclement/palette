package com.alexrdclement.palette.components.demo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.demo.core.TextDemo
import com.alexrdclement.palette.components.demo.core.TextDemoControl
import com.alexrdclement.palette.components.demo.core.TextDemoState
import com.alexrdclement.palette.components.demo.core.TextFieldDemo
import com.alexrdclement.palette.components.demo.core.TextFieldDemoControl
import com.alexrdclement.palette.components.demo.core.TextFieldDemoState
import com.alexrdclement.palette.components.demo.geometry.CircleDemo
import com.alexrdclement.palette.components.demo.geometry.GridDemo
import com.alexrdclement.palette.components.demo.geometry.GridDemoControl
import com.alexrdclement.palette.components.demo.geometry.GridDemoState
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.collections.immutable.toPersistentList

enum class ComponentDemoType {
    Circle,
    CircleOutline,
    Grid,
    Text,
    TextField,
}

@Composable
fun ComponentDemo(
    modifier: Modifier = Modifier,
    state: ComponentDemoState = rememberComponentDemoState(),
    control: ComponentDemoControl = rememberComponentDemoControl(state = state),
) {
    Demo(
        controls = control.controls,
        modifier = modifier
            .fillMaxSize()
    ) {
        ComponentDemo(
            state = state,
            control = control,
        )
    }
}

@Composable
fun DemoScope.ComponentDemo(
    modifier: Modifier = Modifier,
    state: ComponentDemoState = rememberComponentDemoState(),
    control: ComponentDemoControl = rememberComponentDemoControl(state = state),
) {
    when (state.demoType) {
        ComponentDemoType.Circle -> CircleDemo(
            modifier = modifier
                .fillMaxSize()
        )
        ComponentDemoType.CircleOutline -> CircleDemo(
            drawStyle = Stroke(2f),
            modifier = modifier
                .fillMaxSize()
        )
        ComponentDemoType.Grid -> GridDemo(
            state = state.gridDemoState,
            modifier = modifier
                .fillMaxSize()
        )
        ComponentDemoType.Text -> TextDemo(
            state = state.textDemoState,
            control = control.textDemoControl,
            modifier = modifier
        )
        ComponentDemoType.TextField -> TextFieldDemo(
            state = state.textFieldDemoState,
            control = control.textFieldDemoControl,
            modifier = modifier
        )
    }
}

@Composable
fun rememberComponentDemoState(
    componentDemoTypeInitial: ComponentDemoType = ComponentDemoType.Circle,
): ComponentDemoState {
    val density = LocalDensity.current
    val color = PaletteTheme.colorScheme.primary
    return rememberSaveable(
        density,
        color,
        saver = ComponentDemoStateSaver(
            density = density,
            color = color,
        ),
    ) {
        ComponentDemoState(
            density = density,
            color = color,
            componentDemoTypeInitial = componentDemoTypeInitial,
        )
    }
}

@Stable
class ComponentDemoState(
    density: Density,
    color: Color,
    componentDemoTypeInitial: ComponentDemoType = ComponentDemoType.Circle,
    gridDemoStateInitial: GridDemoState = GridDemoState(
        density = density,
        color = color,
    ),
    textDemoStateInitial: TextDemoState = TextDemoState(),
    textFieldDemoStateInitial: TextFieldDemoState = TextFieldDemoState(
        initialText = "Hello world",
    ),
) {
    var demoType by mutableStateOf(componentDemoTypeInitial)
        internal set

    val gridDemoState = gridDemoStateInitial

    val textDemoState = textDemoStateInitial

    val textFieldDemoState = textFieldDemoStateInitial
}

private const val demoTypeKey = "demoType"
private const val gridDemoStateKey = "gridDemoState"
private const val textDemoStateKey = "textDemoState"
private const val textFieldDemoStateKey = "textFieldDemoState"

fun ComponentDemoStateSaver(
    density: Density,
    color: Color,
) = mapSaverSafe(
    save = { value ->
        mapOf(
            demoTypeKey to value.demoType,
            gridDemoStateKey to save(value.gridDemoState, com.alexrdclement.palette.components.demo.geometry.GridDemoStateSaver, this),
            textDemoStateKey to save(value.textDemoState, com.alexrdclement.palette.components.demo.core.TextDemoStateSaver, this),
            textFieldDemoStateKey to save(value.textFieldDemoState, com.alexrdclement.palette.components.demo.core.TextFieldDemoStateSaver, this),
        )
    },
    restore = { map ->
        ComponentDemoState(
            density = density,
            color = color,
            componentDemoTypeInitial = map[demoTypeKey] as ComponentDemoType,
            gridDemoStateInitial = restore(map[gridDemoStateKey], com.alexrdclement.palette.components.demo.geometry.GridDemoStateSaver)!!,
            textDemoStateInitial = restore(map[textDemoStateKey], com.alexrdclement.palette.components.demo.core.TextDemoStateSaver)!!,
            textFieldDemoStateInitial = restore(map[textFieldDemoStateKey], com.alexrdclement.palette.components.demo.core.TextFieldDemoStateSaver)!!,
        )
    },
)

@Composable
fun rememberComponentDemoControl(
    state: ComponentDemoState = rememberComponentDemoState(),
): ComponentDemoControl {
    return remember(state) { ComponentDemoControl(state) }
}

@Stable
class ComponentDemoControl(
    val state: ComponentDemoState,
) {
    val demoTypeControl = enumControl(
        name = "Component",
        values = { ComponentDemoType.entries },
        selectedValue = { state.demoType },
        onValueChange = { state.demoType = it },
    )

    val gridDemoControl = GridDemoControl(state = state.gridDemoState)
    val gridDemoControls = Control.ControlColumn(
        name = "Grid",
        controls = { gridDemoControl.controls },
        expandedInitial = true,
    )

    val textDemoControl = TextDemoControl(state = state.textDemoState)
    val textDemoControls = Control.ControlColumn(
        name = "Text",
        controls = { textDemoControl.controls },
        expandedInitial = true,
    )

    val textFieldDemoControl = TextFieldDemoControl(state = state.textFieldDemoState)
    val textFieldDemoControls = Control.ControlColumn(
        name = "Text Field",
        controls = { textFieldDemoControl.controls },
        expandedInitial = true,
    )

    val controls
        get() = buildList {
            add(demoTypeControl)
            when (state.demoType) {
                ComponentDemoType.Circle,
                ComponentDemoType.CircleOutline,
                -> Unit
                ComponentDemoType.Grid -> add(gridDemoControls)
                ComponentDemoType.Text -> add(textDemoControls)
                ComponentDemoType.TextField -> add(textFieldDemoControls)
            }
        }.toPersistentList()
}
