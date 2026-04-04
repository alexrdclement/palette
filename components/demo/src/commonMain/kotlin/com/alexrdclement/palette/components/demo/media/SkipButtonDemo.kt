package com.alexrdclement.palette.components.demo.media

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.media.SkipBackButton
import com.alexrdclement.palette.components.media.SkipButton
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SkipButtonDemo(
    modifier: Modifier = Modifier,
) {
    var enabled by remember { mutableStateOf(true) }

    val controls = persistentListOf(
        Control.Toggle(
            name = "Enabled",
            value = { enabled },
            onValueChange = { enabled = it },
        ),
    )

    Demo(
        controls = controls,
        modifier = modifier.fillMaxSize(),
    ) {
        SkipButtonDemo(enabled = enabled)
    }
}

@Composable
fun DemoScope.SkipButtonDemo(
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    SkipBackButton(
        onClick = {},
        enabled = enabled,
        modifier = modifier
            .size(52.dp)
            .align(Alignment.CenterStart),
    )
    SkipButton(
        onClick = {},
        enabled = enabled,
        modifier = Modifier
            .size(52.dp)
            .align(Alignment.CenterEnd),
    )
}
