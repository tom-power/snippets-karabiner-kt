package se.tp21.karabiner.snippets

import se.tp21.karabiner.snippets.base.fallThrough
import se.tp21.karabiner.snippets.rules.mergeRules
import se.tp21.karabiner.snippets.rules.toKarabinerRules
import sh.kau.karabiner.*

fun complexModificationsFrom(snippetRules: SnippetRules): ComplexModifications =
    ComplexModifications(
        title = "snippets",
        description = "snippets expanded with space",
        rules = mergeRules(toKarabinerRules(snippetRules)) + fallThrough()
    )