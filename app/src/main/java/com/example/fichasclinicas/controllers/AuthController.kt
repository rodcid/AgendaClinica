package com.example.fichasclinicas.controllers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.room.Room
import com.example.fichasclinicas.LoginActivity
import com.example.fichasclinicas.MainActivity
import com.example.fichasclinicas.lib.AppDatabase
import com.example.fichasclinicas.lib.BCrypt
import com.example.fichasclinicas.models.User
import com.example.fichasclinicas.models.UserEntity
import java.lang.Exception
import java.util.*

class AuthController constructor(ctx: Context) {
    private val sharedPref = ctx.getSharedPreferences("AGENDA_CLINICA", Context.MODE_PRIVATE)
    private val ctx = ctx
    private val dao = Room.databaseBuilder(
        ctx.applicationContext,
        AppDatabase::class.java, "database-name"
        )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .userDao()

    fun login(email: String, password: String) {
        val user = dao.findByEmail(email)
        if(user == null){
            Toast.makeText(this.ctx, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            if (BCrypt.checkpw(password,user.clave)) {
                val sharedEdit = sharedPref.edit()
                sharedEdit.putLong("user_id",user.id!!)
                sharedEdit.commit()


                Toast.makeText(this.ctx, "Bienvenido ${user.nombre}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this.ctx, MainActivity::class.java)
                this.ctx.startActivity(intent)
            } else {
                Toast.makeText(this.ctx, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }catch (e:Exception){
            Toast.makeText(this.ctx, "Error general", Toast.LENGTH_SHORT).show()
        }
    }

    fun Register(user : User){
        val hashedPassword = BCrypt.hashpw(user.clave, BCrypt.gensalt())
        val userEntity = UserEntity(
            id = null,
            nombre = user.nombre,
            apellido = user.apellido,
            birthdate = user.birthdate,
            email = user.email,
            clave = hashedPassword
        )



        try {
            dao.insert(userEntity)
            Toast.makeText(this.ctx,"Cuenta ${user.email} registrada", Toast.LENGTH_LONG).show()
            val intent = Intent(this.ctx, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            this.ctx.startActivity(intent)
        }catch (e: Exception){
            Toast.makeText(this.ctx,"cuenta existente", Toast.LENGTH_SHORT).show()
        }

    }

    fun checkUserSession() {
        val id = sharedPref.getLong("user_id", -1)

        Handler().postDelayed({
            if (id == (-1).toLong()) {
                val intent = Intent(this.ctx, LoginActivity::class.java)
                this.ctx.startActivity(intent)
            } else {
                val intent = Intent(this.ctx, MainActivity::class.java)
                this.ctx.startActivity(intent)
            }
            (this.ctx as Activity).finish()
        }, 2000)
    }

    fun clearSession() {
        val editor = sharedPref.edit()
        editor.remove("user_id")
        editor.commit()
        val intent = Intent(this.ctx, LoginActivity::class.java)
        this.ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }
}