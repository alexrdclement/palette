package com.alexrdclement.palette.app.theme.format

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.core.NumberFormatDemo
import com.alexrdclement.palette.app.demo.formats.core.NumberFormatDemoState
import com.alexrdclement.palette.app.demo.formats.core.rememberNumberFormatDemoControl
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.core.NumberFormatScheme
import com.alexrdclement.palette.theme.format.core.NumberFormatToken
import com.alexrdclement.palette.theme.format.core.toFormat
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun NumberFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberNumberFormatScreenState(formats = themeController.formats)
    val control = rememberNumberFormatScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Number format",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        Demo(
            controls = control.controls,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(
                    space = PaletteTheme.spacing.large,
                    alignment = Alignment.CenterVertically,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(PaletteTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.numberFormatsByToken.keys.toList()) { token ->
                    NumberFormatDemo(
                        state = state.numberFormatDemoStatesByToken[token]!!,
                        modifier = Modifier.padding(
                            horizontal = PaletteTheme.spacing.medium,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun rememberNumberFormatScreenState(
    formats: Formats,
): NumberFormatScreenState {
    return rememberSaveable(
        formats,
        saver = NumberFormatScreenStateSaver(formats),
    ) {
        NumberFormatScreenState(
            formats = formats,
        )
    }
}

@Stable
class NumberFormatScreenState(
    val formats: Formats,
) {
    val numberFormatScheme: NumberFormatScheme
        get() = formats.numberFormats

    val numberFormatsByToken = NumberFormatToken.entries.associateWith { token ->
        token.toFormat()
    }

    val numberFormatDemoStatesByToken = NumberFormatToken.entries.associateWith { token ->
        NumberFormatDemoState(
            numberFormatInitial = numberFormatsByToken[token]!!,
            demoTextFieldState = TextFieldState("12345678.90"),
        )
    }
}

fun NumberFormatScreenStateSaver(formats: Formats) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        NumberFormatScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberNumberFormatScreenControl(
    state: NumberFormatScreenState,
    themeController: ThemeController,
): NumberFormatScreenControl {
    val formatControlByToken = NumberFormatToken.entries.associateWith { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController) {
        NumberFormatScreenControl(
            state = state,
            themeController = themeController,
            formatControlByToken = formatControlByToken,
        )
    }
}

@Stable
class NumberFormatScreenControl(
    val state: NumberFormatScreenState,
    val themeController: ThemeController,
    formatControlByToken: Map<NumberFormatToken, Control>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        *formatControlByToken.values.toTypedArray(),
    )
}

@Composable
private fun makeControlForToken(
    token: NumberFormatToken,
    state: NumberFormatScreenState,
    themeController: ThemeController,
): Control {
    val demoControl = rememberNumberFormatDemoControl(
        state = state.numberFormatDemoStatesByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    numberFormats = state.numberFormatScheme.update(
                        token = token,
                        numberFormat = newValue,
                    )
                )
            )
        }
    )
    return Control.ControlColumn(
        name = token.name,
        controls = { demoControl.controls },
        expandedInitial = true,
    )
}

fun NumberFormatScheme.update(
    token: NumberFormatToken,
    numberFormat: NumberFormat,
): NumberFormatScheme {
    return when (token) {
        NumberFormatToken.Default -> this.copy(
            default = numberFormat,
        )
        NumberFormatToken.Currency -> this.copy(
            currency = numberFormat,
        )
    }
}
