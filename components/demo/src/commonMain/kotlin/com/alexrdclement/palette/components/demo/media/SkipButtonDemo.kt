package com.alexrdclement.palette.components.demo.media

import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.paddingValuesControls
import com.alexrdclement.palette.components.media.SkipBackButton
import com.alexrdclement.palette.components.media.SkipButton
import com.alexrdclement.palette.components.media.SkipButtonStyle
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SkipButtonDemo(
    modifier: Modifier = Modifier,
) {
    var enabled by remember { mutableStateOf(true) }
    val base = PaletteTheme.styles.media.skipButton
    var contentPadding by remember { mutableStateOf(base.buttonStyle.contentPadding) }

    val controls = persistentListOf(
        Control.Toggle(
            name = "Enabled",
            value = { enabled },
            onValueChange = { enabled = it },
        ),
        Control.ControlColumn(
            name = "Style",
            indent = true,
            expandedInitial = true,
            controls = {
                persistentListOf(
                    paddingValuesControls(
                        name = "Content padding",
                        value = { contentPadding },
                        onValueChange = { contentPadding = it },
                    ),
                )
            },
        ),
    )

    Demo(
        controls = controls,
        modifier = modifier.fillMaxSize(),
    ) {
        SkipButtonDemo(
            enabled = enabled,
            style = base.copy(buttonStyle = base.buttonStyle.copy(contentPadding = contentPadding)),
        )
    }
}

@Composable
fun DemoScope.SkipButtonDemo(
    enabled: Boolean,
    modifier: Modifier = Modifier,
    style: SkipButtonStyle = PaletteTheme.styles.media.skipButton,
) {
    SkipBackButton(
        onClick = {},
        enabled = enabled,
        style = style,
        modifier = modifier
            .size(52.dp)
            .align(Alignment.CenterStart),
    )
    SkipButton(
        onClick = {},
        enabled = enabled,
        style = style,
        modifier = Modifier
            .size(52.dp)
            .align(Alignment.CenterEnd),
    )
}
