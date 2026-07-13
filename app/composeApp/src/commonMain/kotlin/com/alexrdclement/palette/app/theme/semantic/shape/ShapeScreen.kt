package com.alexrdclement.palette.app.theme.semantic.shape

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.theme.components.layout.BoxWithLabel
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import com.alexrdclement.palette.theme.semantic.ShapeToken
import com.alexrdclement.palette.theme.semantic.copy
import com.alexrdclement.palette.theme.semantic.primitiveToken
import com.alexrdclement.palette.theme.semantic.toComposeShape
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
    val controls = remember(themeController) {
        persistentListOf<Control>(
            *ShapeToken.entries.map { token ->
                enumControl(
                    name = token.name,
                    values = { ShapePrimitiveToken.entries },
                    selectedValue = { token.primitiveToken(themeController.semantic.shapeScheme) },
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
            }.toTypedArray()
        )
    }

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
            controls = controls,
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
