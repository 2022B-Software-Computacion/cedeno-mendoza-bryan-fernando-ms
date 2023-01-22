import java.time.LocalDate

fun main(args: Array<String>) {
    var opc=""
    var opcSistema=""
    var opcPlaneta=""

    while (opc!="x") {
        opcSistema=""
        opcPlaneta=""
        println(menuPrincipal())
        println("Ingrese una opcion para hacer")
        val opcion = readln()
        when (opcion) {
            ("a")-> {
                println(menuSistemas())
                while (opcSistema!="x"){
                    opcSistema=opcionesSistemas()
                }
            }
            ("b")-> {
                println(menuPlanetas())
                while (opcPlaneta!="x"){
                    opcPlaneta=opcionesPlanetas()
                }
            }
            ("x")->{
                opc=opcion
            }
            else ->
            {
                println("Ingrese una opcion correcta")
            }
        }
    }
}

fun crearPlaneta(identificador: Int):Planeta{
    val planetaCreado=Planeta()
    var nombre: String = ""
    var superficie: Double = 0.0
    var habitable: Boolean = false
    var fechaDescubrimiento = LocalDate.parse("2022-12-12")
    var edad: Int=0

    print("Nombre: ")
    nombre = readln()
    print("SuperficieT(x.x): ")
    superficie = readln().toDouble()
    print("Es Habitable?: ")
    habitable = readln().toBoolean()
    print("Fecha de Descubrimiento(yyyy-mm-dd): ")
    fechaDescubrimiento = LocalDate.parse(readln())
    print("Edad(ma): ")
    edad = readln().toInt()

    planetaCreado.ingresoDatos(nombre,superficie,habitable,fechaDescubrimiento,edad)
    if (identificador==0){
        planetaCreado.setId(0)
    }else{
        planetaCreado.setId(identificador)
    }
    return planetaCreado
}

fun opcionesPlanetas():String{
    println("Ingrese una opcion para hacer")
    var listaPlanetas: ArrayList<Planeta> = cargarListaPlanetas()
    var planetaCreado=Planeta()

    val opcion = readln()
    when (opcion) {
        ("a")-> { //Imprimir Lista de Planetas
            println("Se imprimen la lista de Planetas")
            leerListaPlanetas(listaPlanetas)
        }
        ("b")-> { //Crear nuevo planeta
            println("Se crea Planetas\n A continuacion Ingrese la  informacion requeria:\n")
            planetaCreado=crearPlaneta(listaPlanetas.size)
            println("agrego el planeta a la lista")
            listaPlanetas.add(planetaCreado)
            println("guardo nueva lista")
            guardarListaPlanetas(listaPlanetas)
        }
        ("c")-> { //Actualizar planeta
            println("Se actualiza Planetas")
            println("Se Actualiza Planetas\n A continuacion Ingrese la  informacion requeria:\n")
            var identificador:String
            while (true){
                println("Ingrese el identificador del Planeta")
                identificador = readln()
                if (!existePlaneta(listaPlanetas,identificador.toInt())){
                    println("No existe ese planeta")
                }else{
                    println("recoger datos nuevo planeta")
                    planetaCreado=crearPlaneta(identificador.toInt())
                    println("cambiar el planeta de la lista")
                    modificarPlaneta(listaPlanetas,identificador.toInt(),planetaCreado)
                    println("guardo nueva lista")
                    break
                }
            }
        }
        ("d")-> {
            println("Se elimina Planetas")
            println("Se Elimina Planetas\n A continuacion Ingrese la  informacion requeria:\n")
            var identificador:String

            while (true){
                println("Ingrese el identificador del Planeta a eliminar")
                identificador = readln()
                if (!existePlaneta(listaPlanetas,identificador.toInt())){
                    println("No existe ese planeta")
                }else{
                    println("Elimino el planeta de la lista")
                    eliminarPlaneta(listaPlanetas,identificador.toInt())
                    break
                }
            }

        }
        ("x")-> {
        println("Se vuelve al menu anterior")
        }
        else ->
        {
            println("Ingrese una opcion correcta")
        }
    }
    return opcion
}

fun crearSistemas(identificador: Int):Sistemas{
    val sistemaCreado= Sistemas()
    var nombre: String = ""
    var habitable: Boolean = false
    var temperatura: Int=0
    var distancia: Double = 0.0
    var masaSolar: Double = 0.0
    var fechaDescubrimiento = LocalDate.parse("2022-12-12")
    var listaPlanetas : MutableList<Int> = mutableListOf()

    print("Nombre: ")
    nombre = readln()
    print("Es Habitable(true/false)?: ")
    habitable = readln().toBoolean()
    print("Temperatura(X°K): ")
    temperatura = readln().toInt()
    print("Distancia A.L.(xx.x): ")
    distancia= readln().toDouble()
    print("Masa Solar(xx.x): ")
    masaSolar= readln().toDouble()
    print("Fecha de Descubrimiento(yyyy-mm-dd): ")
    fechaDescubrimiento = LocalDate.parse(readln())


    while (true){
        print("Ingrese planeta: ")
        listaPlanetas.add(readln().toInt())
        print("Hay mas planetas(y/n)?: ")
        if (readln()=="n"){
            break
        }
    }

    sistemaCreado.ingresoDatos(nombre,habitable,temperatura,distancia,masaSolar,fechaDescubrimiento,listaPlanetas)
    if (identificador==0){
        sistemaCreado.setId(0)
    }else{
        sistemaCreado.setId(identificador)
    }
    return sistemaCreado
}

fun opcionesSistemas():String{
    println("Ingrese una opcion para hacer")
    var listaSistemas: ArrayList<Sistemas> = cargarListaSistemas()
    var listaPlanetas: ArrayList<Planeta> = cargarListaPlanetas()
    var sistemaCreado=Sistemas()


    val opcion = readln()
    when (opcion) {
        ("a")-> { //Imprimir Lista de Sistemas
            println("Se imprimen la lista de Sistemas")
            leerListaSistemas(listaSistemas,listaPlanetas)
        }
        ("b")-> { //Crear nuevo Sistemas
            println("Se crea Sistemas\n A continuacion Ingrese la  informacion requeria:\n")
            sistemaCreado=crearSistemas(listaSistemas.size)
            println("agrego el Sistema a la lista")
            listaSistemas.add(sistemaCreado)
            println("guardo nueva lista")
            guardarListaSistemas(listaSistemas)
        }
        ("c")-> { //Actualizar sistema
            println("Se actualiza Sistemas")
            println("Se Actualiza Sistemas\n A continuacion Ingrese la  informacion requeria:\n")
            var identificador:String
            while (true){
                println("Ingrese el identificador del }Sistemas")
                identificador = readln()
                if (!existeSistema(listaSistemas,identificador.toInt())){
                    println("No existe ese Sistema")
                }else{
                    println("recoger datos nuevo Sistema")
                    sistemaCreado=crearSistemas(identificador.toInt())
                    println("cambiar el Sistema de la lista")
                    modificarSistemas(listaSistemas,identificador.toInt(),sistemaCreado)
                    println("guardo nueva lista")
                    break
                }
            }
        }
        ("d")-> {
            println("Se elimina Sistema")
            println("Se Elimina Sistema\n A continuacion Ingrese la  informacion requeria:\n")
            var identificador:String

            while (true){
                println("Ingrese el identificador del Sistema a eliminar")
                identificador = readln()
                if (!existeSistema(listaSistemas,identificador.toInt())){
                    println("No existe ese Sistema")
                }else{
                    println("Elimino el Sistema de la lista")
                    eliminarSistemas(listaSistemas,identificador.toInt())
                    break
                }
            }

        }
        ("x")-> {
            println("Se vuelve al menu anterior")
        }
        else ->
        {
            println("Ingrese una opcion correcta")
        }
    }
    return opcion
}

fun leerListaPlanetas(listaplanetas: ArrayList<Planeta>){
    listaplanetas
        .forEach {
                valorActual: Planeta ->
            println("Planeta Actual:${valorActual.toString()}")
        }
    //guardarListaPlanetas(listaplanetas)
}

fun eliminarPlaneta(listaplanetas: ArrayList<Planeta>,index:Int){
    listaplanetas.removeAt(index)
    guardarListaPlanetas(listaplanetas)
}

fun modificarPlaneta(listaplanetas: ArrayList<Planeta>,index:Int,planeta: Planeta){
    listaplanetas.set(index,planeta)
    guardarListaPlanetas(listaplanetas)
}

fun existePlaneta(listaplanetas: ArrayList<Planeta>,identificador: Int):Boolean{
    listaplanetas
        .forEachIndexed { i, p ->
            if (p.getID()==identificador ){
                println("Planeta Existe")
                return true
            }
        }
    println("Planeta NO Existe")
    return false
}

fun guardarListaPlanetas(listaplanetas:ArrayList<Planeta>){
    var listaPlanetasString: ArrayList<String> = arrayListOf<String>()
    var doc = Documento()
    val directorio ="Examen/src/main/archivos/planetas.txt"
    listaplanetas
        .forEach {
                planetaActual: Planeta ->
            listaPlanetasString.add(planetaActual.convertirText())
            //println("Planeta Actual:${planetaActual.convertirText()}")
        }
    doc.escribirDatos(directorio,listaPlanetasString)
}

fun cargarListaPlanetas():ArrayList<Planeta>{
    var doc = Documento()
    val directorio ="Examen/src/main/archivos/planetas.txt"
    var listaPlanetasString: ArrayList<String> = arrayListOf<String>()
    var listaPlanetas: ArrayList<Planeta> = arrayListOf<Planeta>()

    listaPlanetasString=doc.Leer(directorio)
    listaPlanetasString
        .forEach {
                planetaActual: String ->
            val auxPlaneta=Planeta()
            auxPlaneta.convertirObjeto(planetaActual)
            listaPlanetas.add(auxPlaneta)
            //println("Planeta Actual :) :${auxPlaneta.toString()}")
        }
    return listaPlanetas
}

fun leerListaSistemas(listasistemas: ArrayList<Sistemas>, listasplanetas: ArrayList<Planeta>){
    listasistemas
        .forEach {
                valorActual: Sistemas ->
            println("Sistemas Actual:\n${valorActual.toString()}")
            println("Planetas Detallados:\n${valorActual.mostrarDetallePlanetas(listasplanetas)} ")
        }
    guardarListaSistemas(listasistemas)
}

fun eliminarSistemas(listasistemas: ArrayList<Sistemas>,index:Int){
    listasistemas.removeAt(index)
    guardarListaSistemas(listasistemas)
}

fun modificarSistemas(listasistemas: ArrayList<Sistemas>,index:Int,sistema: Sistemas){
    listasistemas.set(index,sistema)
    guardarListaSistemas(listasistemas)
}

fun existeSistema(listaSistemas: ArrayList<Sistemas>,identificador: Int):Boolean{
    listaSistemas
        .forEachIndexed { i, s ->
            if (s.getID()==identificador ){
                println("Sistema Existe")
                return true
            }
        }
    println("Sistema NO Existe")
    return false
}

fun guardarListaSistemas(listasistemas:ArrayList<Sistemas>){
    var listaSistemasString: ArrayList<String> = arrayListOf<String>()
    var doc = Documento()
    val directorio ="Examen/src/main/archivos/sistemasPlanetarios.txt"
    listasistemas
        .forEach {
                sistemaActual: Sistemas ->
            listaSistemasString.add(sistemaActual.convertirText())
            //println("Sistemas Actual:${sistemaActual.convertirText()}")
        }
    doc.escribirDatos(directorio,listaSistemasString)
}

fun cargarListaSistemas():ArrayList<Sistemas>{
    var doc = Documento()
    val directorio ="Examen/src/main/archivos/sistemasPlanetarios.txt"
    var listaSistemasString: ArrayList<String> = arrayListOf<String>()
    var listaSistemas: ArrayList<Sistemas> = arrayListOf<Sistemas>()

    listaSistemasString=doc.Leer(directorio)
    listaSistemasString
        .forEach {
                sistemasActual: String ->
            val auxSistemas=Sistemas()
            auxSistemas.convertirObjeto(sistemasActual)
            listaSistemas.add(auxSistemas)
            //println("Sistemas Actual :) :${auxSistemas.toString()}")
        }
    return listaSistemas
}

fun menuPrincipal():String{

    return  "--------------Proyecto App Moviles--------------\n" +
            "-------------- Fernando Cedeño -----------------\n" +
            "a) SISTEMAS PLANETARIOS\n" +
            "b) PLANETAS\n" +
            "x) Salir\n" +
            "------------------------------------------------"
}

fun menuSistemas():String{

    return  "--------------SISTEMAS PLANETARIOS--------------\n" +
            "a) Listar Sistemas Planetarios\n" +
            "b) Crear Sistema\n" +
            "c) Actualizar Sistema\n" +
            "d) Eliminar Sistema\n" +
            "x) Atras\n" +
            "------------------------------------------------"
}

fun menuPlanetas():String{

    return  "--------------PLANETAS--------------\n" +
            "a) Listar Planetas  \n" +
            "b) Crear Planetas\n" +
            "c) Actualizar Planetas\n" +
            "d) Eliminar Planetas\n" +
            "x) Atras\n" +
            "------------------------------------------------"
}