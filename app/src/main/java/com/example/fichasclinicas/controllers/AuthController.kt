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
import com.example.fichasclinicas.lib.RetrofitClient
import com.example.fichasclinicas.models.*
import com.example.fichasclinicas.services.AuthService
import retrofit2.Call
import retrofit2.Callback;
import retrofit2.Response
import java.lang.Exception
import java.util.*

class AuthController constructor(ctx: Context) {
    private val sharedPref = ctx.getSharedPreferences("AGENDA_CLINICA", Context.MODE_PRIVATE)
    private val ctx = ctx
    private val retrofit = RetrofitClient.getRetrofitInstance()
    private val authService = retrofit.create(AuthService::class.java)

    private val dao = Room.databaseBuilder(
        ctx.applicationContext,
        AppDatabase::class.java, "database-name"
        )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
        .userDao()

    fun login(email: String, password: String) {
        val loginPayload = LoginPayloadDTO(email, password)
        val call = authService.login(loginPayload)
        call.enqueue(object : Callback<LoginResponseDTO> {
            override fun onFailure(call: Call<LoginResponseDTO>, t: Throwable) {
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LoginResponseDTO>,
                response: Response<LoginResponseDTO>
            ) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, "Error de conexion", Toast.LENGTH_SHORT).show()
                } else {
                    val bodyResponse = response.body()
                    Toast.makeText(ctx, "Bienvenido ${bodyResponse?.user?.id}", Toast.LENGTH_SHORT)
                        .show()
                    val sharedEdit = sharedPref.edit()
                    sharedEdit.putLong("user_id", bodyResponse?.user?.id!!)
                    sharedEdit.putString("user_jwt", bodyResponse?.jwt!!)
                    sharedEdit.commit()

                    val intent = Intent(ctx, MainActivity::class.java)
                    ctx.startActivity(intent)
                    (ctx as Activity).finish()
                }
            }
        })
    }

    fun getSessionUserId() : Long{
        return sharedPref.getLong("user_id", -1)
    }

    fun Register(user : User){
        val registerPayloadDTO = RegisterPayloadDTO(
            user.email,
            user.email,
            user.clave
        )
        val call =authService.register(registerPayloadDTO)

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                if (response.code() != 200) {
                    Toast.makeText(ctx, "Cuenta Existente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, "Cuenta Registrada", Toast.LENGTH_SHORT).show()
                    val intent = Intent(ctx, MainActivity::class.java)
                    ctx.startActivity(intent)
                    (ctx as Activity).finish()
                }
            }
        })
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
        editor.remove("user_jwt")
        editor.commit()
        val intent = Intent(this.ctx, LoginActivity::class.java)
        this.ctx.startActivity(intent)
        (this.ctx as Activity).finish()
    }
}