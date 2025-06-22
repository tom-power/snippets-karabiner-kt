package se.tp21.karabiner.snippets.base

import se.tp21.karabiner.utils.setLastKeyCodeVariable
import sh.kau.karabiner.*
import kotlin.reflect.KClass

fun lastKey(): List<KarabinerRule> {
    return listOf(
        karabinerRule(
            description = "snippets - last key",
            manipulators = (lastKeyManipulatorsFor(keyCodesFor(KeyCode::class))).toTypedArray()
        )
    )
}

private fun <T: KeyCode> keyCodesFor(kClass: KClass<T>) =
    kClass.sealedSubclasses.mapNotNull { it.objectInstance }

private fun lastKeyManipulatorsFor(keyCodes: List<KeyCode>) =
    keyCodes.map { keyCode ->
        Manipulator(
            from = From(keyCode),
            to = listOf(
                To(
                    keyCode = keyCode,
                ),
                To(
                    setVariable = setLastKeyCodeVariable(keyCode)
                )
            )
        )
    }.toList()