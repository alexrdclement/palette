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
import com.alexrdclement.palette.app.demo.formats.datetime.DateTimeFormatDemo
import com.alexrdclement.palette.app.demo.formats.datetime.DateTimeFormatDemoState
import com.alexrdclement.palette.app.demo.formats.datetime.rememberDateTimeFormatDemoControl
import com.alexrdclement.palette.components.demo.DemoList
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.datetime.DateFormatValue
import com.alexrdclement.palette.formats.datetime.toFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.datetime.DateFormatScheme
import com.alexrdclement.palette.theme.format.datetime.DateFormatToken
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.format

@Composable
fun DateFormatSchemeScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberDateFormatSchemeScreenState(
        formats = themeController.formats,
    )
    val control = rememberDateFormatSchemeScreenControl(
        state = state,
        themeController = themeController,
    )

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Date",
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
                        localDateTime.date.format(format.toFormat())
                    },
                )
            }
        }
    }
}

@Composable
fun rememberDateFormatSchemeScreenState(
    formats: Formats,
): DateFormatSchemeScreenState {
    return rememberSaveable(
        formats.dateTimeFormats,
        saver = DateFormatSchemeScreenStateSaver(formats),
    ) {
        DateFormatSchemeScreenState(
            formats = formats,
        )
    }
}

@Stable
class DateFormatSchemeScreenState(
    val formats: Formats,
) {
    val dateFormatScheme = formats.dateTimeFormats.dateFormatScheme

    val dateFormatsByToken = DateFormatToken.entries.associateWith {
        when (it) {
            DateFormatToken.Default -> dateFormatScheme.default
            DateFormatToken.Long -> dateFormatScheme.long
            DateFormatToken.Short -> dateFormatScheme.short
        }
    }

    val dateTimeFormatDemoStateByToken = DateFormatToken.entries.associateWith {
        DateTimeFormatDemoState(
            tokenInitial = dateFormatsByToken[it]!!,
        )
    }
}

fun DateFormatSchemeScreenStateSaver(
    formats: Formats,
) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        DateFormatSchemeScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberDateFormatSchemeScreenControl(
    state: DateFormatSchemeScreenState,
    themeController: ThemeController,
): DateFormatSchemeScreenControl {
    val formatControlByToken = DateFormatToken.entries.associateWith { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController) {
        DateFormatSchemeScreenControl(
            state = state,
            themeController = themeController,
            formatControlByToken = formatControlByToken,
        )
    }
}

@Stable
class DateFormatSchemeScreenControl(
    val state: DateFormatSchemeScreenState,
    val themeController: ThemeController,
    formatControlByToken: Map<DateFormatToken, Control>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        *formatControlByToken.values.toTypedArray(),
    )
}

@Composable
private fun makeControlForToken(
    token: DateFormatToken,
    state: DateFormatSchemeScreenState,
    themeController: ThemeController,
): Control {
    val demoControl = rememberDateTimeFormatDemoControl(
        entries = DateFormatValue.entries,
        state = state.dateTimeFormatDemoStateByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    dateTimeFormats = state.formats.dateTimeFormats.copy(
                        dateFormatScheme = state.dateFormatScheme.update(
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

fun DateFormatScheme.update(
    format: DateFormatToken,
    value: DateFormatValue,
) = when (format) {
    DateFormatToken.Default -> copy(
        default = value,
    )
    DateFormatToken.Short -> copy(
        short = value,
    )
    DateFormatToken.Long -> copy(
        long = value,
    )
}
