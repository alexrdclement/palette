package com.alexrdclement.palette.app.demo.experiments

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.experiments.demo.fade.FadeDemo
import com.alexrdclement.palette.app.demo.experiments.demo.gradient.GradientDemo
import com.alexrdclement.palette.app.demo.experiments.demo.scroll.AnimateScrollItemVisibleDemo
import com.alexrdclement.palette.app.demo.experiments.demo.uievent.UiEventDemo
import com.alexrdclement.palette.components.layout.Scaffold

@Composable
fun ExperimentScreen(
    experiment: Experiment,
    onNavigateBack: () -> Unit,
    onConfigureClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = experiment.title,
                onNavigateBack = onNavigateBack,
                onConfigureClick = onConfigureClick,
            )
        },
    ) { innerPadding ->
        when (experiment) {
            Experiment.AnimateScrollItemVisible -> AnimateScrollItemVisibleDemo(
                modifier = Modifier.padding(innerPadding),
            )
            Experiment.Fade -> FadeDemo(
                modifier = Modifier.padding(innerPadding),
            )
            Experiment.Gradients -> GradientDemo(
                modifier = Modifier.padding(innerPadding),
            )
            Experiment.UiEvent -> UiEventDemo(
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
