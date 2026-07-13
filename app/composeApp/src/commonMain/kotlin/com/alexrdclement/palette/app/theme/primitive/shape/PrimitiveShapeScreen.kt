package com.alexrdclement.palette.app.theme.primitive.shape

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.components.core.toComposeShape
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import com.alexrdclement.palette.theme.primitive.ShapePrimitives
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

/**
 * Edits the primitive shape tokens ([PaletteTheme.primitive.shape]). A single primitive is shown as
 * the demo subject; the rectangle's corner radius is editable and shared by every semantic shape
 * role that references the rectangle primitive.
 */
@Composable
fun PrimitiveShapeScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberPrimitiveShapeScreenState(themeState = themeController)
    val control = rememberPrimitiveShapeScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Shape",
                onNavigateUp = onNavigateUp,
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
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(96.dp)
                    .border(
                        width = 1.dp,
                        color = PaletteTheme.semantic.color.outline,
                        shape = state.subjectShape.toComposeShape(),
                    )
            )
        }
    }
}

@Composable
fun rememberPrimitiveShapeScreenState(
    themeState: ThemeState,
): PrimitiveShapeScreenState {
    return rememberSaveable(
        themeState,
        saver = PrimitiveShapeScreenStateSaver(themeState),
    ) {
        PrimitiveShapeScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class PrimitiveShapeScreenState(
    val themeState: ThemeState,
    subjectInitial: ShapePrimitiveToken = ShapePrimitiveToken.Rectangle,
) {
    var subject by mutableStateOf(subjectInitial)

    val shapePrimitives: ShapePrimitives
        get() = themeState.primitive.shape

    val subjectShape: Shape
        get() = shapePrimitives.shape(subject)
}

private const val subjectKey = "subject"

fun PrimitiveShapeScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf(subjectKey to state.subject.name)
    },
    restore = { map ->
        PrimitiveShapeScreenState(
            themeState = themeState,
            subjectInitial = (map[subjectKey] as? String)
                ?.let { ShapePrimitiveToken.valueOf(it) }
                ?: ShapePrimitiveToken.Rectangle,
        )
    }
)

@Composable
fun rememberPrimitiveShapeScreenControl(
    state: PrimitiveShapeScreenState,
    themeController: ThemeController,
): PrimitiveShapeScreenControl {
    return remember(state, themeController) {
        PrimitiveShapeScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class PrimitiveShapeScreenControl(
    val state: PrimitiveShapeScreenState,
    val themeController: ThemeController,
) {
    private val subjectControl = enumControl(
        name = "Shape",
        values = { ShapePrimitiveToken.entries },
        selectedValue = { state.subject },
        onValueChange = { state.subject = it },
    )

    private val cornerRadiusControl = Control.Slider(
        name = "Corner radius",
        value = { state.shapePrimitives.rectangle.cornerRadius.value },
        onValueChange = { radius ->
            themeController.updatePrimitive {
                it.copy(shape = it.shape.copy(rectangle = Shape.Rectangle(cornerRadius = radius.dp)))
            }
        },
        valueRange = { 0f..64f },
        stepIncrement = 4f,
    )

    val controls: PersistentList<Control>
        get() = buildList {
            add(subjectControl)
            if (state.subject == ShapePrimitiveToken.Rectangle) {
                add(cornerRadiusControl)
            }
        }.toPersistentList()
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        PrimitiveShapeScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
