package com.example.a02_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearPlaneta : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_planeta)
        
        val etNombrePlaneta = findViewById<EditText>(R.id.etNombrePlaneta)
        val etFechaDesPlaneta  = findViewById<EditText>(R.id.etFechaDesPlaneta)
        val etSuperficiePlaneta  = findViewById<EditText>(R.id.etSuperficiePlaneta)
        val etEdadPlaneta  = findViewById<EditText>(R.id.etEdadPlaneta)
        val etHabitablePlaneta  = findViewById<EditText>(R.id.etHabitablePlaneta)
        val btnSavePlaneta = findViewById<Button>(R.id.btnCrearPlaneta)

        btnSavePlaneta.setOnClickListener {
            if (etNombrePlaneta.text.isNotEmpty()
                && etFechaDesPlaneta.text.isNotEmpty()
                && etSuperficiePlaneta.text.isNotEmpty()
                && etEdadPlaneta.text.isNotEmpty()
                && etHabitablePlaneta.text.isNotEmpty()
            ){
                val transformHabitablePlaneta = etHabitablePlaneta.text.toString()
                val resultHabitablePlaneta = transformHabitablePlaneta.toBoolean()

                val transformSuperficiePlaneta = etSuperficiePlaneta.text.toString()
                val resultSuperficiePlaneta = transformSuperficiePlaneta.toDouble()

                val transformEdadPlaneta = etEdadPlaneta.text.toString()
                val resultEdadPlaneta = transformEdadPlaneta.toInt()

                val data = hashMapOf(
                    "nombre" to etNombrePlaneta.text.toString(),
                    "superficie" to resultSuperficiePlaneta,
                    "habitable" to resultHabitablePlaneta,
                    "fechaDescubrimiento" to etFechaDesPlaneta.text.toString(),
                    "edad" to resultEdadPlaneta
                )

                db.collection("Planeta").add(data).
                addOnSuccessListener {
                    Toast.makeText(this, "Creacion Exitosa de Planeta", Toast.LENGTH_SHORT).show()
                    goActivity(PlanetasPrincipal::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al crear Planeta", Toast.LENGTH_SHORT).show()
                    goActivity(PlanetasPrincipal::class.java)
                }
            }
        }
    }
    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}