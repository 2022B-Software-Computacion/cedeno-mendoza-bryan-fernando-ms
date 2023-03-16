package com.example.fsmapplication

import android.app.DownloadManager.Query
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.vaesapplication.JCitiesDto
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JFirebaseFirestore : AppCompatActivity() {
    var query: Query?=null // consulta desde firestore
    val arreglo: ArrayList<JCitiesDto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jfirebase_firestore)
        val listView = findViewById<ListView>(R.id.lv_firestore) // declaramos el list view
        val adaptador: ArrayAdapter<JCitiesDto> = ArrayAdapter( //creamos un adaptador
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador //le ponemos el adaptador als list view
        adaptador.notifyDataSetChanged() //guardamos cambios

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba) //traemos el boton de datos de prueba
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }
    }

    //Funciones de utilidad
    fun crearDatosPrueba(){
        val db = Firebase.  firestore // Objeto Firestore
        val cities = db.collection("cities") // nombre coleccion
        val data1 = hashMapOf( // Objeto a guardarse
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities
            .document("SF") // Asigna el ID = "SF"
            .set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }
    fun limpiarArreglo() { arreglo.clear() }
    fun anadirAArregloCiudad(
        arregloNuevo: ArrayList<JCitiesDto>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<JCitiesDto>
    ) {
        ciudad.id
        val nuevaCiudad = JCitiesDto(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?,
            ciudad.data.get("regions") as ArrayList<String>
        )
        arregloNuevo.add(
            nuevaCiudad
        )
        adaptador.notifyDataSetChanged()
    }

}