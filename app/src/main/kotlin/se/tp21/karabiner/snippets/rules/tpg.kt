package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.utils.snippetReplacementRulesFor
import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode.*
import sh.kau.karabiner.ModifierKeyCode
import sh.kau.karabiner.To

fun tpg(): List<KarabinerRule> =
    snippetReplacementRulesFor(
        keyCodes = tpg,
        replacement = tpower21,
        replacementDesc = ::tpower21.name
    )

private val tpg = listOf(T, P, G)

private val atSign = To(Num2, listOf(ModifierKeyCode.LeftShift))
private val tpower21 =
    listOf(T, P, O, W, E, R, Num2, Num1).map(::To) +
        atSign +
        listOf(G, M, A, I, L, Period, C, O, M).map(::To)