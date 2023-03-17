package com.example.a02_examen

data class Sistema(
    var id: String = "",
    var nombre: String = "",
    var habitable: Boolean? = null,
    var temperatura: Int=0,
    var distancia: Double = 0.0,
    var masaSolar: Double = 0.0,
    var fechaDescubrimiento: String = "",

)
