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
        ?.let(::To)
        ?: this.toOtherTo()
        ?: error("Unknown to: $this")

private fun Char.toOtherTo(): To? =
    when (this) {
        ' ' -> To(KeyCode.Spacebar)
        '@' -> To(KeyCode.Num2, modifiers = listOf(ModifierKeyCode.LeftShift))
        '+' -> To(KeyCode.EqualSign, modifiers = listOf(ModifierKeyCode.LeftShift))
        '.' -> To(KeyCode.Period)
        '1' -> To(KeyCode.Num1)
        '2' -> To(KeyCode.Num2)
        '3' -> To(KeyCode.Num3)
        '4' -> To(KeyCode.Num4)
        '5' -> To(KeyCode.Num5)
        '6' -> To(KeyCode.Num6)
        '7' -> To(KeyCode.Num7)
        '8' -> To(KeyCode.Num8)
        '9' -> To(KeyCode.Num9)
        '0' -> To(KeyCode.Num0)
        else -> null
    }

private fun Char.toKeyCode(): KeyCode =
    maybeKeyCode()
        ?: error("Unknown KeyCode: $this")

private fun Char.maybeKeyCode(): KeyCode? =
    KeyCode::class.sealedSubclasses
        .singleOrNull { it.simpleName.equals(this.toString(), ignoreCase = true) }
        ?.objectInstance
