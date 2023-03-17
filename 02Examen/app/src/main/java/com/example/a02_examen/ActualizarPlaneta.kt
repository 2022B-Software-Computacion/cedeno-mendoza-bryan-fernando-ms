package com.example.a02_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarPlaneta : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_planeta)

        val bundle = intent.extras
        val idPlaneta = bundle?.getString("id")
        var docRef = idPlaneta?.let { db.collection("Planeta").document(it) }

        val etNombrePlaneta = findViewById<EditText>(R.id.etNombrePlanetaUpdate)
        val etFechaDesPlaneta  = findViewById<EditText>(R.id.etFechaDesPlanetaUpdate)
        val etSuperficiePlaneta  = findViewById<EditText>(R.id.etSuperficiePlanetaUpdate)
        val etEdadPlaneta  = findViewById<EditText>(R.id.etEdadPlanetaUpdate)
        val etHabitablePlaneta  = findViewById<EditText>(R.id.etHabitablePlanetaUpdate)
        val btnActualizaPlaneta = findViewById<Button>(R.id.btnActualizarPlaneta)

        if (docRef != null) {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Access the DocumentSnapshot data here.
                    val nombre = documentSnapshot.getString("nombre")

                    val superficieAux = documentSnapshot.getDouble("superficie")
                    val superficie = superficieAux.toString()

                    val habitableAux = documentSnapshot.getBoolean("habitable")
                    val habitable = habitableAux.toString()

                    val fechaDescubrimiento = documentSnapshot.getString("fechaDescubrimiento")

                    val edadAux = documentSnapshot.get("edad")
                    val edad = edadAux.toString()




                    etNombrePlaneta.setText(nombre)
                    etFechaDesPlaneta.setText(fechaDescubrimiento)
                    etSuperficiePlaneta.setText(superficie)
                    etEdadPlaneta.setText(edad)
                    etHabitablePlaneta.setText(habitable)
                }
            }
        }

        btnActualizaPlaneta.setOnClickListener {

            if (etNombrePlaneta.text.isNotEmpty()
                && etFechaDesPlaneta.text.isNotEmpty()
                && etSuperficiePlaneta.text.isNotEmpty()
                && etEdadPlaneta.text.isNotEmpty()
                && etHabitablePlaneta.text.isNotEmpty()
            ){

                val transformEdad= etEdadPlaneta.text.toString()
                val resultEdad = transformEdad.toInt()
                val transformHabitablePlaneta = etHabitablePlaneta.text.toString()
                val resultHabitablePlaneta = transformHabitablePlaneta.toBoolean()
                val transformSuperficie = etSuperficiePlaneta.text.toString()
                val resultSuperficie = transformSuperficie.toDouble()


                val data = hashMapOf(
                    "nombre" to etNombrePlaneta.text.toString(),
                    "superficie" to resultSuperficie,
                    "habitable" to resultHabitablePlaneta,
                    "fechaDescubrimiento" to etFechaDesPlaneta.text.toString(),
                    "edad" to resultEdad
                )


                if (idPlaneta != null) {

                    db.collection("Planeta").document(idPlaneta).update(data as Map<String, Any>).
                    addOnSuccessListener {
                        Toast.makeText(this, "Actualizacion Exitosa de Planeta", Toast.LENGTH_SHORT).show()
                        goActivity(PlanetasPrincipal::class.java)
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error al actualizar Planeta", Toast.LENGTH_SHORT).show()
                        goActivity(PlanetasPrincipal::class.java)
                    }
                }
            }
        }

        
        
    }
    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}