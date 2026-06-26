package com.alexrdclement.palette.app.theme.styles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.border
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BorderStyleScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberBorderStyleScreenState(themeState = themeController)
    val control = rememberBorderStyleScreenControl(state = state, themeController = themeController)

    Scaffold(
        style = PaletteTheme.styles.layout.scaffold,
        topBar = {
            DemoTopBar(
                title = "Border style",
                onNavigateUp = onNavigateUp,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            style = PaletteTheme.styles.demo.list,
            items = BorderStyleToken.entries,
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { token ->
            Box(
                modifier = Modifier
                    .padding(PaletteTheme.spacing.large)
                    .border(state.tokenSet(token)),
            ) {
                Text(
                    text = token.name,
                    style = PaletteTheme.styles.core.text.headline,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun rememberBorderStyleScreenState(
    themeState: ThemeState,
): BorderStyleScreenState {
    return rememberSaveable(
        themeState,
        saver = BorderStyleScreenStateSaver(themeState),
    ) {
        BorderStyleScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class BorderStyleScreenState(
    val themeState: ThemeState,
) {
    /** The current token set for [token] — the theme's current styles. */
    fun tokenSet(token: BorderStyleToken): BorderStyle =
        themeState.styles.border.getValue(token)
}

fun BorderStyleScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        BorderStyleScreenState(
            themeState = themeState,
        )
    }
)

@Composable
fun rememberBorderStyleScreenControl(
    state: BorderStyleScreenState,
    themeController: ThemeController,
): BorderStyleScreenControl {
    return remember(state, themeController) {
        BorderStyleScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class BorderStyleScreenControl(
    val state: BorderStyleScreenState,
    val themeController: ThemeController,
) {
    val styleControls = BorderStyleToken.entries.map { token ->
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
    token: BorderStyleToken,
    state: BorderStyleScreenState,
    themeController: ThemeController,
): Control {
    fun setTokenSet(value: BorderStyle) {
        val styles = state.themeState.styles
        themeController.setStyles(
            styles.copy(border = styles.border + (token to value))
        )
    }

    val contentColorControl = enumControl(
        name = "Color",
        values = { ColorToken.entries },
        selectedValue = { state.tokenSet(token).color },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).update(color = newValue))
        },
    )

    val widthControl = Control.Slider(
        name = "Width",
        value = { state.tokenSet(token).width.value },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).update(width = newValue.dp))
        },
        valueRange = { 0f..100f },
    )

    val shapeControl = enumControl(
        name = "Shape",
        values = { ShapeToken.entries },
        selectedValue = { state.tokenSet(token).shape },
        onValueChange = { newValue ->
            setTokenSet(state.tokenSet(token).update(shape = newValue))
        },
    )

    return Control.ControlColumn(
        name = token.name,
        controls = {
            persistentListOf(
                contentColorControl,
                widthControl,
                shapeControl,
            )
        },
    )
}

fun BorderStyle.update(
    color: ColorToken? = null,
    width: Dp? = null,
    shape: ShapeToken? = null
): BorderStyle = this.copy(
    color = color ?: this.color,
    width = width ?: this.width,
    shape = shape ?: this.shape,
)
