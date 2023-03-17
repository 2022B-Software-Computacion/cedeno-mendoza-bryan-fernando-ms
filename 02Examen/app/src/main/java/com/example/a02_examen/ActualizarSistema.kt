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

class ActualizarSistema : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_sistema)

        val bundle = intent.extras
        val idSistema = bundle?.getString("id")
        var docRef = idSistema?.let { db.collection("Sistema").document(it) }

        val etNombreSistema = findViewById<EditText>(R.id.etNombreSistemaUpdate)
        val etFechaDesSistema = findViewById<EditText>(R.id.etFechaDesSistemaUpdate)
        val etDistanciaSistema = findViewById<EditText>(R.id.etDistanciaSistemaUpdate)
        val etMasaSolar = findViewById<EditText>(R.id.etMasaSolarSistemaUpdate)
        val etTemperatura = findViewById<EditText>(R.id.etTemperaturaSistemaUpdate)
        val etHabitable = findViewById<EditText>(R.id.etHabitableSistemaUpdate)
        val btnActualizarSistema = findViewById<Button>(R.id.btnSistemaUpdate)


        if (docRef != null) {
            docRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Access the DocumentSnapshot data here.
                    val nombre = documentSnapshot.getString("nombre")
                    val habitableAux = documentSnapshot.getBoolean("habitable")
                    val habitable = habitableAux.toString()
                    val fechaDescubrimiento = documentSnapshot.getString("fechaDescubrimiento")
                    val distanciaAux = documentSnapshot.getDouble("distancia")
                    val distancia = distanciaAux.toString()
                    val masaSolarAux = documentSnapshot.getDouble("masaSolar")
                    val masaSolar = masaSolarAux.toString()
                    val temperaturaAux = documentSnapshot.get("temperatura")
                    val temperatura = temperaturaAux.toString()


                    etNombreSistema.setText(nombre)
                    etFechaDesSistema.setText(fechaDescubrimiento)
                    etDistanciaSistema.setText(distancia)
                    etMasaSolar.setText(masaSolar)
                    etTemperatura.setText(temperatura)
                    etHabitable.setText(habitable)
                }
            }
        }

        btnActualizarSistema.setOnClickListener {

            if (etNombreSistema.text.isNotEmpty()
                && etFechaDesSistema.text.isNotEmpty()
                && etDistanciaSistema.text.isNotEmpty()
                && etMasaSolar.text.isNotEmpty()
                && etTemperatura.text.isNotEmpty()
                && etHabitable.text.isNotEmpty()
            ){

                val transformTemperatura = etTemperatura.text.toString()
                val resultTemperatura = transformTemperatura.toInt()

                val transformHabitableSistema = etHabitable.text.toString()
                val resultHabitableSistema = transformHabitableSistema.toBoolean()

                val transformDistancia = etDistanciaSistema.text.toString()
                val resultDistancia = transformDistancia.toDouble()

                val transformMasaSolar = etMasaSolar.text.toString()
                val resultMasaSolar = transformMasaSolar.toDouble()



                val data = hashMapOf(
                    "nombre" to etNombreSistema.text.toString(),
                    "habitable" to resultHabitableSistema,
                    "temperatura" to resultTemperatura,
                    "distancia" to resultDistancia,
                    "masaSolar" to resultMasaSolar,
                    "fechaDescubrimiento" to etFechaDesSistema.text.toString()
                )


                if (idSistema != null) {

                    db.collection("Sistema").document(idSistema).update(data as Map<String, Any>).
                    addOnSuccessListener {
                        Toast.makeText(this, "Actualizacion Exitosa de Sistema", Toast.LENGTH_SHORT).show()
                        goActivity(SistemasPrincipal::class.java)
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error al actualizar Sistema", Toast.LENGTH_SHORT).show()
                        goActivity(SistemasPrincipal::class.java)
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