package com.alexrdclement.palette.app.theme.primitive.typography

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.core.ComposeTextStyleDemoDefault
import com.alexrdclement.palette.components.demo.core.TextDemo
import com.alexrdclement.palette.components.demo.core.TextStyleDemoDefault
import com.alexrdclement.palette.components.demo.core.rememberTextDemoControl
import com.alexrdclement.palette.components.demo.core.rememberTextDemoState
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.toComposeFontFamily
import com.alexrdclement.palette.theme.primitive.toComposeFontWeight

/**
 * Edits the primitive typography tokens ([PaletteTheme.primitive.typography]) — the base font family
 * and weight — which propagate through the semantic typography ramp. The centered text demo is
 * seeded from the primitive font, and its font-family/weight controls write back to the primitive so
 * the two stay merged.
 */
@Composable
fun PrimitiveTypographyScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val textDemoState = rememberTextDemoState(
        textStyleInitial = TextStyleDemoDefault.copy(
            composeTextStyle = ComposeTextStyleDemoDefault.copy(
                fontFamily = themeController.primitive.typography.fontFamily.toComposeFontFamily(),
                fontWeight = themeController.primitive.typography.fontWeight.toComposeFontWeight(),
            ),
        ),
    )
    val textDemoControl = rememberTextDemoControl(textDemoState)

    val composeTextStyleState = textDemoState.textStyleDemoState.composeTextStyleDemoState
    LaunchedEffect(composeTextStyleState, themeController) {
        snapshotFlow { composeTextStyleState.fontFamily to composeTextStyleState.fontWeight }
            .collect { (fontFamily, fontWeight) ->
                themeController.updatePrimitive {
                    it.copy(
                        typography = it.typography.copy(
                            fontFamily = fontFamily ?: it.typography.fontFamily,
                            fontWeight = fontWeight ?: it.typography.fontWeight,
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
        TextDemo(
            modifier = Modifier.padding(paddingValues),
            state = textDemoState,
            control = textDemoControl,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PaletteTheme {
        PrimitiveTypographyScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
