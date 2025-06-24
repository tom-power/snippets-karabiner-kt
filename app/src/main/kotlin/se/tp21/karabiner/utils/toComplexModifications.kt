package se.tp21.karabiner.utils

import se.tp21.karabiner.snippets.base.fallThrough
import se.tp21.karabiner.snippets.rules.SnippetRules
import sh.kau.karabiner.*

fun SnippetRules.toComplexModifications(): ComplexModifications =
    ComplexModifications(
        title = "snippets",
        description = "snippets expanded with space",
        rules = mergeRules(toKarabinerRules(this)) + fallThrough()
    )