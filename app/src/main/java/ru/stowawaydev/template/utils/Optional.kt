package ru.stowawaydev.template.utils

import java.util.*

/**
 * template header (replace it)
 */

class Optional<T> {

    companion object {

        private val EMPTY = Optional<Any>()

        fun <T> empty(): Optional<T> {
            return EMPTY as Optional<T>
        }

        fun <T> of(value: T?): Optional<T> {
            return if (value == null) empty() else Optional(value)
        }
    }

    private constructor() {
        this.orNull = null
    }

    private constructor(value: T) {
        this.orNull = Objects.requireNonNull(value)
    }

    val orNull: T?

    val isPresent: Boolean
        get() = orNull != null

    val isNotPresent: Boolean
        get() = !isPresent

    fun getOrDefault(defValue: T): T {
        return orNull ?: defValue
    }

    fun get(): T {
        if (orNull == null) {
            throw NoSuchElementException("No value present")
        }
        return orNull
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        } else if (obj !is Optional<*>) {
            return false
        }
        val other = obj as Optional<*>?
        return orNull == other?.orNull
    }

    override fun hashCode(): Int {
        return orNull.hashCode()
    }

    override fun toString(): String {
        return if (orNull != null) {
            String.format("Optional[%s]", orNull)
        } else {
            "Optional.empty"
        }
    }
}
