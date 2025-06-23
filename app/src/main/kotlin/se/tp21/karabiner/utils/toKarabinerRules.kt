package se.tp21.karabiner.utils
import se.tp21.karabiner.snippets.rules.SnippetRules
import sh.kau.karabiner.*

fun toKarabinerRules(rules: SnippetRules): List<KarabinerRule> =
    rules.rules.map { rule ->
        snippetRuleFor(
            keyCodes = rule.keys.map { it.toKeyCode() },
            replacement = rule.replacement.map { it.toTo() },
            desc = rule.description
        )
    }

private fun Char.toTo(): To =
    maybeKeyCode()
        ?.let { To(it) }
        ?: this.toOtherTo()

private fun Char.toOtherTo(): To =
    when {
        this == ' ' -> To(KeyCode.Spacebar)
        this == '@' -> To(KeyCode.Num2, modifiers = listOf(ModifierKeyCode.LeftShift))
        this == '+' -> To(KeyCode.EqualSign, modifiers = listOf(ModifierKeyCode.LeftShift))
        this == '.' -> To(KeyCode.Period)
        this in "1234567890" ->
            when(this) {
                '1' -> KeyCode.Num1
                '2' -> KeyCode.Num2
                '3' -> KeyCode.Num3
                '4' -> KeyCode.Num4
                '5' -> KeyCode.Num5
                '6' -> KeyCode.Num6
                '7' -> KeyCode.Num7
                '8' -> KeyCode.Num8
                '9' -> KeyCode.Num9
                '0' -> KeyCode.Num0
                else -> TODO()
            }.let(::To)
        else -> TODO()
    }

private fun Char.toKeyCode(): KeyCode =
    maybeKeyCode()
      ?: error("Unknown KeyCode: ${this}")

private fun Char.maybeKeyCode(): KeyCode? =
    KeyCode::class.sealedSubclasses
        .singleOrNull { it.simpleName.equals(this.toString(), ignoreCase = true) }
        ?.let { it.objectInstance }
