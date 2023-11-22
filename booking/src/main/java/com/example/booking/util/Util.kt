package com.example.booking.util

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import com.example.resources.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val PHONE_MASK = "+ 7 ([000]) [000]-[00]-[00]"
const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
const val ERROR_TEXT = "Error occurred: "

val russianOrdinalNumber = listOf(
    "Первый",
    "Второй",
    "Третий",
    "Четвертый",
    "Пятый",
    "Шестой",
    "Седьмой",
    "Восьмой",
    "Девятый",
    "Десятый",
    "Одиннадцатый",
    "Двенадцатый",
    "Тринадцатый",
    "Четырнадцатый",
    "Пятнадцатый",
    "Шестнадцатый",
    "Семнадцатый",
    "Восемнадцатый",
    "Девятнадцатый",
    "Двадцатый",
    "Двадцать первый",
    "Двадцать второй",
    "Двадцать третий",
    "Двадцать четвертый",
    "Двадцать пятый",
    "Двадцать шестой",
    "Двадцать седьмой",
    "Двадцать восьмой",
    "Двадцать девятый",
    "Тридцатый"
)

fun setupEditText(
    context: Context,
    editText: TextInputEditText,
    textInputLayout: TextInputLayout
): Boolean {
    var isFilled: Boolean
    val errorBackgroundColor = ContextCompat.getColor(context, R.color.error)
    val backgroundColor = ContextCompat.getColor(context, R.color.boxBackgroundColor)
    if (editText.text?.trim()?.isNotEmpty() == true) {
        isFilled = true
        textInputLayout.error = null
        editText.backgroundTintList = ColorStateList.valueOf(backgroundColor)
    } else {
        isFilled = false
        textInputLayout.error = "Заполните поле"
        editText.backgroundTintList = ColorStateList.valueOf(errorBackgroundColor)
    }

    editText.addTextChangedListener(
        object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.trim()?.isNotEmpty() == true) {
                    isFilled = true
                    textInputLayout.error = null
                    editText.backgroundTintList =
                        ColorStateList.valueOf(backgroundColor)
                } else {
                    isFilled = false
                    textInputLayout.error = "Заполните поле"
                    editText.backgroundTintList =
                        ColorStateList.valueOf(errorBackgroundColor)
                }
            }
        }
    )
    return isFilled
}