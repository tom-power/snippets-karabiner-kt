package se.tp21.karabiner.snippets.rules

import kotlinx.serialization.Serializable

@Serializable
data class SnippetRule(
    val description: String,
    val keys: String,
    val replacement: String,
)

@Serializable
data class SnippetRules(
    val rules: List<SnippetRule>,
)
