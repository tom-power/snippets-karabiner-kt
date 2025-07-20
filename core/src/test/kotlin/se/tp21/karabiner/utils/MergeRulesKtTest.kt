package se.tp21.karabiner.utils

import kotlinx.serialization.json.JsonPrimitive
import se.tp21.karabiner.snippets.rules.ReplacementManipulators
import se.tp21.karabiner.snippets.rules.mergeRules
import sh.kau.karabiner.*
import sh.kau.karabiner.Condition.VariableIfCondition
import sh.kau.karabiner.Condition.VariableUnlessCondition
import kotlin.test.Test
import kotlin.test.assertEquals

class MergeRulesKtTest {
    private val ab = listOf(KeyCode.A, KeyCode.B)
    private val abRule = KarabinerRule("ab", ReplacementManipulators(ab, ab.map(::To)).invoke())

    private val bc = listOf(KeyCode.B, KeyCode.C)
    private val bcRule = KarabinerRule("bc", ReplacementManipulators(bc, bc.map(::To)).invoke())

    @Test
    fun `merges duplicates replacement manipulators in rules`() {
        assertEquals(
            expected = expectedMergedRules,
            actual = mergeRules(listOf(abRule, bcRule))
        )
    }

}

val expectedMergedRules = listOf(
    KarabinerRule(
        description = "ab",
        manipulators = listOf(
            Manipulator(
                type = "basic",
                from = From(
                    keyCode = KeyCode.A,
                    modifiers = null,
                    simultaneous = null,
                    simultaneousOptions = null
                ),
                to = listOf(
                    To(setVariable = SetVariable(name = "snippetKeys", value = JsonPrimitive("a"))),
                    To(setVariable = SetVariable(name = "lastKey", value = JsonPrimitive("a"))),
                    To(keyCode = KeyCode.A),
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = null,
                description = "ab",
            ),
            Manipulator(
                type = "basic",
                from = From(
                    keyCode = KeyCode.B,
                    modifiers = null,
                    simultaneous = null,
                    simultaneousOptions = null
                ),
                to = listOf(
                    To(setVariable = SetVariable(name = "snippetKeys", value = JsonPrimitive("ab"))),
                    To(setVariable = SetVariable(name = "lastKey", value = JsonPrimitive("b"))),
                    To(keyCode = KeyCode.B),
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableIfCondition(name = "lastKey", value = JsonPrimitive("a")),
                    VariableIfCondition(name = "snippetKeys", value = JsonPrimitive("a")),
                ),
                description = "ab",
            ),
            Manipulator(
                type = "basic",
                from = From(
                    keyCode = KeyCode.Spacebar,
                    modifiers = null,
                    simultaneous = null,
                    simultaneousOptions = null
                ),
                to = listOf(
                    To(setVariable = SetVariable(name = "snippetKeys", value = JsonPrimitive(0))),
                    To(keyCode = KeyCode.DeleteOrBackspace),
                    To(keyCode = KeyCode.DeleteOrBackspace),
                    To(keyCode = KeyCode.A),
                    To(keyCode = KeyCode.B)
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableIfCondition(name = "lastKey", value = JsonPrimitive("b")),
                    VariableIfCondition(name = "snippetKeys", value = JsonPrimitive("ab")),
                ),
                description = "ab",
            )
        )
    ),
    KarabinerRule(
        description = "bc",
        manipulators = listOf(
            Manipulator(
                type = "basic",
                from = From(
                    keyCode = KeyCode.B,
                    modifiers = null,
                    simultaneous = null,
                    simultaneousOptions = null
                ),
                to = listOf(
                    To(setVariable = SetVariable(name = "snippetKeys", value = JsonPrimitive("b"))),
                    To(setVariable = SetVariable(name = "lastKey", value = JsonPrimitive("b"))),
                    To(keyCode = KeyCode.B),
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableUnlessCondition(name = "lastKey", value = JsonPrimitive("a")),
                    VariableUnlessCondition(name = "snippetKeys", value = JsonPrimitive("a"))
                ),
                description = "bc",
            ),
            Manipulator(
                type = "basic",
                from = From(
                    keyCode = KeyCode.C,
                    modifiers = null,
                    simultaneous = null,
                    simultaneousOptions = null
                ),
                to = listOf(
                    To(setVariable = SetVariable(name = "snippetKeys", value = JsonPrimitive("bc"))),
                    To(setVariable = SetVariable(name = "lastKey", value = JsonPrimitive("c"))),
                    To(keyCode = KeyCode.C),
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableIfCondition(name = "lastKey", value = JsonPrimitive("b")),
                    VariableIfCondition(name = "snippetKeys", value = JsonPrimitive("b"))
                ),
                description = "bc",
            ),
            Manipulator(
                type = "basic",
                from = From(
                    keyCode = KeyCode.Spacebar,
                    modifiers = null,
                    simultaneous = null,
                    simultaneousOptions = null
                ),
                to = listOf(
                    To(setVariable = SetVariable(name = "snippetKeys", value = JsonPrimitive(0))),
                    To(keyCode = KeyCode.DeleteOrBackspace),
                    To(keyCode = KeyCode.DeleteOrBackspace),
                    To(keyCode = KeyCode.B),
                    To(keyCode = KeyCode.C)
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableIfCondition(name = "lastKey", value = JsonPrimitive("c")),
                    VariableIfCondition(name = "snippetKeys", value = JsonPrimitive("bc")),
                ),
                description = "bc",
            )
        )
    )
)