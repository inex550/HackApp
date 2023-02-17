package ru.faaen.hackapp.core.common.ext


typealias SpecError = Map<String, String>

typealias EditableSpecError = HashMap<String, String>

fun EditableSpecError.setError(field: String, error: String?): Boolean {
    if (error != null) {
        set(field, error)
    } else {
        remove(field)
    }
    return error != null
}

fun EditableSpecError.hasErrors() = isNotEmpty()

fun emptySpec(): SpecError = emptyMap()