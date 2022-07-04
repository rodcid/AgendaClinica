package com.example.fichasclinicas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fichasclinicas.models.AgendaEntity


@Dao
interface AgendaDAO {

    //Query para mostrar la agenda segun el id del usuario
    @Query("SELECT * FROM agenda WHERE user_id = :userId")
    fun findAll (userId: Long?): List<AgendaEntity>

    //Query para buscar por id
    @Query("SELECT * FROM agenda WHERE id =:id")
    fun findById(id :Long) : AgendaEntity?

    //Query para agendar un nuevo procedimiento.
    @Insert
    fun insert(agenda: AgendaEntity)


    @Update
    fun update(agenda: AgendaEntity)

    // Para borrar procedimiento segun id
    @Query("DELETE FROM agenda WHERE id = :id")
    fun delete(id:Long)


}
