package com.example.a02_examen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_examen.adapter.PlanetaAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlanetasPrincipal : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var planetaList: ArrayList<Planeta>
    private var db = Firebase.firestore
    var idPlaneta = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planetas_principal)

        recyclerView = findViewById(R.id.recyclerPlaneta)
        recyclerView.layoutManager = LinearLayoutManager(this)

        planetaList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Planeta").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (document in it.documents) {
                    val planetaItem: Planeta? = document.toObject(Planeta::class.java)
                    if (planetaItem != null) {
                        planetaItem.id = document.id
                        println(planetaItem.id)
                        planetaList.add(planetaItem)
                    }
                }
                val adapter = PlanetaAdapter(planetaList)
                recyclerView.adapter =  adapter
                adapter.setOnItemClickListener(object : PlanetaAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {

                        val idPlaneta = planetaList[position].id
                        Toast.makeText(this@PlanetasPrincipal, "Id:   $idPlaneta", Toast.LENGTH_SHORT).show()
                        val btnEditPlaneta = findViewById<Button>(R.id.btnPlanetaEdit)
                        btnEditPlaneta.setOnClickListener {
                            sentDataToOtherActivity(ActualizarPlaneta::class.java, idPlaneta)
                        }
                    }
                })
            }
        }

            .addOnFailureListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }

        val btnCreatePlaneta = findViewById<Button>(R.id.btnPlanetaCreate)
        btnCreatePlaneta.setOnClickListener {
            goActivity(CrearPlaneta::class.java)
        }
    }

    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    private fun sentDataToOtherActivity(activity: Class<*>, id: String) {
        val intent = Intent(this, activity)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}