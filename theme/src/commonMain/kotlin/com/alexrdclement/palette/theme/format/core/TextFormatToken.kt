package com.alexrdclement.palette.theme.format.core

import com.alexrdclement.palette.formats.core.Capitalization
import com.alexrdclement.palette.formats.core.TextFormatReplacements
import com.alexrdclement.palette.formats.core.TextFormat

enum class TextFormatToken {
    Default,
    Title,
    TextField,
}

fun TextFormatToken.toFormat(): TextFormat {
    val defaultPunctuationReplacements = mapOf(
        TextFormatReplacements.UNICODE_ELLIPSES,
        TextFormatReplacements.UNICODE_EM_DASH,
        TextFormatReplacements.NEWLINE_UNICODE_BULLET
    )
    return when (this) {
        TextFormatToken.Default -> TextFormat(
            capitalization = Capitalization.Sentence,
            spaceSeparator = " ",
            replacements = defaultPunctuationReplacements,
        )
        TextFormatToken.Title -> TextFormat(
            capitalization = Capitalization.Title,
            spaceSeparator = " ",
            replacements = defaultPunctuationReplacements,
        )
        TextFormatToken.TextField -> TextFormat(
            capitalization = Capitalization.Sentence,
            spaceSeparator = " ",
            replacements = defaultPunctuationReplacements,
        )
    }
}
