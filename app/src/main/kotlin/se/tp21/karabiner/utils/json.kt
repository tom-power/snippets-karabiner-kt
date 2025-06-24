package se.tp21.karabiner.utils

import sh.kau.karabiner.json

inline fun <reified T> String.decode(): T {
    return json().decodeFromString<T>(this)
}

inline fun <reified T> T.encode(): String  {
    return json().encodeToString(this)
}