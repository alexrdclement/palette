package com.alexrdclement.palette.components.demo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.DividerStyle
import com.alexrdclement.palette.components.core.HorizontalDivider
import com.alexrdclement.palette.components.core.VerticalDivider
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.Controls
import com.alexrdclement.palette.components.demo.control.ControlsStyle
import com.alexrdclement.palette.components.util.horizontalPaddingValues
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Styling for the demo framework: the [ControlStyle] threaded to the control panel plus the
 * [dividerStyle] separating content from controls. Supplied explicitly to [Demo]/[DemoList]; themed
 * callers build it from their theme, keeping this framework theme-agnostic.
 */
data class DemoStyle(
    val controlsStyle: ControlsStyle = ControlsStyle(),
    val dividerStyle: DividerStyle = DividerStyle(),
)

@Stable
interface DemoScope : BoxWithConstraintsScope

private class DemoScopeImpl(
    val boxWithConstraintsScope: BoxWithConstraintsScope,
) : DemoScope, BoxWithConstraintsScope by boxWithConstraintsScope

@Composable
fun Demo(
    modifier: Modifier = Modifier,
    style: DemoStyle = DemoStyle(),
    controls: PersistentList<Control> = persistentListOf(),
    content: @Composable DemoScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
            .padding(WindowInsets.safeDrawing.horizontalPaddingValues()),
    ) {
        if (minWidth < minHeight) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    val scope = DemoScopeImpl(this)
                    scope.content()
                }
                if (controls.isNotEmpty()) {
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), style = style.dividerStyle)
                    Controls(
                        controls = controls,
                        controlsStyle = style.controlsStyle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                            .navigationBarsPadding(),
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    val scope = DemoScopeImpl(this@BoxWithConstraints)
                    scope.content()
                }
                if (controls.isNotEmpty()) {
                    VerticalDivider(modifier = Modifier.fillMaxHeight(), style = style.dividerStyle)
                    Controls(
                        controls = controls,
                        controlsStyle = style.controlsStyle,
                        modifier = Modifier
                            .fillMaxHeight()
                            .widthIn(max = 300.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                            .navigationBarsPadding(),
                    )
                }
            }
        }
    }
}
