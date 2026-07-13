package com.alexrdclement.palette.app.theme.primitive.typography

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.demo.core.ComposeTextStyleDemoDefault
import com.alexrdclement.palette.components.demo.core.ComposeTextStyleDemoState
import com.alexrdclement.palette.components.demo.core.TextDemo
import com.alexrdclement.palette.components.demo.core.TextDemoControl
import com.alexrdclement.palette.components.demo.core.TextDemoState
import com.alexrdclement.palette.components.demo.core.TextStyleDemoDefault
import com.alexrdclement.palette.components.demo.core.rememberTextDemoControl
import com.alexrdclement.palette.components.demo.core.rememberTextDemoState
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.FontFamily
import com.alexrdclement.palette.theme.primitive.FontStyle
import com.alexrdclement.palette.theme.primitive.FontWeight
import com.alexrdclement.palette.theme.primitive.Typography
import com.alexrdclement.palette.theme.primitive.toComposeFontFamily
import com.alexrdclement.palette.theme.primitive.toComposeFontStyle
import com.alexrdclement.palette.theme.primitive.toComposeFontWeight
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TypographyScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val state = rememberTypographyScreenState(themeState = themeController)
    val control = rememberTypographyScreenControl(state = state, themeController = themeController)

    LaunchedEffect(state, themeController) {
        snapshotFlow { state.composeTextStyleState }
            .collect { textStyle ->
                themeController.updatePrimitive {
                    it.copy(
                        typography = it.typography.copy(
                            fontFamily = textStyle.fontFamily ?: it.typography.fontFamily,
                            fontWeight = textStyle.fontWeight ?: it.typography.fontWeight,
                            fontStyle = textStyle.fontStyle ?: it.typography.fontStyle,
                        ),
                    )
                }
            }
    }

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Typography",
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
            TextDemo(
                state = state.textDemoState,
                control = control.textDemoControl,
            )
        }
    }
}

@Composable
fun rememberTypographyScreenState(
    themeState: ThemeState,
): TypographyScreenState {
    val primitiveTypography = themeState.primitive.typography
    val textDemoState = rememberTextDemoState(
        textStyleInitial = TextStyleDemoDefault.copy(
            composeTextStyle = ComposeTextStyleDemoDefault.copy(
                fontFamily = primitiveTypography.fontFamily.toComposeFontFamily(),
                fontWeight = primitiveTypography.fontWeight.toComposeFontWeight(),
                fontStyle = primitiveTypography.fontStyle.toComposeFontStyle(),
            ),
        ),
    )
    return remember(themeState, textDemoState) {
        TypographyScreenState(
            themeState = themeState,
            textDemoState = textDemoState,
        )
    }
}

@Stable
class TypographyScreenState(
    val themeState: ThemeState,
    val textDemoState: TextDemoState,
) {
    val primitiveTypography: Typography
        get() = themeState.primitive.typography

    val composeTextStyleState: ComposeTextStyleDemoState
        get() = textDemoState.textStyleDemoState.composeTextStyleDemoState
}

@Composable
fun rememberTypographyScreenControl(
    state: TypographyScreenState,
    themeController: ThemeController,
): TypographyScreenControl {
    val textDemoControl = rememberTextDemoControl(state.textDemoState)
    return remember(state, themeController, textDemoControl) {
        TypographyScreenControl(
            state = state,
            themeController = themeController,
            textDemoControl = textDemoControl,
        )
    }
}

@Stable
class TypographyScreenControl(
    val state: TypographyScreenState,
    val themeController: ThemeController,
    val textDemoControl: TextDemoControl,
) {
    private val fontFamilyControl = enumControl(
        name = "Font family",
        values = { FontFamily.entries },
        selectedValue = { state.primitiveTypography.fontFamily },
        onValueChange = { fontFamily ->
            themeController.updatePrimitive {
                it.copy(typography = it.typography.copy(fontFamily = fontFamily))
            }
            textDemoControl.updateTextStyle(
                textStyle = with(state.textDemoState.textStyleDemoState.textStyle) {
                    copy(
                        composeTextStyle = composeTextStyle.copy(
                            fontFamily = fontFamily.toComposeFontFamily(),
                        ),
                    )
                }
            )
        },
    )

    private val fontWeightControl = enumControl(
        name = "Font weight",
        values = { FontWeight.entries },
        selectedValue = { state.primitiveTypography.fontWeight },
        onValueChange = { fontWeight ->
            themeController.updatePrimitive {
                it.copy(typography = it.typography.copy(fontWeight = fontWeight))
            }
            textDemoControl.updateTextStyle(
                textStyle = with(state.textDemoState.textStyleDemoState.textStyle) {
                    copy(
                        composeTextStyle = composeTextStyle.copy(
                            fontWeight = fontWeight.toComposeFontWeight(),
                        ),
                    )
                }
            )
        },
    )

    private val fontStyleControl = enumControl(
        name = "Font style",
        values = { FontStyle.entries },
        selectedValue = { state.primitiveTypography.fontStyle },
        onValueChange = { fontStyle ->
            themeController.updatePrimitive {
                it.copy(typography = it.typography.copy(fontStyle = fontStyle))
            }
            textDemoControl.updateTextStyle(
                textStyle = with(state.textDemoState.textStyleDemoState.textStyle) {
                    copy(
                        composeTextStyle = composeTextStyle.copy(
                            fontStyle = fontStyle.toComposeFontStyle(),
                        ),
                    )
                }
            )
        },
    )

    private val textControl = Control.ControlColumn(
        name = "Text",
        controls = { textDemoControl.controls },
    )

    val controls: PersistentList<Control> = persistentListOf(
        fontFamilyControl,
        fontWeightControl,
        fontStyleControl,
        textControl,
    )
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        TypographyScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
