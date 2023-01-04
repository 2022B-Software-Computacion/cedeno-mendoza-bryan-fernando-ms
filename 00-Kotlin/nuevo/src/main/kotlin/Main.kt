import java.util.*
//Main kt
fun main(){
    println("Hola")
    //Tipos de variables

    //Immutables(No se reasignan)
    val inmutables: string = "Adrian";
    //inmutable="Vicente";//No se puede re asignar


    //Mutables(Re asignar)
    var mutable:string ="Viente"
    mutable="adrian"

    //Es recomendable usar val> var
    //Nota: el ; es opcional en Kotlin, Kotlin sigue siendo java

    //Sintaxis Duck Typing
    val ejemploVariable = "Ejemplo"
    val edadEjemplo: int=12
    ejemploVaribles.trim()

    //Variables Primitivas
    val nombre: String = "Fernando Cedeño"
    val sueldo:Double  =1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean  = true
    val fechaNacimiento: Date  = Date()

    //Condicionales

    if (true){

    }else{

    }

    val estadoCivilWhen='S'
    when (estadoCivilWhen){
        ("S")->{

        }
        "C"->{

        }
        "UN"->println("hablar")
        else->println("No reconocido")
    }
    val coqueteo = if(estadoCivilWhen=="S") "SI" else "NO"


}

fun imprimirNombre(nombre:String): unit{
    println("Nombre:${nombre}")
}

fun calcularSueldo(
    sueldo:Double,
    tasa:double=12.2,
    bonoEspecial:Double?=null,
): Double{
    if(bonoEspecial)
}