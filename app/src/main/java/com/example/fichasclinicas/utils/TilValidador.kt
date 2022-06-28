package com.example.fichasclinicas.utils

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TilValidador constructor(til: TextInputLayout) {
    private val formatter = SimpleDateFormat("yyyy-MM-dd")
    private val til: TextInputLayout = til
    private val value: String = til.editText?.text.toString()
    private var required: Boolean = false
    private var invalid: Boolean = false

    private fun setError(invalid: Boolean, message: String) {
        if (invalid) {
            this.invalid = true
            til.error = message
        } else {
            til.error = null
            til.isErrorEnabled = false
        }
    }

    private fun mustValidate(): Boolean {
        return (!this.required && this.value.isNotEmpty() || !invalid)
    }

    fun dateAfter(date: Date): TilValidador {
        if (mustValidate()) {
            var invalidField = false
            try {
                val dateValue = formatter.parse(this.value)
                invalidField = !dateValue.before(date)
            } catch (e: Exception) {
                invalidField = true
            }
            this.setError(invalidField, "La fecha no puede ser posterior a ${formatter.format(date)}")
        }
        return this
    }

    fun required(): TilValidador {
        this.required = true
        val invalidField = this.value.isEmpty()
        this.setError(invalidField, "Campo requerido")
        return this
    }

    fun email(): TilValidador {
        if (mustValidate()) {
            val invalidField = !Patterns.EMAIL_ADDRESS.matcher(this.value).matches()
            this.setError(invalidField, "El valor debe ser un email válido")
        }
        return this
    }

    fun isValid(): Boolean {
        return !this.invalid
    }
}