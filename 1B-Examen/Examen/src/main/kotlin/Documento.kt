import java.io.File
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class Documento{

    fun Leer(urlDocumento:String):ArrayList<String>{
        val archivo:ArrayList<String> = arrayListOf<String>()
        try {
            val sc = Scanner(File(urlDocumento))
            while (sc.hasNextLine()) {
                val line = sc.nextLine()
                if(line==""){
                    //println("good")
                }else{
                    archivo.add(line)}
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return archivo;
    }

    public fun escribirDatos(urlDocumento:String,listaObjetos: ArrayList<String>){
        val file = File(urlDocumento)
        var cvs = ""
        listaObjetos.forEach {
            cvs += it +"\n"
        }
        file.writeText(cvs, Charsets.UTF_8)
    }


    fun ultimoId(urlDocument:String):Int{
        var aux:Int=0
        //println(urlDocument)
        try {
            val sc = Scanner(File(urlDocument))
            while (sc.hasNextLine()) {
                val line = sc.nextLine()
                aux++
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return aux;
    }
}