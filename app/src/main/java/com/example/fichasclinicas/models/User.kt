package com.example.fichasclinicas.models

import android.text.Editable
import java.util.*

data class User(
    val id :Long?,
    val nombre: String,
    val apellido: String,
    val birthdate: Date,
    val email: String,
    val clave: String,
)
