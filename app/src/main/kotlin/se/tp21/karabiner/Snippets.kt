package se.tp21.karabiner

import se.tp21.karabiner.snippets.base.lastKey
import se.tp21.karabiner.utils.mergeRules
import sh.kau.karabiner.ComplexModifications

fun snippets() =
    ComplexModifications(
        title = "snippets",
        description = "snippets expanded with space",
        rules = mergeRules(snippetRules) + lastKey()
    )
