package com.example.fichasclinicas.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fichasclinicas.models.AgendaEntity


@Dao
interface AgendaDAO {
    @Query("SELECT * FROM agenda")
    fun findAll (): List<AgendaEntity>

    @Insert
    fun insert(agenda: AgendaEntity)

    @Update
    fun update(agenda: AgendaEntity)

    @Query("DELETE FROM agenda WHERE id = :id")
    fun delete(id:Long)


}
