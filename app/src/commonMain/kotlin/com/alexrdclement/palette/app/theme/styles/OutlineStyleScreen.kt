package com.alexrdclement.palette.app.theme.styles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.Demo
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
import com.alexrdclement.palette.theme.modifiers.OutlineStyleToken
import com.alexrdclement.palette.theme.modifiers.outline
import com.alexrdclement.palette.theme.modifiers.toStyle
import io.ktor.util.sha1
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun OutlineStyleScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberOutlineStyleScreenState(themeState = themeController)
    val control = rememberOutlineStyleScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Outline style",
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
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(
                    space = PaletteTheme.spacing.large,
                    alignment = Alignment.CenterVertically,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(PaletteTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.outlineStylesByToken.keys.toList()) { style ->
                    Box(
                        modifier = Modifier
                            .padding(PaletteTheme.spacing.medium)
                            .outline(style.toStyle(state.outlineStyleScheme)),
                    ) {
                        Text(
                            text = style.name,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun rememberOutlineStyleScreenState(
    themeState: ThemeState,
): OutlineStyleScreenState {
    return rememberSaveable(
        themeState,
        saver = OutlineStyleScreenStateSaver(themeState),
    ) {
        OutlineStyleScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class OutlineStyleScreenState(
    val themeState: ThemeState,
) {
    val styles: Styles
        get() = themeState.styles

    val outlineStyleScheme
        get() = styles.outline

    val outlineStylesByToken = OutlineStyleToken.entries.associateWith { token ->
        token.toStyle(outlineStyleScheme)
    }
}

fun OutlineStyleScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        OutlineStyleScreenState(
            themeState = themeState,
        )
    }
)

@Composable
fun rememberOutlineStyleScreenControl(
    state: OutlineStyleScreenState,
    themeController: ThemeController,
): OutlineStyleScreenControl {
    return remember(state, themeController) {
        OutlineStyleScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class OutlineStyleScreenControl(
    val state: OutlineStyleScreenState,
    val themeController: ThemeController,
) {
    val styleControls = OutlineStyleToken.entries.map { token ->
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
    token: OutlineStyleToken,
    state: OutlineStyleScreenState,
    themeController: ThemeController,
): Control {
    val contentColorControl = enumControl(
        name = "Color",
        values = { ColorToken.entries },
        selectedValue = { state.outlineStylesByToken[token]!!.color },
        onValueChange = { newValue ->
            val outlineScheme = when (token) {
                OutlineStyleToken.Primary -> state.styles.outline.copy(
                    primary = state.styles.outline.primary.copy(
                        color = newValue,
                    )
                )
                OutlineStyleToken.Secondary -> state.styles.outline.copy(
                    secondary = state.styles.outline.secondary.copy(
                        color = newValue,
                    )
                )
            }
            val styles = state.styles.copy(
                outline = outlineScheme,
            )
            themeController.setStyles(styles)
        },
    )

    val shapeControl = enumControl(
        name = "Shape",
        values = { ShapeToken.entries },
        selectedValue = { state.outlineStylesByToken[token]!!.shape },
        onValueChange = { newValue ->
            val outlineScheme = when (token) {
                OutlineStyleToken.Primary -> state.styles.outline.copy(
                    primary = state.styles.outline.primary.copy(
                        shape = newValue,
                    )
                )
                OutlineStyleToken.Secondary -> state.styles.outline.copy(
                    secondary = state.styles.outline.secondary.copy(
                        shape = newValue,
                    )
                )
            }
            val styles = state.styles.copy(
                outline = outlineScheme,
            )
            themeController.setStyles(styles)
        },
    )

    val widthControl = Control.Slider(
        name = "Width",
        value = { state.outlineStylesByToken[token]!!.width?.value ?: 0f },
        onValueChange = { newValue ->
            val outlineScheme = when (token) {
                OutlineStyleToken.Primary -> state.styles.outline.copy(
                    primary = state.styles.outline.primary.copy(
                        width = newValue.dp,
                    )
                )
                OutlineStyleToken.Secondary -> state.styles.outline.copy(
                    secondary = state.styles.outline.secondary.copy(
                        width = newValue.dp,
                    )
                )
            }
            val styles = state.styles.copy(
                outline = outlineScheme,
            )
            themeController.setStyles(styles)
        },
        valueRange = { 0f..100f },
    )

    return Control.ControlColumn(
        name = token.name,
        controls = {
            persistentListOf(
                contentColorControl,
                shapeControl,
                widthControl
            )
        },
    )
}
