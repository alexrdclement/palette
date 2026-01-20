package com.alexrdclement.palette.app.demo.formats.datetime

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexrdclement.palette.app.demo.DemoTopBar
import com.alexrdclement.palette.app.demo.formats.datetime.navigation.DateTimeFormat
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.formats.datetime.format
import com.alexrdclement.palette.formats.datetime.DateFormatValue
import com.alexrdclement.palette.formats.datetime.DateTimeFormatValue
import com.alexrdclement.palette.formats.datetime.InstantFormatValue
import com.alexrdclement.palette.formats.datetime.TimeFormatValue
import com.alexrdclement.palette.formats.datetime.toFormat
import com.alexrdclement.palette.formats.demo.datetime.DateTimeFormatDemo
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
                entries = DateFormatValue.entries,
                initial = DateFormatValue.YMD,
                format = { _, localDateTime, token ->
                    localDateTime.date.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
            DateTimeFormat.DateTime -> DateTimeFormatDemo(
                entries = DateTimeFormatValue.entries,
                initial = DateTimeFormatValue.YMDContinental,
                format = { _, localDateTime, token ->
                    localDateTime.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
            DateTimeFormat.Instant -> DateTimeFormatDemo(
                entries = InstantFormatValue.entries,
                initial = InstantFormatValue.YMDContinental,
                format = { instant, _, token ->
                    instant.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
            DateTimeFormat.Time -> DateTimeFormatDemo(
                entries = TimeFormatValue.entries,
                initial = TimeFormatValue.HMContinental,
                format = { _, localDateTime, token ->
                    localDateTime.time.format(token.toFormat())
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
