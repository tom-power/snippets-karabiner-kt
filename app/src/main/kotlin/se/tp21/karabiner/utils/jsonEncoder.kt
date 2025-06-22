package se.tp21.karabiner.utils

import kotlinx.serialization.json.Json
import sh.kau.karabiner.ComplexModifications

fun jsonEncoder(complexModifications: ComplexModifications): String {
    val jsonEncoder = Json {
        prettyPrint = true
        encodeDefaults = true
        explicitNulls = false // Don't serialize null values
    }

    return jsonEncoder.encodeToString<ComplexModifications>(complexModifications)
}