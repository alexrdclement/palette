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
import com.alexrdclement.palette.theme.Styles
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.modifiers.BorderStyle
import com.alexrdclement.palette.theme.modifiers.BorderStyleScheme
import com.alexrdclement.palette.theme.modifiers.BorderStyleToken
import com.alexrdclement.palette.theme.modifiers.border
import com.alexrdclement.palette.theme.modifiers.toStyle
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
            items = state.borderStylesByToken.keys.toList(),
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { style ->
            Box(
                modifier = Modifier
                    .padding(PaletteTheme.spacing.large)
                    .border(style.toStyle(state.borderStyleScheme)),
            ) {
                Text(
                    text = style.name,
                    style = PaletteTheme.styles.text.headline,
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
    val styles: Styles
        get() = themeState.styles

    val borderStyleScheme
        get() = styles.border

    val borderStylesByToken = BorderStyleToken.entries.associateWith { token ->
        token.toStyle(borderStyleScheme)
    }
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
    val contentColorControl = enumControl(
        name = "Color",
        values = { ColorToken.entries },
        selectedValue = { state.borderStylesByToken[token]!!.color },
        onValueChange = { newValue ->
            val styles = state.styles.copy(
                border = state.styles.border.update(
                    token = token,
                    color = newValue,
                ),
            )
            themeController.setStyles(styles)
        },
    )

    val widthControl = Control.Slider(
        name = "Width",
        value = { state.borderStylesByToken[token]!!.width?.value ?: 0f },
        onValueChange = { newValue ->
            val styles = state.styles.copy(
                border = state.styles.border.update(
                    token = token,
                    width = newValue.dp,
                ),
            )
            themeController.setStyles(styles)
        },
        valueRange = { 0f..100f },
    )

    val shapeControl = enumControl(
        name = "Shape",
        values = { ShapeToken.entries },
        selectedValue = { state.borderStylesByToken[token]!!.shape },
        onValueChange = { newValue ->
            val styles = state.styles.copy(
                border = state.styles.border.update(
                    token = token,
                    shape = newValue,
                ),
            )
            themeController.setStyles(styles)
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

fun BorderStyleScheme.update(
    token: BorderStyleToken,
    color: ColorToken? = null,
    width: Dp? = null,
    shape: ShapeToken? = null
): BorderStyleScheme {
    return when (token) {
        BorderStyleToken.Primary -> this.copy(
            primary = this.primary.update(
                color = color,
                width = width,
                shape = shape,
            )
        )
        BorderStyleToken.Secondary -> this.copy(
            secondary = this.secondary.update(
                color = color,
                width = width,
                shape = shape,
            )
        )
        BorderStyleToken.Tertiary -> this.copy(
            tertiary = this.tertiary.update(
                color = color,
                width = width,
                shape = shape,
            )
        )
        BorderStyleToken.Surface -> this.copy(
            surface = this.surface.update(
                color = color,
                width = width,
                shape = shape,
            )
        )
    }
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
