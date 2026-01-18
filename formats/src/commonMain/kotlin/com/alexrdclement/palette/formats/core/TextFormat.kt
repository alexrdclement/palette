package com.alexrdclement.palette.formats.core

data class TextFormat(
    val capitalization: Capitalization = Capitalization.Sentence,
    val spaceSeparator: String = " ",
    val replacements: Map<String, String> = emptyMap(),
)

sealed class Capitalization {
    data object Sentence : Capitalization()
    data object Title : Capitalization()
    data object Uppercase : Capitalization()
    data object Lowercase : Capitalization()
    data object ReverseSentence : Capitalization()
    data class Alternating(val capitalizeFirstChar: Boolean = true) : Capitalization()

    enum class Key {
        Sentence,
        Title,
        Uppercase,
        Lowercase,
        ReverseSentence,
        Alternating,
    }

    companion object {
        fun toKey(value: Capitalization): Key = when (value) {
            Sentence -> Key.Sentence
            Title -> Key.Title
            Uppercase -> Key.Uppercase
            Lowercase -> Key.Lowercase
            ReverseSentence -> Key.ReverseSentence
            is Alternating -> Key.Alternating
        }

        fun fromKey(
            key: Key,
            capitalizeFirstChar: Boolean = true,
        ): Capitalization = when (key) {
            Key.Sentence -> Sentence
            Key.Title -> Title
            Key.Uppercase -> Uppercase
            Key.Lowercase -> Lowercase
            Key.ReverseSentence -> ReverseSentence
            Key.Alternating -> Alternating(capitalizeFirstChar)
        }
    }
}

object TextFormatReplacements {
    val UNICODE_ELLIPSES = "..." to "\u2026"
    val UNICODE_EM_DASH = "--" to "\u2014"
    val NEWLINE_UNICODE_BULLET = "\n* " to "\n\u2022 "
}

fun TextFormat.format(
    string: String,
): String {
    if (string.isEmpty()) return string

    if (spaceSeparator == " ") {
        val formatted = when (capitalization) {
            Capitalization.Sentence -> string.titlecaseFirstChar()
            Capitalization.Title -> {
                val words = string.split(' ')
                if (words.size == 1) string.titlecaseFirstChar()
                else words.joinToString(spaceSeparator) { it.titlecaseFirstChar() }
            }
            Capitalization.Uppercase -> string.uppercase()
            Capitalization.Lowercase -> string.lowercase()
            Capitalization.ReverseSentence -> string.split(' ').formatReverseSentenceCase(spaceSeparator)
            is Capitalization.Alternating -> string.formatAlternatingCase(capitalization.capitalizeFirstChar)
        }
        return formatted.applyReplacements(replacements)
    }

    val words = string.split(" ").filter { it.isNotEmpty() }
    if (words.isEmpty()) return ""

    val formatted = when (capitalization) {
        Capitalization.Sentence -> {
            val firstWord = words[0].titlecaseFirstChar()
            if (words.size == 1) firstWord
            else buildString {
                append(firstWord)
                for (i in 1 until words.size) {
                    append(spaceSeparator)
                    append(words[i])
                }
            }
        }
        Capitalization.Title -> words.joinToString(spaceSeparator) { it.titlecaseFirstChar() }
        Capitalization.Uppercase -> words.joinToString(spaceSeparator) { it.uppercase() }
        Capitalization.Lowercase -> words.joinToString(spaceSeparator) { it.lowercase() }
        Capitalization.ReverseSentence -> words.formatReverseSentenceCase(spaceSeparator)
        is Capitalization.Alternating -> words.joinToString(spaceSeparator).formatAlternatingCase(capitalization.capitalizeFirstChar)
    }

    return formatted.applyReplacements(replacements)
}

private fun List<String>.formatReverseSentenceCase(separator: String): String {
    if (isEmpty()) return ""
    val firstWord = this[0].lowercaseFirstChar()
    if (size == 1) return firstWord
    return buildString {
        append(firstWord)
        for (i in 1 until size) {
            append(separator)
            append(this@formatReverseSentenceCase[i].titlecaseFirstChar())
        }
    }
}

private fun String.titlecaseFirstChar() = replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
private fun String.lowercaseFirstChar() = replaceFirstChar { if (it.isUpperCase()) it.lowercase() else it.toString() }

private fun String.formatAlternatingCase(capitalizeFirstChar: Boolean): String {
    if (isEmpty()) return this

    val result = StringBuilder()
    var letterIndex = 0

    for (char in this) {
        if (char.isWhitespace()) {
            result.append(char)
        } else {
            val shouldCapitalize = if (capitalizeFirstChar) {
                letterIndex % 2 == 0
            } else {
                letterIndex % 2 == 1
            }
            result.append(if (shouldCapitalize) char.uppercase() else char.lowercase())
            letterIndex++
        }
    }

    return result.toString()
}

private fun String.applyReplacements(replacements: Map<String, String>): String {
    if (replacements.isEmpty()) return this

    var result = this
    for ((from, to) in replacements) {
        result = result.replace(from, to)
    }
    return result
}
