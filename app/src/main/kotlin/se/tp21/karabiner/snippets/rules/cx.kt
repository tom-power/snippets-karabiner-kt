package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.utils.snippetReplacementRulesFor
import sh.kau.karabiner.*
import sh.kau.karabiner.KeyCode.*

fun cx(): List<KarabinerRule> =
    snippetReplacementRulesFor(
        keyCodes = cx,
        replacement = chmodx,
        replacementDesc = ::chmodx.name
    )

private val cx = listOf(C, X)
private val plusSign = To(EqualSign, listOf(ModifierKeyCode.LeftShift))
private val chmodx =
    listOf(C, H, M, O, D, Spacebar).map(::To) + plusSign + To(X)
