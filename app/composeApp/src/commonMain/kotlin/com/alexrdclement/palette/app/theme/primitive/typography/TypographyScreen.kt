package com.alexrdclement.palette.app.theme.primitive.typography

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.demo.core.ComposeTextStyleDemoDefault
import com.alexrdclement.palette.components.demo.core.TextDemo
import com.alexrdclement.palette.components.demo.core.TextStyleDemoDefault
import com.alexrdclement.palette.components.demo.core.rememberTextDemoControl
import com.alexrdclement.palette.components.demo.core.rememberTextDemoState
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.FontFamily
import com.alexrdclement.palette.theme.primitive.FontStyle
import com.alexrdclement.palette.theme.primitive.FontWeight
import com.alexrdclement.palette.theme.primitive.toComposeFontFamily
import com.alexrdclement.palette.theme.primitive.toComposeFontStyle
import com.alexrdclement.palette.theme.primitive.toComposeFontWeight
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TypographyScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val primitiveTypography = themeController.primitive.typography

    val textDemoState = rememberTextDemoState(
        textStyleInitial = TextStyleDemoDefault.copy(
            composeTextStyle = ComposeTextStyleDemoDefault.copy(
                fontFamily = primitiveTypography.fontFamily.toComposeFontFamily(),
                fontWeight = primitiveTypography.fontWeight.toComposeFontWeight(),
                fontStyle = primitiveTypography.fontStyle.toComposeFontStyle(),
            ),
        ),
    )
    val textDemoControl = rememberTextDemoControl(textDemoState)

    val controls = remember(primitiveTypography, textDemoControl) {
        persistentListOf(
            enumControl(
                name = "Font family",
                values = { FontFamily.entries },
                selectedValue = { primitiveTypography.fontFamily },
                onValueChange = { fontFamily ->
                    themeController.updatePrimitive {
                        it.copy(typography = it.typography.copy(fontFamily = fontFamily))
                    }
                    textDemoControl.updateTextStyle(
                        textStyle = with(textDemoState.textStyleDemoState.textStyle) {
                            copy(
                                composeTextStyle = composeTextStyle.copy(
                                    fontFamily = fontFamily.toComposeFontFamily(),
                                ),
                            )
                        }
                    )
                },
            ),
            enumControl(
                name = "Font weight",
                values = { FontWeight.entries },
                selectedValue = { primitiveTypography.fontWeight },
                onValueChange = { fontWeight ->
                    themeController.updatePrimitive {
                        it.copy(typography = it.typography.copy(fontWeight = fontWeight))
                    }
                    textDemoControl.updateTextStyle(
                        textStyle = with(textDemoState.textStyleDemoState.textStyle) {
                            copy(
                                composeTextStyle = composeTextStyle.copy(
                                    fontWeight = fontWeight.toComposeFontWeight(),
                                ),
                            )
                        }
                    )
                },
            ),
            enumControl(
                name = "Font style",
                values = { FontStyle.entries },
                selectedValue = { primitiveTypography.fontStyle },
                onValueChange = { fontStyle ->
                    themeController.updatePrimitive {
                        it.copy(typography = it.typography.copy(fontStyle = fontStyle))
                    }
                    textDemoControl.updateTextStyle(
                        textStyle = with(textDemoState.textStyleDemoState.textStyle) {
                            copy(
                                composeTextStyle = composeTextStyle.copy(
                                    fontStyle = fontStyle.toComposeFontStyle(),
                                ),
                            )
                        }
                    )
                },
            ),
            Control.ControlColumn(
                name = "Text",
                controls = { textDemoControl.controls },
            ),
        )
    }

    val composeTextStyleState = textDemoState.textStyleDemoState.composeTextStyleDemoState
    LaunchedEffect(composeTextStyleState, themeController) {
        snapshotFlow { composeTextStyleState }
            .collect { textStyle ->
                themeController.updatePrimitive {
                    it.copy(
                        typography = it.typography.copy(
                            fontFamily = textStyle.fontFamily ?: it.typography.fontFamily,
                            fontWeight = textStyle.fontWeight ?: it.typography.fontWeight,
                            fontStyle = textStyle.fontStyle ?: it.typography.fontStyle
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
            controls = controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextDemo(
                state = textDemoState,
                control = textDemoControl,
            )
        }
    }
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
