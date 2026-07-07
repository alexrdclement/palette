package com.alexrdclement.palette.components.demo.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.copy
import com.alexrdclement.palette.theme.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.demo.control.paddingValuesControls
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.styles.tokenSet
import com.alexrdclement.palette.theme.toColor
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ButtonDemo(
    state: ButtonDemoState = rememberButtonDemoState(),
    control: ButtonDemoControl = rememberButtonDemoControl(state),
    modifier: Modifier = Modifier,
) {
    Demo(
        controls = control.controls,
        modifier = modifier
            .fillMaxSize()
    ) {
        ButtonDemo(
            state = state,
            control = control,
        )
    }
}

@Composable
fun DemoScope.ButtonDemo(
    modifier: Modifier = Modifier,
    state: ButtonDemoState = rememberButtonDemoState(),
    control: ButtonDemoControl = rememberButtonDemoControl(state),
) {
    LaunchedEffect(control, maxWidth) {
        control.onButtonSizeChanged(maxWidth)
    }
    // A demo text style per button variant, colored by that variant's content color. Recomputed
    // when the theme changes, driving a single flow that re-applies the selected variant's color.
    val textStyleByToken = ButtonStyleToken.entries.associateWith { token ->
        TextStyleDemoDefault.copy(color = token.tokenSet().contentColor.toColor())
    }
    LaunchedEffect(state.style, textStyleByToken) {
        state.textDemoState.textStyle = textStyleByToken.getValue(state.style)
    }
    Button(
        onClick = {},
        style = PaletteTheme.styles.core.button[state.style].copy(contentPadding = state.contentPadding),
        enabled = state.enabled,
        modifier = modifier
            .width(state.width)
            .align(Alignment.Center)
            .padding(PaletteTheme.spacing.medium)
            .semantics { contentDescription = "Demo Button" }
    ) {
        this@ButtonDemo.TextDemo(
            state = state.textDemoState,
            control = control.textDemoControl,
        )
    }
}

@Composable
fun rememberButtonDemoState(
    contentPaddingInitial: PaddingValues = PaddingValues(
        horizontal = PaletteTheme.spacing.large,
        vertical = PaletteTheme.spacing.medium,
    ),
): ButtonDemoState = rememberSaveable(saver = ButtonDemoStateSaver) {
    ButtonDemoState(contentPaddingInitial = contentPaddingInitial)
}

@Stable
class ButtonDemoState(
    styleInitial: ButtonStyleToken = ButtonStyleToken.Primary,
    enabledInitial: Boolean = true,
    maxWidthInitial: Dp = 0.dp,
    widthInitial: Dp = 200.dp,
    contentPaddingInitial: PaddingValues = PaddingValues(),
    val textDemoState: TextDemoState = TextDemoState(
        initialText = "Button",
        textAlignInitial = TextAlign.Center,
    ),
) {
    var enabled by mutableStateOf(enabledInitial)
        internal set
    var style by mutableStateOf(styleInitial)
        internal set
    var maxWidth by mutableStateOf(maxWidthInitial)
        internal set
    var width by mutableStateOf(widthInitial)
        internal set
    var contentPadding by mutableStateOf(contentPaddingInitial)
        internal set
}

private const val enabledKey = "enabled"
private const val styleKey = "style"
private const val maxWidthKey = "maxWidth"
private const val widthKey = "width"
private const val contentPaddingStartKey = "contentPaddingStart"
private const val contentPaddingTopKey = "contentPaddingTop"
private const val contentPaddingEndKey = "contentPaddingEnd"
private const val contentPaddingBottomKey = "contentPaddingBottom"
private const val textDemoStateKey = "textDemoState"

val ButtonDemoStateSaver = mapSaverSafe(
    save = { value ->
        mapOf(
            enabledKey to value.enabled,
            styleKey to value.style,
            maxWidthKey to value.maxWidth.value,
            widthKey to value.width.value,
            contentPaddingStartKey to value.contentPadding.calculateStartPadding(LayoutDirection.Ltr).value,
            contentPaddingTopKey to value.contentPadding.calculateTopPadding().value,
            contentPaddingEndKey to value.contentPadding.calculateEndPadding(LayoutDirection.Ltr).value,
            contentPaddingBottomKey to value.contentPadding.calculateBottomPadding().value,
            textDemoStateKey to save(value.textDemoState, TextDemoStateSaver, this),
        )
    },
    restore = { map ->
        ButtonDemoState(
            enabledInitial = map[enabledKey] as Boolean,
            styleInitial = map[styleKey] as ButtonStyleToken,
            maxWidthInitial = (map[maxWidthKey] as Float).dp,
            widthInitial = (map[widthKey] as Float).dp,
            contentPaddingInitial = PaddingValues(
                start = (map[contentPaddingStartKey] as Float).dp,
                top = (map[contentPaddingTopKey] as Float).dp,
                end = (map[contentPaddingEndKey] as Float).dp,
                bottom = (map[contentPaddingBottomKey] as Float).dp,
            ),
            textDemoState = restore(map[textDemoStateKey], TextDemoStateSaver)!!
        )
    },
)

@Composable
fun rememberButtonDemoControl(
    state: ButtonDemoState,
): ButtonDemoControl = remember(state) { ButtonDemoControl(state) }

@Stable
class ButtonDemoControl(
    val state: ButtonDemoState,
) {
    val styleTokenControl = enumControl(
        name = "Token",
        values = { ButtonStyleToken.entries },
        selectedValue = { state.style },
        onValueChange = { state.style = it },
    )

    val enabledControl = Control.Toggle(
        name = "Enabled",
        value = { state.enabled },
        onValueChange = { state.enabled = it },
    )

    val widthControl = Control.Slider(
        name = "Width",
        value = { state.width.value },
        onValueChange = { state.width = it.dp },
        valueRange = { 0f..state.maxWidth.value },
    )

    val contentPaddingControl = paddingValuesControls(
        name = "Content padding",
        value = { state.contentPadding },
        onValueChange = { state.contentPadding = it },
    )

    val styleControls = Control.ControlColumn(
        name = "Style",
        indent = true,
        expandedInitial = true,
        controls = {
            persistentListOf(
                styleTokenControl,
                contentPaddingControl,
            )
        },
    )

    val textDemoControl = TextDemoControl(state.textDemoState)
    val textDemoControls = Control.ControlColumn(
        name = "Text",
        indent = true,
        controls = { textDemoControl.controls },
    )

    val controls = persistentListOf(
        enabledControl,
        styleControls,
        widthControl,
        textDemoControls,
    )

    fun onButtonSizeChanged(width: Dp) {
        if (state.maxWidth == 0.dp) {
            state.width = width
        }
        state.maxWidth = width
        if (state.width > state.maxWidth) {
            state.width = state.maxWidth
        }

        if (state.textDemoState.maxWidth == 0.dp) {
            state.textDemoState.width = width
        }
        state.textDemoState.maxWidth = width
        if (state.textDemoState.width > state.textDemoState.maxWidth) {
            state.textDemoState.width = state.textDemoState.maxWidth
        }
    }
}
