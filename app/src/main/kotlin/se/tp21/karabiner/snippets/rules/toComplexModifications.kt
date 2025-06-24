package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.snippets.base.fallThrough
import sh.kau.karabiner.*

fun SnippetRules.toComplexModifications(): ComplexModifications =
    ComplexModifications(
        title = "snippets",
        description = "snippets expanded with space",
        rules = mergeRules(toKarabinerRules(this)) + fallThrough()
    )