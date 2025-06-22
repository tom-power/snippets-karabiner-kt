package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.utils.snippetReplacementRulesFor
import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode.*
import sh.kau.karabiner.To

fun ij(): List<KarabinerRule> =
    snippetReplacementRulesFor(
        keyCodes = listOf(I,J),
        replacement = listOf(I, N, T, E, L, L, I, J).map(::To),
    )