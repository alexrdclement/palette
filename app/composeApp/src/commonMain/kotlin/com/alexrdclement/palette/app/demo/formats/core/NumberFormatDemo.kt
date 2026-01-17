package com.alexrdclement.palette.app.demo.formats.core

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextFieldState.Saver.restore
import androidx.compose.foundation.text.input.TextFieldState.Saver.save
import androidx.compose.foundation.text.input.byValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.demo.Demo
import com.alexrdclement.palette.components.demo.DemoScope
import com.alexrdclement.palette.components.demo.control.Control
import com.alexrdclement.palette.components.util.mapSaverSafe
import com.alexrdclement.palette.formats.core.NumberFormat
import com.alexrdclement.palette.formats.core.format
import com.alexrdclement.palette.formats.core.update
import com.alexrdclement.palette.theme.PaletteTheme
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun NumberFormatDemo(
    state: NumberFormatDemoState = rememberNumberFormatDemoState(),
    control: NumberFormatDemoControl = rememberNumberFormatDemoControl(state),
    modifier: Modifier = Modifier,
) {
    Demo(
        controls = control.controls,
        modifier = modifier
            .fillMaxSize()
    ) {
        NumberFormatDemo(
            state = state,
        )
    }
}

@Composable
fun DemoScope.NumberFormatDemo(
    state: NumberFormatDemoState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = state.text,
        style = PaletteTheme.typography.headline,
        modifier = modifier.align(Alignment.Center)
    )
}

@Composable
fun rememberNumberFormatDemoState(
    numberFormat: NumberFormat = NumberFormat(),
    demoTextFieldState: TextFieldState = TextFieldState("12345678.90"),
): NumberFormatDemoState {
    return rememberSaveable(
        numberFormat,
        demoTextFieldState,
        saver = NumberFormatDemoStateSaver(),
    ) {
        NumberFormatDemoState(
            numberFormatInitial = numberFormat,
            demoTextFieldState = demoTextFieldState,
        )
    }
}

@Stable
class NumberFormatDemoState(
    numberFormatInitial: NumberFormat = NumberFormat(),
    val demoTextFieldState: TextFieldState = TextFieldState("12345678.90"),
    val minNumDecimalValuesTextFieldState: TextFieldState = TextFieldState(
        initialText = numberFormatInitial.minNumDecimalValues.toString(),
    ),
    val maxNumDecimalValuesTextFieldState: TextFieldState = TextFieldState(
        initialText = numberFormatInitial.maxNumDecimalValues.toString(),
    ),
    val positiveSignTextFieldState: TextFieldState = TextFieldState(
        initialText = numberFormatInitial.positiveSign ?: "",
    ),
    val negativeSignTextFieldState: TextFieldState = TextFieldState(
        initialText = numberFormatInitial.negativeSign,
    ),
    val groupingChunkTextFieldState: TextFieldState = TextFieldState(
        initialText = numberFormatInitial.groupingChunk.toString(),
    ),
) {
    var numberFormat by mutableStateOf(numberFormatInitial)
        internal set

    val text by derivedStateOf {
        numberFormat.format(demoTextFieldState.text.toString())
    }
}

private const val demoTextFieldStateKey = "demoTextFieldState"
private const val minNumDecimalValuesTextFieldStateKey = "minNumDecimalValuesTextFieldState"
private const val maxNumDecimalValuesTextFieldStateKey = "maxNumDecimalValuesTextFieldState"
private const val positiveSignTextFieldStateKey = "positiveSignTextFieldState"
private const val negativeSignTextFieldStateKey = "negativeSignTextFieldState"
private const val groupingChunkTextFieldStateKey = "groupingChunkTextFieldState"

fun NumberFormatDemoStateSaver() = mapSaverSafe(
    save = { state ->
        mapOf(
            demoTextFieldStateKey to save(state.demoTextFieldState),
            minNumDecimalValuesTextFieldStateKey to save(state.minNumDecimalValuesTextFieldState),
            maxNumDecimalValuesTextFieldStateKey to save(state.maxNumDecimalValuesTextFieldState),
            positiveSignTextFieldStateKey to save(state.positiveSignTextFieldState),
            negativeSignTextFieldStateKey to save(state.negativeSignTextFieldState),
            groupingChunkTextFieldStateKey to save(state.groupingChunkTextFieldState),
        )
    },
    restore = { map ->
        NumberFormatDemoState(
            demoTextFieldState = restore(map[demoTextFieldStateKey]!!) as TextFieldState,
            minNumDecimalValuesTextFieldState = restore(map[minNumDecimalValuesTextFieldStateKey]!!) as TextFieldState,
            maxNumDecimalValuesTextFieldState = restore(map[maxNumDecimalValuesTextFieldStateKey]!!) as TextFieldState,
            positiveSignTextFieldState = restore(map[positiveSignTextFieldStateKey]!!) as TextFieldState,
            negativeSignTextFieldState = restore(map[negativeSignTextFieldStateKey]!!) as TextFieldState,
            groupingChunkTextFieldState = restore(map[groupingChunkTextFieldStateKey]!!) as TextFieldState,
        )
    }
)

@Composable
fun rememberNumberFormatDemoControl(
    state: NumberFormatDemoState,
    onValueChange: (NumberFormat) -> Unit = { state.numberFormat = it },
): NumberFormatDemoControl {
    val onValueChange by rememberUpdatedState(onValueChange)
    return remember(state, onValueChange) {
        NumberFormatDemoControl(
            state = state,
            onValueChange = onValueChange,
        )
    }
}

@Stable
class NumberFormatDemoControl(
    val state: NumberFormatDemoState = NumberFormatDemoState(),
    val onValueChange: (NumberFormat) -> Unit = { state.numberFormat = it },
    val includeTextFieldControl: Boolean = true,
) {
    val demoTextFieldControl = Control.TextField(
        name = "Number",
        includeLabel = false,
        textFieldState = state.demoTextFieldState,
        keyboardOptions = {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            )
        }
    )

    val minNumDecimalValuesControl = Control.TextField(
        name = "Min num decimal values",
        textFieldState = state.minNumDecimalValuesTextFieldState,
        onValueChange = { newValue ->
            val min = newValue.toIntOrNull() ?: state.numberFormat.minNumDecimalValues
            val max = state.numberFormat.maxNumDecimalValues
            val newState = state.numberFormat.update(
                numDecimalValuesRange = min..max,
            )
            onValueChange(newState)
        },
        keyboardOptions = {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            )
        },
        inputTransformation = {
            InputTransformation.byValue { _, proposed ->
                proposed.filter { it.isDigit() }
            }
        }
    )

    val maxNumDecimalValuesControl = Control.TextField(
        name = "Max num decimal values",
        textFieldState = state.maxNumDecimalValuesTextFieldState,
        onValueChange = { newValue ->
            val min = state.numberFormat.minNumDecimalValues
            val max = newValue.toIntOrNull() ?: state.numberFormat.maxNumDecimalValues
            val newState = state.numberFormat.update(
                numDecimalValuesRange = min..max,
            )
            onValueChange(newState)
        },
        keyboardOptions = {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            )
        },
        inputTransformation = {
            InputTransformation.byValue { _, proposed ->
                proposed.filter { it.isDigit() }
            }
        }
    )

    val positiveSignControl = Control.TextField(
        name = "Positive sign",
        textFieldState = state.positiveSignTextFieldState,
        onValueChange = { newValue ->
            val newState = state.numberFormat.update(
                positiveSign = newValue,
            )
            onValueChange(newState)
        },
    )

    val negativeSignControl = Control.TextField(
        name = "Negative sign",
        textFieldState = state.negativeSignTextFieldState,
        onValueChange = { newValue ->
            val newState = state.numberFormat.update(
                negativeSign = newValue,
            )
            onValueChange(newState)
        },
    )

    val groupingSeparatorControl = Control.CharField(
        name = "Grouping separator",
        value = { state.numberFormat.groupingSeparator },
        onValueChange = { newValue ->
            val newState = state.numberFormat.update(
                groupingSeparator = newValue,
            )
            onValueChange(newState)
        },
    )

    val groupingChunkControl = Control.TextField(
        name = "Grouping chunk",
        textFieldState = state.groupingChunkTextFieldState,
        onValueChange = { newValue ->
            val newState = state.numberFormat.update(
                groupingChunk = newValue.toIntOrNull(),
            )
            onValueChange(newState)
        },
        keyboardOptions = {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            )
        },
        inputTransformation = {
            InputTransformation.byValue { _, proposed ->
                proposed.filter { it.isDigit() }
            }
        }
    )

    val decimalSeparatorControl = Control.CharField(
        name = "Decimal separator",
        value = { state.numberFormat.decimalSeparator },
        onValueChange = { newValue ->
            val newState = state.numberFormat.update(
                decimalSeparator = newValue,
            )
            onValueChange(newState)
        },
    )

    val controls: PersistentList<Control> = buildList {
        if (includeTextFieldControl) {
            add(demoTextFieldControl)
        }
        addAll(
            listOf(
                minNumDecimalValuesControl,
                maxNumDecimalValuesControl,
                positiveSignControl,
                negativeSignControl,
                groupingSeparatorControl,
                groupingChunkControl,
                decimalSeparatorControl,
            )
        )
    }.toPersistentList()
}
