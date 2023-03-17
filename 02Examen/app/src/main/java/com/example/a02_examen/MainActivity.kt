package com.example.a02_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRestaurant = findViewById<Button>(R.id.btnSistema)
        btnRestaurant.setOnClickListener{
            goActivity(SistemasPrincipal::class.java)
        }

        val btnDish = findViewById<Button>(R.id.btnPlaneta)
        btnDish.setOnClickListener{
            goActivity(PlanetasPrincipal::class.java)
        }

    }
    private fun goActivity(activity: Class<*>){
        val intent = Intent(this, activity)
        startActivity(intent)
    }
}