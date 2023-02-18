import java.time.LocalDate

class Sistemas {
    private var id: Int = 0
    private var nombre: String = ""
    private var habitable: Boolean = false
    private var temperatura: Int=0
    private var distancia: Double = 0.0
    private var masaSolar: Double = 0.0
    private var fechaDescubrimiento = LocalDate.parse("2022-12-12")
    private var listaPlanetas : MutableList<Int> = mutableListOf()


    public fun setId(id:Int){
        this.id=id;
    }
    public fun getID(): Int? {
        return id;
    }


    fun ingresoDatos(nombre:String,habitable:Boolean,temp:Int,distancia:Double,masa:Double, fecha: LocalDate,listaP:MutableList<Int>){
        var doc = Documento()
        //this.id=doc.ultimoId("Examen/src/main/archivos/sistemasPlanetarios.txt")
        this.nombre=nombre
        this.habitable=habitable
        this.temperatura=temp
        this.distancia=distancia
        this.masaSolar=masa
        this.fechaDescubrimiento=fecha
        this.listaPlanetas=listaP
    }
    fun convertirArrayText():String{  // convertir el array de planetas a texto
        var respuestaForEach=""
            this.listaPlanetas
            .forEach{
                    valorActual: Int ->
                respuestaForEach +=",${valorActual}"
            }
        return respuestaForEach
    }

    fun convertirText():String{     //convertir  el objeto en texto
        var planetas=convertirArrayText()
        return "$id,$nombre,$habitable,$temperatura,$distancia,$masaSolar,$fechaDescubrimiento$planetas"
    }

    fun convertirObjeto(sistema:String){    //convertir  el texto en el objeto
        val yourArray: List<String> = sistema.split(",")
        this.id=yourArray[0].toInt()
        this.nombre=yourArray[1]
        this.habitable=yourArray[2].toBoolean()
        this.temperatura=yourArray[3].toInt()
        this.distancia=yourArray[4].toDouble()
        this.masaSolar=yourArray[5].toDouble()
        this.fechaDescubrimiento=LocalDate.parse(yourArray[6])
        val list: List<String> =yourArray.subList(7,yourArray.size)
        this.listaPlanetas= list.map { it.toInt() }.toMutableList()

    }

    override fun toString(): String {
        return "Sistemas(id=$id, nombre='$nombre', habitable=$habitable, temperatura=$temperatura, distancia=$distancia, masaSolar=$masaSolar, fechaDescubrimiento=$fechaDescubrimiento, listaPlanetas=$listaPlanetas)"
    }

    fun mostrarDetallePlanetas(listaPlanetasDetallados:ArrayList<Planeta>): String{  //mostrar el detalle del planeta
        var detalle:String=""
        var planeta=Planeta()
        listaPlanetas.forEach{
            if(it==listaPlanetasDetallados.get(it).getID()){
                planeta=listaPlanetasDetallados.get(it)
            }
            detalle+=planeta.toString()+"\n"
        }
        return detalle
    }
}