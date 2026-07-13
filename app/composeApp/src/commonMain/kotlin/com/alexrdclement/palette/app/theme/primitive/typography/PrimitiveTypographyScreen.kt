package com.alexrdclement.palette.app.theme.primitive.typography

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.demo.core.TextDemo
import com.alexrdclement.palette.components.demo.core.rememberTextDemoControl
import com.alexrdclement.palette.components.demo.core.rememberTextDemoState
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.FontFamily
import com.alexrdclement.palette.theme.primitive.FontWeight
import kotlinx.collections.immutable.persistentListOf

/**
 * Edits the primitive typography tokens ([PaletteTheme.primitive.typography]) — the base font family
 * and weight — which propagate through the semantic typography ramp. A single centered text demo is
 * shown; its own controls are grouped in the collapsed "Text" column.
 */
@Composable
fun PrimitiveTypographyScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val primitiveTypography = themeController.primitive.typography
    val textDemoState = rememberTextDemoState()
    val textDemoControl = rememberTextDemoControl(textDemoState)

    val controls = remember(primitiveTypography, textDemoControl) {
        persistentListOf<Control>(
            enumControl(
                name = "Font family",
                values = { FontFamily.entries },
                selectedValue = { primitiveTypography.fontFamily },
                onValueChange = { fontFamily ->
                    themeController.updatePrimitive {
                        it.copy(typography = it.typography.copy(fontFamily = fontFamily))
                    }
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
                },
            ),
            Control.ControlColumn(
                name = "Text",
                controls = { textDemoControl.controls },
            ),
        )
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
                .padding(paddingValues),
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
        PrimitiveTypographyScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
