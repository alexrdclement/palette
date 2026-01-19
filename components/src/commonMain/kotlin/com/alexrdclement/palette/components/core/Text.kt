package com.alexrdclement.palette.components.core

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.alexrdclement.palette.components.LocalContentColor
import com.alexrdclement.palette.formats.core.format
import com.alexrdclement.palette.theme.PaletteTheme
import com.alexrdclement.palette.theme.styles.TextStyle
import com.alexrdclement.palette.theme.preview.TextStylePreviewParameterProvider

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = PaletteTheme.styles.text.bodyMedium,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    autoSize: TextAutoSize? = null,
) {
    val formattedText = remember(text, style.format) { style.format.format(text) }
    val color = style.composeTextStyle.color.takeOrElse { LocalContentColor.current }
    BasicText(
        text = formattedText,
        modifier = modifier,
        style = style.composeTextStyle.copy(color = color),
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        autoSize = autoSize,
    )
}

@Composable
fun Text(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = PaletteTheme.styles.text.bodyMedium,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    autoSize: TextAutoSize? = null,
) {
    val color = style.composeTextStyle.color.takeOrElse { LocalContentColor.current }
    BasicText(
        text = text,
        modifier = modifier,
        style = style.composeTextStyle.copy(color = color),
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent,
        autoSize = autoSize,
    )
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(TextStylePreviewParameterProvider::class) textStylePair: Pair<String, TextStyle>,
) {
    PaletteTheme {
        Surface {
            Text(
                text = textStylePair.first,
                style = textStylePair.second,
            )
        }
    }
}
