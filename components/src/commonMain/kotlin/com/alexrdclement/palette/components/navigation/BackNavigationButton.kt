package com.alexrdclement.palette.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.alexrdclement.palette.components.core.Button
import com.alexrdclement.palette.components.core.Surface
import com.alexrdclement.palette.theme.ColorToken
import com.alexrdclement.palette.theme.PaletteTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BackNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        contentColor = ColorToken.Primary,
        containerColor = ColorToken.Surface,
        borderStyle = null,
        contentPadding = PaddingValues(PaletteTheme.spacing.medium),
        modifier = modifier.size(48.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(BackNavigationIconShape)
                .background(PaletteTheme.colorScheme.primary)
        )
    }
}

private const val BackNavigationIconWidthProportion = 0.8f
private val BackNavigationIconShape: Shape = GenericShape { size, _ ->
    val offsetEndX = size.width * BackNavigationIconWidthProportion
    moveTo(offsetEndX, 0f)
    lineTo(0f, size.height / 2f)
    lineTo(offsetEndX, size.height)
    lineTo(offsetEndX, 0f)
}

@Preview
@Composable
private fun BackNavigationButtonPreview() {
    PaletteTheme {
        Surface {
            BackNavigationButton(
                onClick = {},
            )
        }
    }
}
