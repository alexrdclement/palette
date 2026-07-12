package com.alexrdclement.palette.app.theme.primitive.typography

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.TextStyle
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.formats.core.TextFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.theme.components.layout.BoxWithLabel
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.FontFamily
import com.alexrdclement.palette.theme.primitive.FontWeight
import com.alexrdclement.palette.theme.semantic.TypographyToken
import com.alexrdclement.palette.theme.semantic.toComposeTextStyle
import kotlinx.collections.immutable.persistentListOf

/**
 * Edits the primitive typography tokens ([PaletteTheme.primitive.typography]) — the base font family
 * and weight. Changes propagate through the semantic typography ramp shown below.
 */
@Composable
fun PrimitiveTypographyScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val primitiveTypography = themeController.primitive.typography

    val controls = remember(primitiveTypography) {
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
        DemoList(
            items = TypographyToken.entries.toList(),
            controls = controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { token ->
            BoxWithLabel(
                label = token.name,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Sphinx of black quartz, judge my vow",
                    style = TextStyle(
                        composeTextStyle = token.toComposeTextStyle().copy(
                            color = PaletteTheme.semantic.color.onSurface,
                        ),
                        format = TextFormat(),
                    ),
                )
            }
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
