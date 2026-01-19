package com.alexrdclement.palette.components.demo.subject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.geometry.Grid
import com.alexrdclement.palette.components.geometry.GridCoordinateSystem
import com.alexrdclement.palette.components.geometry.GridLineStyle
import com.alexrdclement.palette.components.geometry.GridVertex
import com.alexrdclement.palette.theme.PaletteTheme

enum class DemoSubjectType {
    Circle,
    CircleOutline,
    GridDot,
    GridLine,
    GridPlus,
    GridRect,
    Text,
    TextField,
}

@Composable
fun DemoSubject(
    demoSubject: DemoSubjectType,
    modifier: Modifier = Modifier,
) {
    when (demoSubject) {
        DemoSubjectType.Circle -> DemoCircle(modifier = modifier)
        DemoSubjectType.CircleOutline -> DemoCircle(drawStyle = Stroke(2f), modifier = modifier)
        DemoSubjectType.GridLine -> Grid(
            coordinateSystem = GridCoordinateSystem.Cartesian(
                spacing = 20.dp,
            ),
            lineStyle = GridLineStyle(
                color = PaletteTheme.colorScheme.primary,
                stroke = Stroke(width = 1f),
            ),
            modifier = modifier.fillMaxSize(),
        )

        DemoSubjectType.GridDot -> Grid(
            coordinateSystem = GridCoordinateSystem.Cartesian(
                spacing = 20.dp,
            ),
            lineStyle = null,
            vertex = GridVertex.Oval(
                color = PaletteTheme.colorScheme.primary,
                size = DpSize(4.dp, 4.dp),
                drawStyle = Fill,
            ),
            modifier = modifier.fillMaxSize(),
        )

        DemoSubjectType.GridRect -> Grid(
            coordinateSystem = GridCoordinateSystem.Cartesian(
                spacing = 20.dp,
            ),
            lineStyle = null,
            vertex = GridVertex.Rect(
                color = PaletteTheme.colorScheme.primary,
                size = DpSize(4.dp, 4.dp),
                drawStyle = Fill,
            ),
            modifier = modifier.fillMaxSize(),
        )

        DemoSubjectType.GridPlus -> Grid(
            coordinateSystem = GridCoordinateSystem.Cartesian(
                spacing = 20.dp,
            ),
            lineStyle = null,
            vertex = GridVertex.Plus(
                color = PaletteTheme.colorScheme.primary,
                size = DpSize(8.dp, 8.dp),
                strokeWidth = 1.dp,
            ),
            modifier = modifier.fillMaxSize(),
        )

        DemoSubjectType.Text -> DemoText(modifier = modifier)
        DemoSubjectType.TextField -> DemoTextField(modifier = modifier)
    }
}
