package com.example.fichasclinicas.lib

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.fichasclinicas.dao.AgendaDAO
import com.example.fichasclinicas.dao.UserDAO
import com.example.fichasclinicas.models.AgendaEntity
import com.example.fichasclinicas.models.UserEntity
import com.example.fichasclinicas.utils.Converters

@Database(entities = [UserEntity::class, AgendaEntity::class],  version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun agendaDao(): AgendaDAO
    companion object{
        const val DATABASE_NAME = "fichas_clinicas"
    }
}