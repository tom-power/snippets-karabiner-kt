package se.tp21.karabiner

import se.tp21.karabiner.snippets.base.lastKey
import sh.kau.karabiner.ComplexModifications

fun snippets() =
    ComplexModifications(
        title = "snippets",
        description = "snippets expanded with space",
        rules = snippetRules + lastKey()
    )
