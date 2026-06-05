package com.alexrdclement.palette.components.demo

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.alexrdclement.palette.components.color.ColorPickerStyle
import com.alexrdclement.palette.components.core.ButtonStyle
import com.alexrdclement.palette.components.core.SurfaceStyle
import com.alexrdclement.palette.components.core.TextFieldStyle
import com.alexrdclement.palette.components.core.TextStyle

/**
 * Styling for the demo control framework. Supplied explicitly to [Demo]/[DemoList]; distributed to
 * the (recursive, data-driven) control renderers via [LocalDemoStyle].
 */
data class DemoStyle(
    val labelStyle: TextStyle = TextStyle(),
    val headerStyle: TextStyle = TextStyle(),
    val fieldTextStyle: TextStyle = TextStyle(),
    val buttonStyle: ButtonStyle = ButtonStyle(),
    val textFieldStyle: TextFieldStyle = TextFieldStyle(),
    val borderColor: Color = Color.Unspecified,
    val colorPickerStyle: ColorPickerStyle = ColorPickerStyle(),
    val surfaceStyle: SurfaceStyle = SurfaceStyle(),
)

val LocalDemoStyle = compositionLocalOf { DemoStyle() }
