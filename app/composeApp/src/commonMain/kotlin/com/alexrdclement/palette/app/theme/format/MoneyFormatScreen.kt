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
import com.alexrdclement.palette.app.demo.components.money.MoneyFormatDemo
import com.alexrdclement.palette.app.demo.components.money.MoneyFormatDemoControl
import com.alexrdclement.palette.app.demo.components.money.MoneyFormatDemoState
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.layout.Scaffold
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.theme.Formats
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.control.ThemeController
import com.alexrdclement.palette.theme.control.ThemeState
import com.alexrdclement.palette.theme.format.MoneyFormat
import com.alexrdclement.palette.theme.format.MoneyFormatScheme
import com.alexrdclement.palette.theme.format.MoneyFormatToken
import com.alexrdclement.palette.theme.format.toFormat
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MoneyFormatScreen(
    themeController: ThemeController,
    onNavigateBack: () -> Unit,
) {
    val state = rememberMoneyFormatScreenState(themeState = themeController)
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
    themeState: ThemeState,
): MoneyFormatScreenState {
    return rememberSaveable(
        themeState,
        saver = MoneyFormatScreenStateSaver(themeState),
    ) {
        MoneyFormatScreenState(
            themeState = themeState,
        )
    }
}

@Stable
class MoneyFormatScreenState(
    val themeState: ThemeState,
) {
    val formats: Formats
        get() = themeState.formats

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
    themeState: ThemeState,
) = mapSaverSafe(
    save = { state ->
        mapOf()
    },
    restore = { map ->
        MoneyFormatScreenState(
            themeState = themeState,
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
