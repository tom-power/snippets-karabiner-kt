package se.tp21.karabiner.utils

import kotlinx.serialization.json.Json
import se.tp21.karabiner.snippets.rules.SnippetRules

fun snippetJsonEncoder(input: String): SnippetRules {
    val jsonEncoder = Json {
        prettyPrint = true
        encodeDefaults = true
        explicitNulls = false // Don't serialize null values
    }

    return jsonEncoder.decodeFromString(input)
}