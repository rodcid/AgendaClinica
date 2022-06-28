package com.example.fichasclinicas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fichasclinicas.models.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM users WHERE users.email = :email LIMIT 1") //query para buscar por email
    fun findByEmail (email:String): UserEntity?                       //Se crea la funcion

    @Insert
    fun insert(user: UserEntity)
}