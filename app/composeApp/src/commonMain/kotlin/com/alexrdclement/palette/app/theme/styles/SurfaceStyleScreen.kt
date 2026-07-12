package com.alexrdclement.palette.app.theme.styles

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.semantic.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.semantic.ShapeToken
import com.alexrdclement.palette.theme.components.core.Surface
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.component.core.BorderStyleToken
import com.alexrdclement.palette.theme.component.core.SurfaceStyleToken
import com.alexrdclement.palette.theme.component.core.SurfaceStyleTokenSet
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SurfaceStyleScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberSurfaceStyleScreenState(themeState = themeController)
    val control = rememberSurfaceStyleScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Surface style",
                onNavigateUp = onNavigateUp,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = SurfaceStyleToken.entries,
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { token ->
            Surface(
                style = PaletteTheme.component.core.surface[token],
                modifier = Modifier.fillMaxWidth(),
            ) { shapePadding ->
                Text(
                    text = token.name,
                    style = PaletteTheme.component.core.text.headline,
                    modifier = Modifier
                        .padding(shapePadding)
                        .padding(PaletteTheme.semantic.spacing.large),
                )
            }
        }
    }
}

@Composable
fun rememberSurfaceStyleScreenState(
    themeState: ThemeState,
): SurfaceStyleScreenState {
    return rememberSaveable(
        themeState,
        saver = SurfaceStyleScreenStateSaver(themeState),
    ) {
        SurfaceStyleScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class SurfaceStyleScreenState(
    val themeState: ThemeState,
) {
    fun tokenSet(token: SurfaceStyleToken): SurfaceStyleTokenSet =
        themeState.styles.surface.getValue(token)
}

fun SurfaceStyleScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        SurfaceStyleScreenState(
            themeState = themeState,
        )
    }
)

@Composable
fun rememberSurfaceStyleScreenControl(
    state: SurfaceStyleScreenState,
    themeController: ThemeController,
): SurfaceStyleScreenControl {
    return remember(state, themeController) {
        SurfaceStyleScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class SurfaceStyleScreenControl(
    val state: SurfaceStyleScreenState,
    val themeController: ThemeController,
) {
    val styleControls = SurfaceStyleToken.entries.map { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }

    val controls: PersistentList<Control> = persistentListOf(
        *styleControls.toTypedArray(),
    )
}

private fun makeControlForToken(
    token: SurfaceStyleToken,
    state: SurfaceStyleScreenState,
    themeController: ThemeController,
): Control {
    fun setTokenSet(value: SurfaceStyleTokenSet) {
        val styles = state.themeState.styles
        themeController.setStyles(
            styles.copy(surface = styles.surface + (token to value))
        )
    }

    val colorControl = enumControl(
        name = "Color",
        values = { ColorToken.entries },
        selectedValue = { state.tokenSet(token).color },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).copy(color = newValue))
        },
    )

    val shapeControl = enumControl(
        name = "Shape",
        values = { ShapeToken.entries },
        selectedValue = { state.tokenSet(token).shape },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).copy(shape = newValue))
        },
    )

    val borderStyleToggleControl = Control.Toggle(
        name = "Border",
        value = { state.tokenSet(token).borderStyle != null },
        onValueChange = { newValue ->
            val border = if (newValue) {
                state.tokenSet(token).borderStyle ?: BorderStyleToken.Surface
            } else {
                null
            }
            setTokenSet(state.tokenSet(token).copy(borderStyle = border))
        },
    )

    val borderStyleControl = enumControl(
        name = "Border style",
        values = { BorderStyleToken.entries },
        selectedValue = { state.tokenSet(token).borderStyle ?: BorderStyleToken.Surface },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).copy(borderStyle = newValue))
        },
    )

    return Control.ControlColumn(
        name = token.name,
        controls = {
            val borderControls = if (state.tokenSet(token).borderStyle != null) {
                listOf(
                    borderStyleToggleControl,
                    borderStyleControl,
                )
            } else {
                listOf(borderStyleToggleControl)
            }
            persistentListOf(
                colorControl,
                shapeControl,
                *borderControls.toTypedArray()
            )
        },
    )
}
