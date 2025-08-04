package se.tp21.karabiner.snippets.base

import se.tp21.karabiner.snippets.utils.VariableKeys.snippetKeys
import se.tp21.karabiner.snippets.utils.setLastKey
import sh.kau.karabiner.*
import kotlin.reflect.KClass

fun fallThrough(): List<KarabinerRule> =
    listOf(
        KarabinerRule(
            description = "snippets - fall through",
            manipulators = fallThroughManipulatorsFor(keyCodesFor(KeyCode::class))
        )
    )

private fun <T : KeyCode> keyCodesFor(kClass: KClass<T>) =
    kClass.sealedSubclasses.mapNotNull { it.objectInstance }

private fun fallThroughManipulatorsFor(keyCodes: List<KeyCode>) =
    keyCodes.map { keyCode ->
        Manipulator(
            from = From(keyCode),
            to =
                unsetVar(snippetKeys) +
                    To(setVariable = setLastKey(keyCode)) +
                    To(keyCode = keyCode)
        )
    }.toList()