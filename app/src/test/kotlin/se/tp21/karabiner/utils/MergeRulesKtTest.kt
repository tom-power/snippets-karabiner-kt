package se.tp21.karabiner.utils

import kotlinx.serialization.json.JsonPrimitive
import sh.kau.karabiner.*
import sh.kau.karabiner.Condition.VariableIfCondition
import sh.kau.karabiner.Condition.VariableUnlessCondition
import kotlin.test.Test
import kotlin.test.assertEquals

class MergeRulesKtTest {
    private val ab = listOf(KeyCode.A, KeyCode.B)
    private val abRule = KarabinerRule("ab", replacementManipulators(ab, ab.map(::To)))

    private val bc = listOf(KeyCode.B, KeyCode.C)
    private val bcRule = KarabinerRule("bc", replacementManipulators(bc, bc.map(::To)))

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
                    To(keyCode = KeyCode.A),
                    To(setVariable = SetVariable(name = "ab", value = JsonPrimitive("a"))),
                    To(setVariable = SetVariable(name = "lastKeyCode", value = JsonPrimitive("a")))
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
                    To(keyCode = KeyCode.B),
                    To(setVariable = SetVariable(name = "ab", value = JsonPrimitive("ab"))),
                    To(setVariable = SetVariable(name = "lastKeyCode", value = JsonPrimitive("b")))
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableIfCondition(name = "lastKeyCode", value = JsonPrimitive("a")),
                    VariableIfCondition(name = "ab", value = JsonPrimitive("a")),
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
                    To(setVariable = SetVariable(name = "ab", value = JsonPrimitive(0))),
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
                    VariableIfCondition(name = "lastKeyCode", value = JsonPrimitive("b")),
                    VariableIfCondition(name = "ab", value = JsonPrimitive("ab")),
                    VariableUnlessCondition(name = "lastKeyCode", value = JsonPrimitive("c")),
                    VariableUnlessCondition(name = "bc", value = JsonPrimitive("bc"))
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
                    To(keyCode = KeyCode.B),
                    To(setVariable = SetVariable(name = "bc", value = JsonPrimitive("b"))),
                    To(setVariable = SetVariable(name = "lastKeyCode", value = JsonPrimitive("b")))
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableUnlessCondition(name = "lastKeyCode", value = JsonPrimitive("a")),
                    VariableUnlessCondition(name = "ab", value = JsonPrimitive("a"))
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
                    To(keyCode = KeyCode.C),
                    To(setVariable = SetVariable(name = "bc", value = JsonPrimitive("bc"))),
                    To(setVariable = SetVariable(name = "lastKeyCode", value = JsonPrimitive("c")))
                ),
                toIfAlone = null,
                toAfterKeyUp = null,
                toIfHeldDown = null,
                parameters = null,
                conditions = listOf(
                    VariableIfCondition(name = "lastKeyCode", value = JsonPrimitive("b")),
                    VariableIfCondition(name = "bc", value = JsonPrimitive("b"))
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
                    To(setVariable = SetVariable(name = "bc", value = JsonPrimitive(0))),
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
                    VariableIfCondition(name = "lastKeyCode", value = JsonPrimitive("c")),
                    VariableIfCondition(name = "bc", value = JsonPrimitive("bc")),
                    VariableUnlessCondition(name = "lastKeyCode", value = JsonPrimitive("b")),
                    VariableUnlessCondition(name = "ab", value = JsonPrimitive("ab")),
                ),
                description = "bc",
            )
        )
    )
)