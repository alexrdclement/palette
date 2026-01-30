package com.alexrdclement.palette.app.demo.components.media

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.media.navigation.MediaComponent
import com.alexrdclement.palette.components.demo.media.MediaControlSheetDemo
import com.alexrdclement.palette.components.layout.Scaffold

@Composable
fun MediaComponentScreen(
    component: MediaComponent,
    onNavigateUp: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = component.title,
                onNavigateUp = onNavigateUp,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (component) {
            MediaComponent.MediaControlSheet -> MediaControlSheetDemo(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
