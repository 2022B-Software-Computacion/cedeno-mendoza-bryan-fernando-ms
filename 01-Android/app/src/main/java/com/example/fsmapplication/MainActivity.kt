package com.example.fsmapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    //Ir a una actividad  con intent Implicito
    val contenidoIntentImplicito = registerForActivityResult( //Generacion de Callback
        ActivityResultContracts.StartActivityForResult()
    ){  //Reultado de lo que buscamos
            result ->
        if(result.resultCode == RESULT_OK){
            if(result.data != null){ //revisamos que resultado no sea nulo
                if(result.data!!.data !=null){  //revisamos que resultado. data no sea nulo
                                                //los !! es cuando sabemos que ya no es nulo
                    val uri: Uri = result.data!!.data!!   //obtenemos una url
                    val cursor = contentResolver.query(     // Creamos el cursor
                        uri, null, null, null, null, null
                    )
                    cursor?.moveToFirst()             //movemos el cursor a primera posicion
                    val indiceTelefono = cursor?.getColumnIndex(       //traemos la pos donde esta el cursor
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(       //telefono tomara el valor de el idice del telefono del cursor
                        indiceTelefono!!
                    )
                    cursor?.close() //cerramos el cursor
                    Log.i("intent-epn","Telefono ${telefono}")         //imprimimos el telefono
                }
            }
        }
    }

    val contenidoIntentExplicito =   //Es funcion se utiliza cuando necesitamos recibir datos de una actividad
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    val data = result.data //Logica con los datos que recibimos
                    Log.i("intent-epn","${data?.getStringExtra("nombre Modificado")}")
                    //la variable que estamos buscando es nombremodificado
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Base de Datos SQlite
        EBaseDeDatos.tablaEntrenador= ESqliteHelperEntrenador(this)



        //IR a Ciclo Vida
        val botonCicloVida =
            findViewById<Button>(R.id.btn_ciclo_vida) //Reconozco el Id del componente
        botonCicloVida
            .setOnClickListener {        //Alhacer click ir hacia la actividad ACicloVida
                irActividad(ACicloVida::class.java)
            }
        //Ir a List View
        val botonListView =
            findViewById<Button>(R.id.btn_ir_list_view) //Reconozco el Id del componente
        botonListView
            .setOnClickListener {        //Alhacer click ir hacia la actividad ListView
                irActividad(BListView::class.java)
            }
        //Intent Implicito
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,     // Creamos Intent respuesta, accion que estamos realizando
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI  // Para solocitar un recurso(Uri)
                )
                contenidoIntentImplicito.launch(intentConRespuesta) //enviamos el intent.
            }
        //Intent explicita con parametros
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

        //Boton ir Metodos Crud


        val botonSqlite=findViewById<Button>(R.id.btn_ir_crud)
        botonSqlite
            .setOnClickListener {
                irActividad(ECrudEntrenador::class.java)
            }

    }
    fun irActividad(            //Funcion ir Actividad //Intends
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase:Class<*>,
    ){//Para enviar parametros a un intent lo realizamos mediante el metodo put extra de ese intent
        val intentExplicito = Intent(this, clase)
        //enviar parámetros ((solamente variables primitivas) a nuestra actividad
        intentExplicito.putExtra("nombre", "Fernando")
        intentExplicito.putExtra("apellido","Cedeño")
        intentExplicito.putExtra("edad",25)
        //con el entrenador Parcelable podemos enviar datos de actividadesy no solo primitivas
        intentExplicito.putExtra("entrenadorPrincipal", BEntrenador(5,"jorge","Paleta"))
        contenidoIntentExplicito.launch(intentExplicito)
    }
}