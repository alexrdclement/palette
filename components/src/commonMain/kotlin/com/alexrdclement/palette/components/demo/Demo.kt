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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.HorizontalDivider
import com.alexrdclement.palette.components.core.VerticalDivider
import com.alexrdclement.palette.components.demo.control.CharControlStyle
import com.alexrdclement.palette.components.demo.control.ColorControlStyle
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.Controls
import com.alexrdclement.palette.components.demo.control.ControlsStyle
import com.alexrdclement.palette.components.demo.control.DropdownControlStyle
import com.alexrdclement.palette.components.demo.control.DynamicListControlStyle
import com.alexrdclement.palette.components.demo.control.ExpandableHeaderStyle
import com.alexrdclement.palette.components.demo.control.SliderControlStyle
import com.alexrdclement.palette.components.demo.control.TextFieldControlStyle
import com.alexrdclement.palette.components.demo.control.ToggleControlStyle
import com.alexrdclement.palette.components.util.horizontalPaddingValues
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.components.core.TextStyle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

/**
 * Styling for the demo control framework. Supplied explicitly to [Demo]/[DemoList]; distributed to
 * the (recursive, data-driven) control renderers via [LocalDemoStyle].
 */
data class DemoStyle(
    val labelStyle: TextStyle = TextStyle(),
    val headerStyle: TextStyle = TextStyle(),
    val buttonStyle: ButtonStyle = ButtonStyle.Default(),
    val textFieldStyle: TextFieldStyle = TextFieldStyle(),
    val borderColor: Color = Color.Unspecified,
    val colorPickerStyle: ColorPickerStyle = ColorPickerStyle(),
    val surfaceStyle: SurfaceStyle = SurfaceStyle(),
    val colorControl: ColorControlStyle = ColorControlStyle(),
    val sliderControl: SliderControlStyle = SliderControlStyle(),
    val toggleControl: ToggleControlStyle = ToggleControlStyle(),
    val charControl: CharControlStyle = CharControlStyle(),
    val textFieldControl: TextFieldControlStyle = TextFieldControlStyle(),
    val dropdownControl: DropdownControlStyle = DropdownControlStyle(),
    val expandableHeader: ExpandableHeaderStyle = ExpandableHeaderStyle(),
    val dynamicListControl: DynamicListControlStyle = DynamicListControlStyle(),
    val controls: ControlsStyle = ControlsStyle(),
)

val LocalDemoStyle = compositionLocalOf { DemoStyle() }

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
    CompositionLocalProvider(LocalDemoStyle provides style) {
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
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    Controls(
                        controls = controls,
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
                    VerticalDivider(modifier = Modifier.fillMaxHeight())
                    Controls(
                        controls = controls,
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
}
