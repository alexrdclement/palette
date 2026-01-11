package com.alexrdclement.palette.app.demo.components.geometry

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.geometry.navigation.GeometryComponent
import com.alexrdclement.palette.components.layout.Scaffold

@Composable
fun GeometryComponentScreen(
    component: GeometryComponent,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = component.title,
                onNavigateBack = onNavigateBack,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (component) {
            GeometryComponent.CurveStitch -> CurveStitchDemo(
                modifier = Modifier.padding(innerPadding)
            )
            GeometryComponent.Grid -> GridDemo(
                modifier = Modifier.padding(innerPadding)
            )
            GeometryComponent.Sphere -> SphereDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
