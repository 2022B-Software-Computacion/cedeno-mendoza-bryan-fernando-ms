package com.example.a02_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearSistema : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sistema)

        val etNombreSistema = findViewById<EditText>(R.id.etNombrePlaneta)
        val etFechaDesSistema = findViewById<EditText>(R.id.etFechaDesPlaneta)
        val etDistanciaSistema = findViewById<EditText>(R.id.etSuperficiePlaneta)
        val etMasaSolar = findViewById<EditText>(R.id.etMasaSolarSistema)
        val etTemperatura = findViewById<EditText>(R.id.etEdadPlaneta)
        val etHabitable = findViewById<EditText>(R.id.etHabitablePlaneta)

        val btnCrearSistema = findViewById<Button>(R.id.btnCrearPlaneta)
        btnCrearSistema.setOnClickListener {
            if (etNombreSistema.text.isNotEmpty()
                && etFechaDesSistema.text.isNotEmpty()
                && etDistanciaSistema.text.isNotEmpty()
                && etMasaSolar.text.isNotEmpty()
                && etTemperatura.text.isNotEmpty()
                && etHabitable.text.isNotEmpty()
            ){
                val transformHabitableSistema = etHabitable.text.toString()
                val resultHabitableSistema = transformHabitableSistema.toBoolean()

                val transformDistancia = etDistanciaSistema.text.toString()
                val resultDistancia = transformDistancia.toDouble()

                val transformMasaSolar = etMasaSolar.text.toString()
                val resultMasaSolar = transformMasaSolar.toDouble()

                val transformTemperatura = etTemperatura.text.toString()
                val resultTemperatura = transformTemperatura.toInt()

                val data = hashMapOf(
                    "nombre" to etNombreSistema.text.toString(),
                    "habitable" to resultHabitableSistema,
                    "temperatura" to resultTemperatura,
                    "distancia" to resultDistancia,
                    "masaSolar" to resultMasaSolar,
                    "fechaDescubrimiento" to etFechaDesSistema.text.toString()
                )

                db.collection("Sistema").add(data).
                addOnSuccessListener {
                    Toast.makeText(this, "Creacion Exitosa de Sistema", Toast.LENGTH_SHORT).show()
                    goActivity(SistemasPrincipal::class.java)
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al crear Sistema", Toast.LENGTH_SHORT).show()
                    goActivity(SistemasPrincipal::class.java)
                }
            }
        }
    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}