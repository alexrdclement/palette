package com.alexrdclement.palette.app.theme.format.datetime

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.datetime.DateTimeFormatDemo
import com.alexrdclement.palette.app.demo.formats.datetime.rememberDateTimeFormatDemoControl
import com.alexrdclement.palette.app.demo.formats.datetime.rememberDateTimeFormatDemoState
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.datetime.format
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.datetime.DateFormatToken
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatScheme
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatToken
import com.alexrdclement.palette.theme.format.datetime.InstantFormatToken
import com.alexrdclement.palette.theme.format.datetime.TimeFormatToken
import com.alexrdclement.palette.theme.format.datetime.toFormat
import com.alexrdclement.palette.theme.format.update
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlin.enums.EnumEntries
import kotlin.time.Instant

@Composable
fun DateTimeFormatScreen(
    format: DateTimeFormatCatalogItem,
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberDateTimeFormatScreenState(themeState = themeController)
    Scaffold(
        topBar = {
            DemoTopBar(
                title = "DateTime format",
                onNavigateBack = onNavigateBack,
                onThemeClick = {},
                actions = {},
            )
        },
    ) { paddingValues ->
        DateTimeFormatDemo(
            format = format,
            state = state,
            themeController = themeController,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

@Composable
private fun DateTimeFormatDemo(
    format: DateTimeFormatCatalogItem,
    state: DateTimeFormatScreenState,
    themeController: ThemeController,
    modifier: Modifier = Modifier,
) {
    when (format) {
        DateTimeFormatCatalogItem.Date -> DateTimeFormatDemo(
            entries = DateFormatToken.entries,
            formatInitial = state.dateTimeFormatScheme.dateFormat,
            format = { _, localDateTime, _ ->
                localDateTime.date.format(state.dateTimeFormatScheme.dateFormat.toFormat())
            },
            onValueChange = { token ->
                val formats = themeController.formats.update(
                    format = format,
                    dateFormat = token,
                )
                themeController.setFormats(formats)
            },
            modifier = modifier,
        )
        DateTimeFormatCatalogItem.DateTime -> DateTimeFormatDemo(
            entries = DateTimeFormatToken.entries,
            formatInitial = state.dateTimeFormatScheme.dateTimeFormat,
            format = { _, localDateTime, _ ->
                localDateTime.format(state.dateTimeFormatScheme.dateTimeFormat.toFormat())
            },
            onValueChange = { token ->
                val formats = themeController.formats.update(
                    format = format,
                    dateTimeFormat = token,
                )
                themeController.setFormats(formats)
            },
            modifier = modifier,
        )
        DateTimeFormatCatalogItem.Instant -> DateTimeFormatDemo(
            entries = InstantFormatToken.entries,
            formatInitial = state.dateTimeFormatScheme.instantFormat,
            format = { instant, _, _ ->
                instant.format(state.dateTimeFormatScheme.instantFormat.toFormat())
            },
            onValueChange = { token ->
                val formats = themeController.formats.update(
                    format = format,
                    instantFormat = token,
                )
                themeController.setFormats(formats)
            },
            modifier = modifier,
        )
        DateTimeFormatCatalogItem.Time -> DateTimeFormatDemo(
            entries = TimeFormatToken.entries,
            formatInitial = state.dateTimeFormatScheme.timeFormat,
            format = { _, localDateTime, _ ->
                localDateTime.time.format(state.dateTimeFormatScheme.timeFormat.toFormat())
            },
            onValueChange = { token ->
                val formats = themeController.formats.update(
                    format = format,
                    timeFormat = token,
                )
                themeController.setFormats(formats)
            },
            modifier = modifier,
        )
    }
}

@Composable
private fun <T : Enum<T>> DateTimeFormatDemo(
    entries: EnumEntries<T>,
    formatInitial: T,
    format: (Instant, LocalDateTime, T) -> String,
    onValueChange: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    val demoState = rememberDateTimeFormatDemoState(
        formatInitial = formatInitial,
    )
    val demoControl = rememberDateTimeFormatDemoControl(
        entries = entries,
        state = demoState,
        onValueChange = onValueChange,
    )
    DateTimeFormatDemo(
        entries = entries,
        initial = formatInitial,
        format = format,
        state = demoState,
        control = demoControl,
        modifier = modifier,
    )
}

@Composable
fun rememberDateTimeFormatScreenState(
    themeState: ThemeState,
): DateTimeFormatScreenState {
    return rememberSaveable(
        themeState.formats.dateTimeFormats,
        saver = DateTimeFormatScreenStateSaver(themeState),
    ) {
        DateTimeFormatScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class DateTimeFormatScreenState(
    val themeState: ThemeState,
) {
    val formats: Formats
        get() = themeState.formats

    val dateTimeFormatScheme: DateTimeFormatScheme
        get() = formats.dateTimeFormats
}

fun DateTimeFormatScreenStateSaver(themeState: ThemeState) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        println("rememberDateTimeFormatScreenState (restore)")
        DateTimeFormatScreenState(
            themeState = themeState,
        )
    }
)

private fun Formats.update(
    format: DateTimeFormatCatalogItem,
    dateFormat: DateFormatToken? = null,
    dateTimeFormat: DateTimeFormatToken? = null,
    instantFormat: InstantFormatToken? = null,
    timeFormat: TimeFormatToken? = null,
): Formats {
    val dateTimeFormatScheme = this.dateTimeFormats.update(
        format = format,
        dateFormat = dateFormat,
        dateTimeFormat = dateTimeFormat,
        instantFormat = instantFormat,
        timeFormat = timeFormat,
    )
    return this.update(
        dateTimeFormats = dateTimeFormatScheme,
    )
}

private fun DateTimeFormatScheme.update(
    format: DateTimeFormatCatalogItem,
    dateFormat: DateFormatToken? = null,
    dateTimeFormat: DateTimeFormatToken? = null,
    instantFormat: InstantFormatToken? = null,
    timeFormat: TimeFormatToken? = null,
): DateTimeFormatScheme {
    return when (format) {
        DateTimeFormatCatalogItem.Date -> copy(
            dateFormat = dateFormat ?: this.dateFormat,
        )
        DateTimeFormatCatalogItem.DateTime -> copy(
            dateTimeFormat = dateTimeFormat ?: this.dateTimeFormat,
        )
        DateTimeFormatCatalogItem.Instant -> copy(
            instantFormat = instantFormat ?: this.instantFormat,
        )
        DateTimeFormatCatalogItem.Time -> copy(
            timeFormat = timeFormat ?: this.timeFormat,
        )
    }
}
