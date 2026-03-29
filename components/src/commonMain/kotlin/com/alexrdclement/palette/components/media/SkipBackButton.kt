package com.alexrdclement.palette.components.media

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.ShapeToken
import com.alexrdclement.palette.theme.styles.ButtonStyleToken

@Composable
fun SkipBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyleToken = ButtonStyleToken.Secondary,
    contentPadding: PaddingValues = PaddingValues(6.dp),
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        style = style,
        contentPadding = contentPadding,
        modifier = modifier
            .aspectRatio(1f)
    ) { shapePadding ->
        SkipIcon(
            modifier = Modifier
                .fillMaxSize()
                .padding(shapePadding)
                .graphicsLayer { scaleX = -1f },
        )
    }
}

@Preview
@Composable
private fun SkipBackButtonPreview() {
    PaletteTheme {
        SkipBackButton(onClick = {})
    }
}
