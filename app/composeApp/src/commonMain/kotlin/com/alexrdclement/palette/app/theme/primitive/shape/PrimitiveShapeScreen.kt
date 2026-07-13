package com.alexrdclement.palette.app.theme.primitive.shape

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.components.core.toComposeShape
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.theme.components.layout.BoxWithLabel
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import com.alexrdclement.palette.theme.primitive.ShapePrimitiveToken
import kotlinx.collections.immutable.persistentListOf

/**
 * Edits the primitive shape tokens ([PaletteTheme.primitive.shape]). The rectangle's corner radius is
 * editable and shared by every semantic shape role that references the rectangle primitive.
 */
@Composable
fun PrimitiveShapeScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val shapePrimitives = PaletteTheme.primitive.shape

    val controls = persistentListOf<Control>(
        Control.Slider(
            name = "Rectangle corner radius",
            value = { shapePrimitives.rectangle.cornerRadius.value },
            onValueChange = { radius ->
                themeController.updatePrimitive {
                    it.copy(shape = it.shape.copy(rectangle = Shape.Rectangle(cornerRadius = radius.dp)))
                }
            },
            valueRange = { 0f..64f },
            stepIncrement = 4f,
        ),
    )

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
            items = ShapePrimitiveToken.entries.toList(),
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
                            shape = shapePrimitives.shape(token).toComposeShape(),
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
        PrimitiveShapeScreen(
            themeController = rememberThemeController(),
            onNavigateUp = {},
        )
    }
}
