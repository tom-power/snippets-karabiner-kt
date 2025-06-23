package se.tp21.karabiner

import se.tp21.karabiner.snippets.base.fallThrough
import se.tp21.karabiner.snippets.rules.SnippetRules
import se.tp21.karabiner.utils.mergeRules
import se.tp21.karabiner.utils.toKarabinerRules
import sh.kau.karabiner.*

fun snippetsWith(rules: SnippetRules): ComplexModifications =
    ComplexModifications(
        title = "snippets",
        description = "snippets expanded with space",
        rules = mergeRules(toKarabinerRules(rules)) + fallThrough()
    )