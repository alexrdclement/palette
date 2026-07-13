package com.alexrdclement.palette.app.theme.semantic.shape

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.theme.components.layout.BoxWithLabel
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import com.alexrdclement.palette.theme.semantic.shape.ShapeScheme
import com.alexrdclement.palette.theme.semantic.shape.ShapeToken
import com.alexrdclement.palette.theme.semantic.shape.copy
import com.alexrdclement.palette.theme.semantic.shape.primitiveToken
import com.alexrdclement.palette.theme.semantic.shape.toComposeShape
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Assigns each semantic shape role a primitive shape token. The geometry (e.g. the rectangle's
 * corner radius) is edited on the primitive shape screen and shared by roles that reference it.
 */
@Composable
fun ShapeScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberShapeScreenState(themeState = themeController)
    val control = rememberShapeScreenControl(state = state, themeController = themeController)

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
        DemoList(
            items = ShapeToken.entries.toList(),
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { token ->
            BoxWithLabel(label = token.name) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .border(
                            width = 1.dp,
                            color = PaletteTheme.semantic.color.outline,
                            shape = token.toComposeShape(),
                        )
                )
            }
        }
    }
}

@Composable
fun rememberShapeScreenState(
    themeState: ThemeState,
): ShapeScreenState {
    return rememberSaveable(
        themeState,
        saver = ShapeScreenStateSaver(themeState),
    ) {
        ShapeScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class ShapeScreenState(
    val themeState: ThemeState,
) {
    val shapeScheme: ShapeScheme
        get() = themeState.semantic.shapeScheme
}

fun ShapeScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { mapOf() },
    restore = {
        ShapeScreenState(
            themeState = themeState,
        )
    }
)

@Composable
fun rememberShapeScreenControl(
    state: ShapeScreenState,
    themeController: ThemeController,
): ShapeScreenControl {
    return remember(state, themeController) {
        ShapeScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class ShapeScreenControl(
    val state: ShapeScreenState,
    val themeController: ThemeController,
) {
    private val shapeControls = ShapeToken.entries.map { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }

    val controls: PersistentList<Control> = persistentListOf(
        *shapeControls.toTypedArray(),
    )
}

private fun makeControlForToken(
    token: ShapeToken,
    state: ShapeScreenState,
    themeController: ThemeController,
): Control {
    return enumControl(
        name = token.name,
        values = { ShapePrimitiveToken.entries },
        selectedValue = { token.primitiveToken(state.shapeScheme) },
        onValueChange = { primitiveToken ->
            themeController.updateSemantic {
                it.copy(
                    shapeScheme = it.shapeScheme.copy(
                        token = token,
                        shapePrimitiveToken = primitiveToken,
                    ),
                )
            }
        },
    )
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        ShapeScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
