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




    //////////////// Provando la funcion Suma
    var sumaUno = Suma(1,2);
    var sumaDos = Suma(1, null);
    var sumaTres = Suma(null, 1);
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    Suma.pi
    Suma.elevarAlCuadrado(2)
    println(Suma.historialSumas)



    //Tipos de arreglos

    //AregloEstático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //ArregloDinámicos
    val arregloDinammico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinammico)
    arregloDinammico.add(11)
    arregloDinammico.add(12)
    println(arregloDinammico)

    //operadores -> sirven para los arreglos estáticos y dinámicos

    //For each->unit
    //iterar un arreglo
    val respuestaForEach: Unit = arregloDinammico
            .forEach{
                valorActual: Int ->
                println("valor actual:${valorActual}")
            }
    arregloEstatico
            .forEachIndexed{indice:Int, valorActual:Int ->
                println("valor${valorActual} indice: ${indice}")
            }




    //MAP -> Muta el arreglo (cambia el arreglo)
    //1)enviemos el nuevo valor de la iteración
    //2)nos devuelve es un nuevo arreglo con los valores modificados
    val respuestaMap: List<Double> = arregloDinammico
            .map{valorActual: Int ->
                return@map valorActual.toDouble() + 100.00
            }
    println(respuestaMap)

    val respuestaMapDos = arregloDinammico.map{it+15}
//      .map{valorActual: Int ->
//      return@map valorActual + 15
//       }
    println(respuestaMapDos)

    //Filter -> Filtrar el arreglo
    //1) devolver una expresión (true o false)
    //2) nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinammico
            .filter { valorActual: Int ->
                val mayoresACinco: Boolean=valorActual >5 //Expresión condición
                return@filter mayoresACinco
            }
    val respuestaFilterDos = arregloDinammico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY(alguno cumple?)
    //AND -> ALL(Todos cumplen?)
    val respuestaAny:Boolean = arregloDinammico
            .any{valorActual:Int ->
                return@any (valorActual>5)
            }
    println(respuestaAny )//true

    val respuestaAll: Boolean=arregloDinammico
            .all{ valorActual:Int ->
                return@all (valorActual>5)
            }
    println(respuestaAll)

    //Reduce -> valor acumulado
    //valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    //[1,2,3,4,5] -> sumeme todos los valores dela rreglo
    //valoriteración1 = valorEmpieza +1=0+1=1 -> iteración1
    //valoriteración2 = valorIteración1 + 2 = 1+2=3->iteración2

    val respuestaReduce: Int =arregloDinammico
            .reduce{ //acumulado = 0 siempre empieza en 0
                acumulado:Int, valorActual: Int->
                return@reduce (acumulado+valorActual)// ->lógica negocio
            }
    println(respuestaReduce) //78







}

fun imprimirNombre(nombre:String): unit{
    println("Nombre:${nombre}")
}

fun calcularSueldo(
    sueldo:Double,
    tasa:double=12.2,
    bonoEspecial:Double?=null,
): Double{
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}



Clases Abstractas
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
            uno: Int,
            dos: Int
    ){//Bloque código constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializado")
    }
}


abstract class Numeros( //Constructor Primario
        //uno: Int, //parámetro
        protected val numeroUno: Int, //propiedad de la clase protected
        protected val numeroDos: Int //propiedad de la clase protected
){
    init { //Bloque de código constructor PRIMARIO
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
        println("inicializado")
    }
}

class Suma(//constructor primario suma
        uno: Int, //parámetro
        dos: Int //parámetro
): Numeros(uno, dos) {
    init { //bloque constructor primario
        this.numeroUno
        this.numeroDos

    }

    constructor(//segundo constructor
            uno: Int?, //parametros
            dos: Int //parametro
    ) : this( //llamada al constructor primario
            if (uno == null) 0 else uno,
            dos
    ) {
    } //Bloque de código del constructor que puede ser opcional

    constructor(//tercer constructor
            uno: Int, //parametros
            dos: Int? //parametro
    ) : this( //llamada al constructor primario
            uno,
            if (dos == null) 0 else uno
    ) {
    }

    constructor(//cuarto constructor
            uno: Int?, //parametrosl
            dos: Int? //parametro
    ) : this( //llamada al constructor primario
            if (uno == null) 0 else uno,
            if (dos == null) 0 else uno
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{// atributos y métodos "compartidos" entre las instancias
    val pi=3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num*num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }

    }

}

