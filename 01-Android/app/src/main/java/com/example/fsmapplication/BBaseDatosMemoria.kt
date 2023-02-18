package com.example.fsmapplication

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador.add(BEntrenador("Fernando","a@a.com"))
            arregloBEntrenador
                .add(
                    BEntrenador("Cedeno","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Mendoza","c@c.com")
                )
        }
    }
}