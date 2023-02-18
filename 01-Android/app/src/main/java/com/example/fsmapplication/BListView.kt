package com.example.fsmapplication

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0  // variable que me permite identificar que item del viewlist seleccione


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view) //traemos el listView
        val adaptador = ArrayAdapter(   //creamos un arreglo de entrenadores
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador  //Creamos un adaptador
        adaptador.notifyDataSetChanged()  //Actualizamos la interface
        val botonAnadirListView = findViewById<Button>(  // Escuchamos el boton añadir
            R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)         //seteamos los datos
            }
        registerForContextMenu(listView) // Cuando den clic sobre el list view se mostrara algo.


    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater     //muestra el xml que tenemos con el menu
        inflater.inflate(R.menu.menu, menu) // trabaja con el menu llamado menu
        //Obtener el id de ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position  // guardamos ese id como el item seleccionado
        idItemSeleccionado = id
    }

    //Acciones cuando se selecciona un elemento del menu contextual.
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar  ->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo() //Abrimos dialogo
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }



    fun abrirDialogo(){         //Existen varios tipos de dialogos como opcion multiple o solo una opcion, aceptar denegar,etc
        val builder = AlertDialog.Builder(this)  //la clase builder me permite crear los dialogos
        builder.setTitle("Desea eliminar")          //creamos titulo
        builder.setPositiveButton(                  //seteamos boton
            "Aceptar",                              //funcionalidad positiva
            DialogInterface.OnClickListener{ dialog, which ->
                //al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val opciones = resources.getStringArray(        //realizo opciones
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(           //realizamos una opcion previa o por defecto
            true, //lunes seleccionado
            false, //martes no seleccionado
            false //miercoles no seleccionado
        )
        builder.setMultiChoiceItems(        //ponemos las opciones y la seleccion previa en el dialogo
            opciones,
            seleccionPrevia,
            {
                    dialog,
                    which,
                    isChecked ->
                "Dio click en el item ${which}"
            }
        )
        val dialogo = builder.create()        ///creamos y mostramos el dialogo
        dialogo.show()
    }


    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>  //Llega un adaptador que es un array de entredadores
    ){
        arreglo.add(
            BEntrenador(    //añadimos  un entrenador al arreglo
                4,
                "Adrian",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()   //
    }


}