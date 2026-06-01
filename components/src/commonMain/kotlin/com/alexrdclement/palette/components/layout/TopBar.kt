package com.alexrdclement.palette.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.components.core.Text
import com.alexrdclement.palette.components.navigation.BackNavigationButton
import com.alexrdclement.palette.components.util.copy

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    spacing: Dp = 8.dp,
    navButton: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
) {
    val windowInsetsPaddingValues = WindowInsets.systemBars.asPaddingValues().copy(bottom = 0.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(windowInsetsPaddingValues)
            .consumeWindowInsets(windowInsetsPaddingValues)
            .padding(vertical = spacing)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(start = spacing)) {
            navButton?.invoke()
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = spacing)
        ) {
            title?.invoke()
        }
        Box(modifier = Modifier.padding(end = spacing)) {
            actions?.invoke()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface {
        TopBar(
            title = {
                Text("Title")
            }
        )
    }
}

@Preview
@Composable
private fun NavButtonPreview() {
    Surface {
        TopBar(
            title = {
                Text("Title")
            },
            navButton = {
                BackNavigationButton(onClick = {})
            }
        )
    }
}

@Preview
@Composable
private fun ActionsPreview() {
    Surface {
        TopBar(
            title = {
                Text("Title")
            },
            actions = {
                Button(
                    onClick = {},
                    modifier = Modifier.size(48.dp),
                ) {
                    Text("Action")
                }
            }
        )
    }
}
