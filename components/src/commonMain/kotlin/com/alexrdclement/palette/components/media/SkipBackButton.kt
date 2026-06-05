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
import com.alexrdclement.palette.components.core.withContentPadding
import com.alexrdclement.palette.components.core.ButtonStyle

@Composable
fun SkipBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.Default(),
    contentPadding: PaddingValues = PaddingValues(6.dp),
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        style = style.withContentPadding(contentPadding),
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
    SkipBackButton(onClick = {})
}
