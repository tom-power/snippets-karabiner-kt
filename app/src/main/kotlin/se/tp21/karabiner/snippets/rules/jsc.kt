package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.utils.snippetReplacementRulesFor
import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode.*
import sh.kau.karabiner.ModifierKeyCode
import sh.kau.karabiner.To

fun jsc(): List<KarabinerRule> =
    snippetReplacementRulesFor(
        keyCodes = jsc,
        replacement = javascript,
        replacementDesc = ::javascript.name
    )

private val jsc = listOf(T, P, G)

private val javascript =
    listOf(J, A, V, A, S, C, R, I, P, T).map(::To)