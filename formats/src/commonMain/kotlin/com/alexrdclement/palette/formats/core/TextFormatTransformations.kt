package com.alexrdclement.palette.formats.core

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer

class StringFormatOutputTransformation(
    private val stringFormat: TextFormat,
) : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        val currentText = asCharSequence().toString()
        if (currentText.isEmpty()) return

        val formatted = stringFormat.format(currentText)

        // Only update if the formatted version is different
        if (formatted != currentText) {
            replace(0, length, formatted)
        }
    }
}
