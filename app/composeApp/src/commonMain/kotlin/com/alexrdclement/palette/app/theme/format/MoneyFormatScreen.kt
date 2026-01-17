package com.alexrdclement.palette.app.theme.format

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
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatDemo
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatDemoControl
import com.alexrdclement.palette.app.demo.formats.money.MoneyFormatDemoState
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.money.MoneyFormat
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.format.Formats
import com.alexrdclement.palette.theme.format.money.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.money.MoneyFormatToken
import com.alexrdclement.palette.theme.format.money.toFormat
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MoneyFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberMoneyFormatScreenState(formats = themeController.formats)
    val control = rememberMoneyFormatScreenControl(state = state, themeController = themeController)

    Scaffold(
        topBar = {
            DemoTopBar(
                title = "Money format",
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
                items(state.moneyFormatsByToken.keys.toList()) { token ->
                    MoneyFormatDemo(
                        state = state.moneyFormatDemoStatesByToken[token]!!,
                    )
                }
            }
        }
    }
}

@Composable
fun rememberMoneyFormatScreenState(
    formats: Formats,
): MoneyFormatScreenState {
    return rememberSaveable(
        formats,
        saver = MoneyFormatScreenStateSaver(formats),
    ) {
        MoneyFormatScreenState(
            formats = formats,
        )
    }
}

@Stable
class MoneyFormatScreenState(
    val formats: Formats,
) {
    val moneyFormatScheme: MoneyFormatScheme
        get() = formats.moneyFormats

    val moneyFormatsByToken = MoneyFormatToken.entries.associateWith { token ->
        token.toFormat(moneyFormatScheme)
    }

    val moneyFormatDemoStatesByToken = MoneyFormatToken.entries.associateWith { token ->
        MoneyFormatDemoState(
            moneyFormatInitial = moneyFormatsByToken[token]!!,
        )
    }
}

fun MoneyFormatScreenStateSaver(
    formats: Formats,
) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        MoneyFormatScreenState(
            formats = formats,
        )
    }
)

@Composable
fun rememberMoneyFormatScreenControl(
    state: MoneyFormatScreenState,
    themeController: ThemeController,
): MoneyFormatScreenControl {
    val formatControls = MoneyFormatToken.entries.map { token ->
        makeControlForToken(
            token = token,
            state = state,
            themeController = themeController,
        )
    }
    return remember(state, themeController, formatControls) {
        MoneyFormatScreenControl(
            state = state,
            themeController = themeController,
            formatControls = formatControls.toPersistentList(),
        )
    }
}

@Stable
class MoneyFormatScreenControl(
    val state: MoneyFormatScreenState,
    val themeController: ThemeController,
    val formatControls: PersistentList<Control>,
) {
    val controls: PersistentList<Control> = formatControls
}

@Composable
private fun makeControlForToken(
    token: MoneyFormatToken,
    state: MoneyFormatScreenState,
    themeController: ThemeController,
): Control {

    val moneyFormatDemoControl = MoneyFormatDemoControl(
        state = state.moneyFormatDemoStatesByToken[token]!!,
        onValueChange = { newValue ->
            themeController.setFormats(
                formats = state.formats.copy(
                    moneyFormats = state.moneyFormatScheme.update(
                        token = token,
                        moneyFormat = newValue,
                    )
                )
            )
        }
    )

    return Control.ControlColumn(
        name = token.name,
        controls = { moneyFormatDemoControl.controls },
        expandedInitial = true,
    )
}

fun MoneyFormatScheme.update(
    token: MoneyFormatToken,
    moneyFormat: MoneyFormat,
): MoneyFormatScheme {
    return when (token) {
        MoneyFormatToken.Default -> this.copy(
            default = moneyFormat,
        )
    }
}
