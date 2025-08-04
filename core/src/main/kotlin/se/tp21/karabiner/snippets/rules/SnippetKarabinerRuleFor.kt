package se.tp21.karabiner.snippets.rules

import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode
import sh.kau.karabiner.To
import sh.kau.karabiner.karabinerRule
import java.util.*

class SnippetKarabinerRuleFor(
    private val keyCodes: List<KeyCode>,
    private val replacement: List<To>,
    private val desc: String? = null,
) : () -> KarabinerRule {
    private val keyCodesDesc: String
        get() =
            keyCodes.joinToString("") { it.name.lowercase(Locale.getDefault()) }
    private val replacementDesc: String
        get() =
            replacement.joinToString("") { it.keyCode?.name?.lowercase(Locale.getDefault()) ?: "" }

    override fun invoke(): KarabinerRule {
        return KarabinerRule(
            description = desc ?: "snippets - $keyCodesDesc -> $replacementDesc",
            manipulators = ReplacementManipulators(keyCodes = keyCodes, replacement = replacement).invoke(),
        )
    }

}