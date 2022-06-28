package com.example.fichasclinicas.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val id :Long?, //se autoincrementa el id.
    @ColumnInfo(name = "Nombre")val nombre: String,
    @ColumnInfo(name = "Apellido" )val apellido: String,
    val birthdate: Date,
    val email: String,
    val clave: String,
)