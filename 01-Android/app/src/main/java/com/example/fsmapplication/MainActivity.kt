package com.example.fsmapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida= findViewById<Button>(R.id.btn_ciclo_vida) //Reconozco el Id del componente
        botonCicloVida
            .setOnClickListener{        //Alhacer click ir hacia la actividad ACicloVida
                irActividad(ACicloVida::class.java)
            }

        val botonListView= findViewById<Button>(R.id.btn_ir_list_view) //Reconozco el Id del componente
        botonListView
            .setOnClickListener{        //Alhacer click ir hacia la actividad ListView
                irActividad(BListView::class.java)
            }
    }
    fun irActividad(            //Funcion ir Actividad //Intends
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}