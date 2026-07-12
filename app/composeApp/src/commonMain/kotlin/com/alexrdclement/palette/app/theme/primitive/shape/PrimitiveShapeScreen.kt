package com.alexrdclement.palette.app.theme.primitive.shape

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Shape
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.core.toComposeShape
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.components.demo.DemoList
import com.alexrdclement.palette.theme.components.layout.BoxWithLabel
import com.alexrdclement.palette.theme.components.layout.Scaffold
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.rememberThemeController
import kotlinx.collections.immutable.persistentListOf

/**
 * Displays the primitive shape tokens ([PaletteTheme.primitive.shape]) — the raw, unopinionated
 * shapes. These are literal building blocks and are not editable; semantic shape tokens reference
 * them.
 */
@Composable
fun PrimitiveShapeScreen(
    themeController: ThemeController,
    onNavigateUp: () -> Unit,
) {
    val shapes: List<Pair<String, Shape>> = listOf(
        "Circle" to PaletteTheme.primitive.shape.circle,
        "Rectangle" to PaletteTheme.primitive.shape.rectangle,
        "Triangle" to PaletteTheme.primitive.shape.triangle,
        "Diamond" to PaletteTheme.primitive.shape.diamond,
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
            items = shapes,
            controls = persistentListOf(),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { (name, shape) ->
            BoxWithLabel(
                label = name,
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = PaletteTheme.semantic.color.outline,
                            shape = shape.toComposeShape(),
                        )
                ) {
                    Text(
                        text = name,
                        style = PaletteTheme.component.core.text.headline,
                    )
                }
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
