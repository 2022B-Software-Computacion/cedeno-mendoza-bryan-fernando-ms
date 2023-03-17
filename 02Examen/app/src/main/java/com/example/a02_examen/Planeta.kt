package com.example.a02_examen

data class Planeta(
    var id: String = "",
    var nombre: String = "",
    var superficie: Double = 0.0,
    var habitable: Boolean? = null,
    var fechaDescubrimiento: String = "",
    var edad: Int=0
)
