package com.alexrdclement.palette.app.theme.format.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.BoxWithLabel
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.datetime.format
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.datetime.InstantFormatScheme
import com.alexrdclement.palette.theme.format.datetime.InstantFormatToken
import com.alexrdclement.palette.formats.datetime.InstantFormatValue
import com.alexrdclement.palette.formats.datetime.toFormat
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun InstantFormatSchemeScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberInstantFormatSchemeScreenState(
        formats = themeController.formats,
    )
    val control = rememberInstantFormatSchemeScreenControl(
        state = state,
        themeController = themeController,
    )

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Instant",
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
                    .align(Alignment.Center)
            ) {
                items(state.instantFormatsByToken.entries.toList()) { (token, format)->
                    BoxWithLabel(
                        label = token.name,
                        modifier = Modifier
                            .padding(horizontal = PaletteTheme.spacing.medium)
                    ) {
                        DateTimeFormatDemo(
                            state = state.dateTimeFormatDemoStateByToken[token]!!,
                            format = { instant, _, _ ->
                                instant.format(format.toFormat())
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun rememberInstantFormatSchemeScreenState(
    formats: Formats,
): InstantFormatSchemeScreenState {
    return rememberSaveable(
        formats.dateTimeFormats,
        saver = InstantFormatSchemeScreenStateSaver(formats),
    ) {
        InstantFormatSchemeScreenState(
            formats = formats,
        )
    }
}

@Stable
class InstantFormatSchemeScreenState(
    val formats: Formats,
) {
    val instantFormatScheme = formats.dateTimeFormats.instantFormatScheme

    val instantFormatsByToken = InstantFormatToken.entries.associateWith {
        when (it) {
            InstantFormatToken.Default -> instantFormatScheme.default
            InstantFormatToken.Long -> instantFormatScheme.long
            InstantFormatToken.Short -> instantFormatScheme.short
        }
    }

    val dateTimeFormatDemoStateByToken = InstantFormatToken.entries.associateWith {
        DateTimeFormatDemoState(
            tokenInitial = instantFormatsByToken[it]!!,
        )
    }
}

fun InstantFormatSchemeScreenStateSaver(
    formats: Formats,
) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        InstantFormatSchemeScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberInstantFormatSchemeScreenControl(
    state: InstantFormatSchemeScreenState,
    themeController: ThemeController,
): InstantFormatSchemeScreenControl {
    val formatControlByToken = InstantFormatToken.entries.associateWith { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController) {
        InstantFormatSchemeScreenControl(
            state = state,
            themeController = themeController,
            formatControlByToken = formatControlByToken,
        )
    }
}

@Stable
class InstantFormatSchemeScreenControl(
    val state: InstantFormatSchemeScreenState,
    val themeController: ThemeController,
    formatControlByToken: Map<InstantFormatToken, Control>,
) {
    val controls: PersistentList<Control> = persistentListOf(
        *formatControlByToken.values.toTypedArray(),
    )
}

@Composable
private fun makeControlForToken(
    token: InstantFormatToken,
    state: InstantFormatSchemeScreenState,
    themeController: ThemeController,
): Control {
    val demoControl = rememberDateTimeFormatDemoControl(
        entries = InstantFormatValue.entries,
        state = state.dateTimeFormatDemoStateByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    dateTimeFormats = state.formats.dateTimeFormats.copy(
                        instantFormatScheme = state.instantFormatScheme.update(
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

fun InstantFormatScheme.update(
    format: InstantFormatToken,
    value: InstantFormatValue,
) = when (format) {
    InstantFormatToken.Default -> copy(
        default = value,
    )
    InstantFormatToken.Short -> copy(
        short = value,
    )
    InstantFormatToken.Long -> copy(
        long = value,
    )
}
