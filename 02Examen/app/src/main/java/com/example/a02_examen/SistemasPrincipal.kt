package com.example.a02_examen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_examen.adapter.SistemaAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SistemasPrincipal : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var Sistemalist : ArrayList<Sistema>
    private var db = Firebase.firestore
    var idSistema = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistemas_principal)

        recyclerView = findViewById(R.id.recyclerSistemas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        Sistemalist = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Sistema").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (document in it.documents) {
                    val sistemaItem: Sistema? = document.toObject(Sistema::class.java)
                    if (sistemaItem != null) {
                        sistemaItem.id = document.id
                        println(sistemaItem.id)
                        Sistemalist.add(sistemaItem)
                    }
                }

                val adapter = SistemaAdapter(Sistemalist)
                recyclerView.adapter =  adapter
                adapter.setOnItemClickListener(object : SistemaAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {

                        val idSistema = Sistemalist[position].id
                        Toast.makeText(this@SistemasPrincipal, "Id:   $idSistema", Toast.LENGTH_SHORT).show()
                        val btnEditarSistema = findViewById<Button>(R.id.btnPlanetaEdit)
                        btnEditarSistema.setOnClickListener {
                            sentDataToOtherActiity(ActualizarSistema::class.java, idSistema)
                        }
                    }
                })
            }
        }
            .addOnFailureListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }

        val btnCrearSistema = findViewById<Button>(R.id.btnSistemasCreate)
        btnCrearSistema.setOnClickListener {
            goActivity(CrearSistema::class.java)
        }

    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    private fun sentDataToOtherActiity(activity: Class<*>, id: String) {
        val intent = Intent(this, activity)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}