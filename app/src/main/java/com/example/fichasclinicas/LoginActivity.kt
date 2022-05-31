package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val btnRegistrar = findViewById<Button>(R.id.activity_login_btn_crearcuenta)
        val btnIngresar = findViewById<Button>(R.id.activity_login_btn_iniciarsesion)

        btnRegistrar.setOnClickListener{
            val intent = Intent(this , CrearCuentaActivity::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}