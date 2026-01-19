package com.alexrdclement.palette.modifiers.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.components.demo.subject.DemoSubject
import com.alexrdclement.palette.components.demo.subject.DemoSubjectType
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.util.mapSaverSafe
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ModifierDemo(
    demoModifier: Modifier,
    controls: PersistentList<Control>,
    modifier: Modifier = Modifier,
    state: ModifierDemoState = rememberModifierDemoState(),
    control: ModifierDemoControl = rememberModifierDemoControl(state = state, modifierControls = controls),
) {
    Demo(
        controls = control.controls,
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DemoSubject(
                demoSubject = state.demoSubject,
                modifier = demoModifier,
            )
        }
    }
}


@Composable
fun rememberModifierDemoState(
    demoSubjectTypeInitial: DemoSubjectType = DemoSubjectType.Circle,
): ModifierDemoState {
    return rememberSaveable(saver = ModifierDemoStateSaver) {
        ModifierDemoState(
            demoSubjectTypeInitial = demoSubjectTypeInitial,
        )
    }
}

@Stable
class ModifierDemoState(
    demoSubjectTypeInitial: DemoSubjectType = DemoSubjectType.Circle,
) {
    var demoSubject by mutableStateOf(demoSubjectTypeInitial)
        internal set
}

private const val demoSubjectKey = "demoSubject"

val ModifierDemoStateSaver = mapSaverSafe(
    save = { value ->
        mapOf(
            demoSubjectKey to value.demoSubject,
        )
    },
    restore = { map ->
        ModifierDemoState(
            demoSubjectTypeInitial = map[demoSubjectKey] as DemoSubjectType,
        )
    },
)

@Composable
fun rememberModifierDemoControl(
    modifierControls: PersistentList<Control> = persistentListOf(),
    state: ModifierDemoState = rememberModifierDemoState(),
): ModifierDemoControl {
    return remember(state) { ModifierDemoControl(modifierControls, state) }
}

@Stable
class ModifierDemoControl(
    val modifierControls: PersistentList<Control>,
    val state: ModifierDemoState,
) {
    val subjectControl = enumControl(
        name = "Subject",
        values = { DemoSubjectType.entries },
        selectedValue = { state.demoSubject },
        onValueChange = { state.demoSubject = it },
    )

    val controls
        get() = persistentListOf(
            subjectControl,
            *modifierControls.toTypedArray(),
        )
}
