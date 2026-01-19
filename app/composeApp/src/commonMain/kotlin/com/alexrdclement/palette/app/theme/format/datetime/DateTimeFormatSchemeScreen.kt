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
import com.alexrdclement.palette.formats.datetime.DateTimeFormatValue
import com.alexrdclement.palette.formats.datetime.toFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatScheme
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatToken
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.format

@Composable
fun DateTimeFormatSchemeScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberDateTimeFormatSchemeScreenState(
        formats = themeController.formats,
    )
    val control = rememberDateTimeFormatSchemeScreenControl(
        state = state,
        themeController = themeController,
    )

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "DateTime",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DemoList(
            items = state.dateFormatsByToken.entries.toList(),
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
                        localDateTime.format(format.toFormat())
                    },
                )
            }
        }
    }
}

@Composable
fun rememberDateTimeFormatSchemeScreenState(
    formats: Formats,
): DateTimeFormatSchemeScreenState {
    return rememberSaveable(
        formats.dateTimeFormats,
        saver = DateTimeFormatSchemeScreenStateSaver(formats),
    ) {
        DateTimeFormatSchemeScreenState(
            formats = formats,
        )
    }
}

@Stable
class DateTimeFormatSchemeScreenState(
    val formats: Formats,
) {
    val dateTimeFormatScheme = formats.dateTimeFormats.dateTimeFormatScheme

    val dateFormatsByToken = DateTimeFormatToken.entries.associateWith {
        when (it) {
            DateTimeFormatToken.Default -> dateTimeFormatScheme.default
            DateTimeFormatToken.Long -> dateTimeFormatScheme.long
            DateTimeFormatToken.Short -> dateTimeFormatScheme.short
        }
    }

    val dateTimeFormatDemoStateByToken = DateTimeFormatToken.entries.associateWith {
        DateTimeFormatDemoState(
            tokenInitial = dateFormatsByToken[it]!!,
        )
    }
}

fun DateTimeFormatSchemeScreenStateSaver(
    formats: Formats,
) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        DateTimeFormatSchemeScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberDateTimeFormatSchemeScreenControl(
    state: DateTimeFormatSchemeScreenState,
    themeController: ThemeController,
): DateTimeFormatSchemeScreenControl {
    val formatControlByToken = DateTimeFormatToken.entries.associateWith { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController) {
        DateTimeFormatSchemeScreenControl(
            state = state,
            themeController = themeController,
            formatControlByToken = formatControlByToken,
        )
    }
}

@Stable
class DateTimeFormatSchemeScreenControl(
    val state: DateTimeFormatSchemeScreenState,
    val themeController: ThemeController,
    formatControlByToken: Map<DateTimeFormatToken, Control>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        *formatControlByToken.values.toTypedArray(),
    )
}

@Composable
private fun makeControlForToken(
    token: DateTimeFormatToken,
    state: DateTimeFormatSchemeScreenState,
    themeController: ThemeController,
): Control {
    val demoControl = rememberDateTimeFormatDemoControl(
        entries = DateTimeFormatValue.entries,
        state = state.dateTimeFormatDemoStateByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    dateTimeFormats = state.formats.dateTimeFormats.copy(
                        dateTimeFormatScheme = state.dateTimeFormatScheme.update(
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

fun DateTimeFormatScheme.update(
    format: DateTimeFormatToken,
    value: DateTimeFormatValue,
) = when (format) {
    DateTimeFormatToken.Default -> copy(
        default = value,
    )
    DateTimeFormatToken.Short -> copy(
        short = value,
    )
    DateTimeFormatToken.Long -> copy(
        long = value,
    )
}
