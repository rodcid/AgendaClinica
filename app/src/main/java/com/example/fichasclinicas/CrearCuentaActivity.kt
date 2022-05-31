package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CrearCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_cuenta_activity)

        val btnCrearCuenta = findViewById<Button>(R.id.activity_crearcuenta_btn_registrar)
        val btnCancelar = findViewById<Button>(R.id.activity_crearcuenta_btn_cancelar)

        btnCancelar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, ConfirmarCrearCuentaActivity::class.java)
            startActivity(intent)
        }
    }
}