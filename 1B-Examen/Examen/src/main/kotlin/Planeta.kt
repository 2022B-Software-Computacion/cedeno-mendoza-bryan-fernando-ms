import java.time.LocalDate

class Planeta {
    private var id: Int = 0
    private var nombre: String = ""
    private var superficie: Double = 0.0
    private var habitable: Boolean = false
    private var fechaDescubrimiento = LocalDate.parse("2022-12-12")
    private var edad: Int=0

    public fun setId(id:Int){
        this.id=id;
    }
    public fun getID(): Int? {
        return id;
    }

    fun ingresoDatos(nombre:String,superficie:Double,habitable:Boolean,fecha:LocalDate,edad:Int){
        var doc = Documento()
        //this.id=doc.ultimoId("Examen/src/main/archivos/planetas.txt")
        this.nombre=nombre
        this.superficie=superficie
        this.habitable=habitable
        this.fechaDescubrimiento=fecha
        this.edad=edad
    }
    fun convertirText():String{
        return "$id,$nombre,$superficie,$habitable,$fechaDescubrimiento,$edad"
    }

    fun convertirObjeto(planeta:String){
        val yourArray: List<String> = planeta.split(",")
        this.id=yourArray[0].toInt()
        this.nombre=yourArray[1]
        this.superficie=yourArray[2].toDouble()
        this.habitable=yourArray[3].toBoolean()
        this.fechaDescubrimiento=LocalDate.parse(yourArray[4])
        this.edad=yourArray[5].toInt()
    }
    override fun toString(): String {
        return "id: $id\tPlaneta: $nombre\tSuperficie: $superficie\tEs Habitable?: $habitable\tFecha de Descubrimiento: $fechaDescubrimiento Edad=$edad)"
    }
}