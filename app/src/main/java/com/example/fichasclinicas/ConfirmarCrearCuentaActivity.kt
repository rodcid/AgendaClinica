package com.example.fichasclinicas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConfirmarCrearCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmar_crear_cuenta_activity)

        val btnConfirmar = findViewById<Button>(R.id.activity_confirmarcrearcuenta_btn_registrar)
        val btnCancelar = findViewById<Button>(R.id.activity_confirmarcrearcuenta_btn_cancelar)

        btnConfirmar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnCancelar.setOnClickListener{
            val intent = Intent(this, CrearCuentaActivity::class.java)
            startActivity(intent)
        }

    }
}