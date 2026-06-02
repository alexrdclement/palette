package com.alexrdclement.palette.theme.components.media

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.theme.styles.ButtonStyleToken
import com.alexrdclement.palette.theme.components.toComponentStyle
import com.alexrdclement.palette.components.media.SkipButton as ComponentSkipButton
import com.alexrdclement.palette.components.media.SkipBackButton as ComponentSkipBackButton

@Composable
fun SkipButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyleToken = ButtonStyleToken.Secondary,
    contentPadding: PaddingValues = PaddingValues(6.dp),
) {
    ComponentSkipButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style.toComponentStyle(),
        contentPadding = contentPadding,
    )
}

@Composable
fun SkipBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyleToken = ButtonStyleToken.Secondary,
    contentPadding: PaddingValues = PaddingValues(6.dp),
) {
    ComponentSkipBackButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = style.toComponentStyle(),
        contentPadding = contentPadding,
    )
}
