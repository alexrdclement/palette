package com.alexrdclement.palette.app.theme.typography

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.demo.control.enumControl
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.components.util.restore
import com.alexrdclement.palette.components.util.save
import com.alexrdclement.palette.formats.core.TextFormat
import com.alexrdclement.palette.theme.FontFamily
import com.alexrdclement.palette.theme.FontStyle
import com.alexrdclement.palette.theme.FontWeight
import com.alexrdclement.palette.theme.PaletteTypographyDefaults
import com.alexrdclement.palette.theme.TypographyToken
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.copy
import com.alexrdclement.palette.theme.styles.TextStyle
import com.alexrdclement.palette.theme.toComposeFontFamily
import com.alexrdclement.palette.theme.toComposeFontStyle
import com.alexrdclement.palette.theme.toComposeFontWeight
import com.alexrdclement.palette.theme.toComposeTextStyle
import com.alexrdclement.palette.theme.toFontFamily
import com.alexrdclement.palette.theme.toFontStyle
import com.alexrdclement.palette.theme.toFontWeight
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TypographyScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberTypographyScreenState(themeState = themeController)
    val control = rememberTypographyScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Typography",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = TypographyToken.entries.toList(),
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { textStyle ->
            BoxWithLabel(
                label = textStyle.name,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = state.text,
                    style = TextStyle(
                        composeTextStyle = textStyle.toComposeTextStyle(),
                        format = TextFormat(),
                    ),
                )
            }
        }
    }
}

@Composable
fun rememberTypographyScreenState(
    themeState: ThemeState,
    initialText: String = "Sphinx of black quartz, judge my vow",
): TypographyScreenState {
    val textFieldState = rememberTextFieldState(initialText = initialText)
    return rememberSaveable(
        themeState,
        saver = TypographyScreenStateSaver(themeState),
    ) {
        TypographyScreenState(
            themeState = themeState,
            textFieldState = textFieldState,
        )
    }
}

@Stable
class TypographyScreenState(
    val themeState: ThemeState,
    val textFieldState: TextFieldState,
) {
    val typography
        get() = themeState.typography

    val text: String
        get() = textFieldState.text.toString()
}

private val textFieldKey = "textField"

fun TypographyScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf(
            textFieldKey to save(state.textFieldState, TextFieldState.Saver, this),
        )
    },
    restore = { map ->
        TypographyScreenState(
            themeState = themeState,
            textFieldState = restore(map[textFieldKey], TextFieldState.Saver)!!,
        )
    }
)

@Composable
fun rememberTypographyScreenControl(
    state: TypographyScreenState,
    themeController: ThemeController,
): TypographyScreenControl {
    return remember(state, themeController) {
        TypographyScreenControl(state = state, themeController = themeController)
    }
}

@Stable
class TypographyScreenControl(
    val state: TypographyScreenState,
    val themeController: ThemeController,
) {
    val textFieldControl = Control.TextField(
        name = "Sample text",
        includeLabel = false,
        textFieldState = state.textFieldState,
    )

    val tokenControls = TypographyToken.entries.map {
        makeControlForToken(it, state, themeController)
    }

    val controls: PersistentList<Control> = persistentListOf(
        textFieldControl,
        *tokenControls.toTypedArray(),
    )
}

private fun makeControlForToken(
    token: TypographyToken,
    state: TypographyScreenState,
    themeController: ThemeController,
): Control {
    val textStyle = token.toComposeTextStyle(state.typography)

    val composeFontFamily = textStyle.fontFamily ?: PaletteTypographyDefaults.fontFamily
    val fontFamilyControl = enumControl(
        name = "Font family",
        values = { FontFamily.entries },
        selectedValue = { composeFontFamily.toFontFamily() },
        onValueChange = {
            val typography = state.typography.copy(
                token = token,
                textStyle = textStyle.copy(
                    fontFamily = it.toComposeFontFamily(),
                )
            )
            themeController.setTypography(typography)
        }
    )

    val fontSizeControl = Control.Slider(
        name = "Font size",
        value = { textStyle.fontSize.value },
        onValueChange = { newSize: Float ->
            val typography = state.typography.copy(
                token = token,
                textStyle = textStyle.copy(
                    fontSize = TextUnit(newSize, TextUnitType.Sp)
                )
            )
            themeController.setTypography(typography)
        },
        valueRange = { 8f..200f },
    )

    val fontWeightControl = enumControl(
        name = "Font weight",
        values = { FontWeight.entries },
        selectedValue = { textStyle.fontWeight?.toFontWeight() ?: FontWeight.Normal },
        onValueChange = {
            val typography = state.typography.copy(
                token = token,
                textStyle = textStyle.copy(
                    fontWeight = it.toComposeFontWeight(),
                )
            )
            themeController.setTypography(typography)
        }
    )

    val fontStyleControl = enumControl(
        name = "Font style",
        values = { FontStyle.entries },
        selectedValue = { textStyle.fontStyle?.toFontStyle() ?: FontStyle.Normal },
        onValueChange = {
            val typography = state.typography.copy(
                token = token,
                textStyle = textStyle.copy(
                    fontStyle = it.toComposeFontStyle(),
                )
            )
            themeController.setTypography(typography)
        },
    )

    val lineHeightControl = Control.Slider(
        name = "Line height",
        value = { textStyle.lineHeight.value },
        onValueChange = { newSize: Float ->
            val typography = state.typography.copy(
                token = token,
                textStyle = textStyle.copy(
                    lineHeight = TextUnit(newSize, TextUnitType.Sp)
                )
            )
            themeController.setTypography(typography)
        },
        valueRange = { 8f..200f },
    )

    val letterSpacingControl = Control.Slider(
        name = "Letter spacing",
        value = { textStyle.letterSpacing.value },
        onValueChange = { newSize: Float ->
            val typography = state.typography.copy(
                token = token,
                textStyle = textStyle.copy(
                    letterSpacing = TextUnit(newSize, TextUnitType.Sp)
                )
            )
            themeController.setTypography(typography)
        },
        valueRange = { -10f..10f },
    )

    return Control.ControlColumn(
        name = token.name,
        controls = {
            persistentListOf(
                fontFamilyControl,
                fontWeightControl,
                fontStyleControl,
                fontSizeControl,
                lineHeightControl,
                letterSpacingControl,
            )
        }
    )
}
