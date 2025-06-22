package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.utils.snippetReplacementRulesFor
import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode.*
import sh.kau.karabiner.To

fun kl(): List<KarabinerRule> =
    snippetReplacementRulesFor(
        keyCodes = listOf(K, L),
        replacement = listOf(K, O, T, L, I, N).map(::To)
    )

