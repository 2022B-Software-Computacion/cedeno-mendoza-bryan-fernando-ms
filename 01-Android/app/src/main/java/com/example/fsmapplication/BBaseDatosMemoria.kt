package com.example.fsmapplication

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador.add(BEntrenador(1,"Fernando","a@a.com"))
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Cedeno","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Mendoza","c@c.com")
                )
        }
    }
}