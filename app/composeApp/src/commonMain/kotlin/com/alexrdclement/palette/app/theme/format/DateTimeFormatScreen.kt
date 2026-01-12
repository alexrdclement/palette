package com.alexrdclement.palette.app.theme.format

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.components.datetime.DateTimeFormatDemo
import com.alexrdclement.palette.app.demo.components.datetime.rememberDateTimeFormatDemoControl
import com.alexrdclement.palette.app.demo.components.datetime.rememberDateTimeFormatDemoState
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.Formats
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.format.DateTimeFormatScheme

@Composable
fun DateTimeFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberDateTimeFormatScreenState(themeState = themeController)
    val demoState = rememberDateTimeFormatDemoState(
        timeFormatInitial = state.dateTimeFormatScheme.timeFormat,
        dateFormatInitial = state.dateTimeFormatScheme.dateFormat,
        dateTimeFormatInitial = state.dateTimeFormatScheme.dateTimeFormat,
    )
    val demoControl = rememberDateTimeFormatDemoControl(state = demoState)

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
            state = demoState,
            control = demoControl,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}

@Composable
fun rememberDateTimeFormatScreenState(
    themeState: ThemeState,
): DateTimeFormatScreenState {
    return rememberSaveable(
        themeState,
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
        DateTimeFormatScreenState(
            themeState = themeState,
        )
    }
)
