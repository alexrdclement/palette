package com.alexrdclement.palette.app.demo.formats.datetime

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.DateTimeFormat
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.formats.datetime.format
import com.alexrdclement.palette.theme.format.datetime.DateFormatToken
import com.alexrdclement.palette.theme.format.datetime.DateTimeFormatToken
import com.alexrdclement.palette.theme.format.datetime.InstantFormatToken
import com.alexrdclement.palette.theme.format.datetime.TimeFormatToken
import com.alexrdclement.palette.theme.format.datetime.toFormat
import kotlinx.datetime.format

@Composable
fun DateTimeFormatScreen(
    format: DateTimeFormat,
    onNavigateBack: () -> Unit,
    onThemeClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DemoTopBar(
                title = format.title,
                onNavigateBack = onNavigateBack,
                onThemeClick = onThemeClick,
            )
        },
    ) { innerPadding ->
        when (format) {
            DateTimeFormat.Date -> DateTimeFormatDemo(
                entries = DateFormatToken.entries,
                initial = DateFormatToken.YMD,
                format = { _, localDateTime, token ->
                    localDateTime.date.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
            DateTimeFormat.DateTime -> DateTimeFormatDemo(
                entries = DateTimeFormatToken.entries,
                initial = DateTimeFormatToken.YMDContinental,
                format = { _, localDateTime, token ->
                    localDateTime.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
            DateTimeFormat.Instant -> DateTimeFormatDemo(
                entries = InstantFormatToken.entries,
                initial = InstantFormatToken.YMDContinental,
                format = { instant, _, token ->
                    instant.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
            DateTimeFormat.Time -> DateTimeFormatDemo(
                entries = TimeFormatToken.entries,
                initial = TimeFormatToken.HMContinental,
                format = { _, localDateTime, token ->
                    localDateTime.time.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
