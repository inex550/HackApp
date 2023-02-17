package ru.faaen.hackapp.core.ui.utils.textwatchers

import android.text.Editable
import android.text.TextWatcher

class PhoneNumberTextWatcher(
    private val mask: String = DEFAULT_MASK
): TextWatcher {

    var phoneNumber: String = ""
        private set

    var editing: Boolean = false

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (editing) return

        val deleted = before > count

        if (deleted) {
            phoneNumber = phoneNumber.dropLast(1)
        } else if (phoneNumber.length < MAX_PHONE_NUMBER_LENGTH && start < s.length) {
            addSymbol(s[start])
        }
    }

    override fun afterTextChanged(e: Editable) {
        if (editing) return

        editing = true

        e.clear()
        e.append(getMaskedNumber())

        editing = false
    }

    private fun getMaskedNumber(): String {
        val numberBuilder = StringBuilder()

        var phoneIdx = 0
        var maskIdx = 0

        while (phoneIdx < phoneNumber.length) {
            if (mask[maskIdx] == CODE_MASK) {
                numberBuilder.append('+', phoneNumber[phoneIdx])
                maskIdx += 1
            } else if (mask[maskIdx] == NUM_MASK) {
                numberBuilder.append(phoneNumber[phoneIdx])
                maskIdx += 1
            }

            while (maskIdx < mask.length) {
                if (mask[maskIdx] != CODE_MASK && mask[maskIdx] != NUM_MASK)
                    numberBuilder.append(mask[maskIdx])
                else break
                maskIdx += 1
            }

            phoneIdx += 1
        }

        return numberBuilder.toString()
    }

    private fun addSymbol(symbol: Char) {
        if (symbol.isDigit()) {
            phoneNumber += if (phoneNumber.isEmpty() && symbol == '8') '7' else symbol
        }
    }

    fun clearNumber() {
        phoneNumber = ""
    }

    companion object {
        const val MAX_PHONE_NUMBER_LENGTH = 11

        const val CODE_MASK = 'X'
        const val NUM_MASK = '_'

        const val DEFAULT_MASK = "X (___) ___ __-__"
    }
}