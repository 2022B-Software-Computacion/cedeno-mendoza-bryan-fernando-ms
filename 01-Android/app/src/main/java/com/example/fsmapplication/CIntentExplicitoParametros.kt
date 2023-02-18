package com.example.fsmapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
    //Dentro del AppComparative tenemos un intent y por ytanto lo usaremos
        //aqui obtenemos los valores de la variables que definimos en la main activity
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad",0)
        //Tambien se puede enviar un arreglo de primitivas pero hay que modificar el metodo
        val entrenador = intent.getParcelableExtra<BEntrenador>(
            "entrenadorPncipal"
        )

        val boton = findViewById<Button>(R.id.btn_devolver_respuesta) //devolvemos una respuesta
        boton.setOnClickListener{devolverRespuesta()}

    }
    fun devolverRespuesta(){  //Los intends son como se comunica android
        val intentDevolverParametros = Intent()  //Crearemos el intent de devolucion
        intentDevolverParametros.putExtra("nombreModificado", "Raul")
        intentDevolverParametros.putExtra("edadModificado",30)
        setResult(      //ponemos  el codigo de resultado y el intent o parametros
            RESULT_OK,
            intentDevolverParametros
        )
        finish()//cerramos la actividad actual y regresa a la que la llamo
    }



}