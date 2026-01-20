package com.alexrdclement.palette.app.theme.format.datetime

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.formats.demo.datetime.DateTimeFormatDemo
import com.alexrdclement.palette.formats.demo.datetime.DateTimeFormatDemoState
import com.alexrdclement.palette.formats.demo.datetime.rememberDateTimeFormatDemoControl
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.datetime.TimeFormatValue
import com.alexrdclement.palette.formats.datetime.toFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.datetime.TimeFormatScheme
import com.alexrdclement.palette.theme.format.datetime.TimeFormatToken
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.format

@Composable
fun TimeFormatSchemeScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberTimeFormatSchemeScreenState(
        formats = themeController.formats,
    )
    val control = rememberTimeFormatSchemeScreenControl(
        state = state,
        themeController = themeController,
    )

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Time",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = state.timeFormatsByToken.entries.toList(),
            controls = control.controls,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { (token, format) ->
            BoxWithLabel(
                label = token.name,
                modifier = Modifier
                    .padding(horizontal = PaletteTheme.spacing.medium)
            ) {
                DateTimeFormatDemo(
                    state = state.dateTimeFormatDemoStateByToken[token]!!,
                    format = { _, localDateTime, _ ->
                        localDateTime.time.format(format.toFormat())
                    },
                )
            }
        }
    }
}

@Composable
fun rememberTimeFormatSchemeScreenState(
    formats: Formats,
): TimeFormatSchemeScreenState {
    return rememberSaveable(
        formats.dateTimeFormats,
        saver = TimeFormatSchemeScreenStateSaver(formats),
    ) {
        TimeFormatSchemeScreenState(
            formats = formats,
        )
    }
}

@Stable
class TimeFormatSchemeScreenState(
    val formats: Formats,
) {
    val timeFormatScheme = formats.dateTimeFormats.timeFormatScheme

    val timeFormatsByToken = TimeFormatToken.entries.associateWith {
        when (it) {
            TimeFormatToken.Default -> timeFormatScheme.default
            TimeFormatToken.Long -> timeFormatScheme.long
            TimeFormatToken.Short -> timeFormatScheme.short
        }
    }

    val dateTimeFormatDemoStateByToken = TimeFormatToken.entries.associateWith {
        DateTimeFormatDemoState(
            tokenInitial = timeFormatsByToken[it]!!,
        )
    }
}

fun TimeFormatSchemeScreenStateSaver(
    formats: Formats,
) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        TimeFormatSchemeScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberTimeFormatSchemeScreenControl(
    state: TimeFormatSchemeScreenState,
    themeController: ThemeController,
): TimeFormatSchemeScreenControl {
    val formatControlByToken = TimeFormatToken.entries.associateWith { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController) {
        TimeFormatSchemeScreenControl(
            state = state,
            themeController = themeController,
            formatControlByToken = formatControlByToken,
        )
    }
}

@Stable
class TimeFormatSchemeScreenControl(
    val state: TimeFormatSchemeScreenState,
    val themeController: ThemeController,
    formatControlByToken: Map<TimeFormatToken, Control>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        *formatControlByToken.values.toTypedArray(),
    )
}

@Composable
private fun makeControlForToken(
    token: TimeFormatToken,
    state: TimeFormatSchemeScreenState,
    themeController: ThemeController,
): Control {
    val demoControl = rememberDateTimeFormatDemoControl(
        entries = TimeFormatValue.entries,
        state = state.dateTimeFormatDemoStateByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    dateTimeFormats = state.formats.dateTimeFormats.copy(
                        timeFormatScheme = state.timeFormatScheme.update(
                            format = token,
                            value = newValue,
                        )
                    )
                )
            )
        }
    )
    return Control.ControlColumn(
        name = token.name,
        controls = { demoControl.controls },
        expandedInitial = false,
    )
}

fun TimeFormatScheme.update(
    format: TimeFormatToken,
    value: TimeFormatValue,
) = when (format) {
    TimeFormatToken.Default -> copy(
        default = value,
    )
    TimeFormatToken.Short -> copy(
        short = value,
    )
    TimeFormatToken.Long -> copy(
        long = value,
    )
}
