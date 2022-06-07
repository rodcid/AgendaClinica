package com.example.fichasclinicas.controllers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.fichasclinicas.MainActivity

class AuthController constructor(ctx: Context) {
    private val ctx = ctx

    fun login(email: String, password: String) {
        if (email == "ex@a.a" && password == "123") {
            Toast.makeText(this.ctx, "Bienvenido", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.ctx, MainActivity::class.java)
            this.ctx.startActivity(intent)
        } else {
            Toast.makeText(this.ctx, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}