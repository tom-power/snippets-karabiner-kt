package se.tp21.karabiner.utils

import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode
import sh.kau.karabiner.To
import sh.kau.karabiner.karabinerRule
import java.util.*

fun snippetRuleFor(
    keyCodes: List<KeyCode>,
    keyCodesDesc: String = keyCodes.joinToString("") { it.name.lowercase(Locale.getDefault()) },
    replacement: List<To>,
    replacementDesc: String = replacement.joinToString("") { it.keyCode?.name?.lowercase(Locale.getDefault()) ?: "" },
    desc: String? = null,
): KarabinerRule =
    karabinerRule(
        description = desc ?: "snippets - $keyCodesDesc -> $replacementDesc",
        manipulators = replacementManipulators(keyCodes = keyCodes, replacement = replacement).toTypedArray(),
    )