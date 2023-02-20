package com.example.fsmapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class HGoogleMapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap //se inicializa despues(lateinit)
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps2)
        solicitarPermisos()
        iniciarLogicaMapa()

        val boton = findViewById<Button>(R.id.btn_ir_carolina)
        boton.
        setOnClickListener {
            irCarolina();
        }
    }
    fun irCarolina(){
        val carolina = LatLng(-0.1825684318486696,
            -78.48447277600916)
        val zoom = 17f
        moverCamaraConZoom(carolina, zoom)
    }

    fun iniciarLogicaMapa(){
        val framentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment //El mapa es un fragmento de mapa
        framentoMapa.getMapAsync{ googleMap -> //Obttenemos el mapa a aprtir del R.Id.map
            if (googleMap != null) { //Verificamos que no sea nulo
                mapa = googleMap //asignamos el fragmento al mapa que declaramos antes
                establecerConfiguracionMapa()

                //Marcador
                val zoom = 17f //Zoom para movernos en el mapa
                val quicentro = LatLng( //Definimo latitud y longitud
                    -0.17577855381359975, -78.48009734334076
                )
                val titulo = "Quicentro"
                val markQuicentro = anadirMarcador(quicentro, titulo)  //Crearemos un marcado
                markQuicentro.tag = titulo  //Asignamos una etiqueta al marcador
                //Polilinea

                val poliLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.17761026253590054,
                                    -78.48085076089252),
                                LatLng(-0.17564689490459107,
                                    -78.48106533760762),
                                LatLng(-0.17658029921419555,
                                    -78.477160041393)
                            )
                    )
                poliLineaUno.tag = "linea-1" // <- ID


                //Poligono
                val poligonoUno = googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.1751930789313819,
                                    -78.47940819046653),
                                LatLng(-0.17747051536134076,
                                    -78.4789552527891),
                                LatLng(-0.17579274009118578,
                                    -78.47743057525523)
                            )
                    )
                poligonoUno.fillColor = 0xc771c4
                poligonoUno.tag = "poligono-2" // <- ID
                escucharListeners()
            }
        }
    }
    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa) {  //With nos permite usar una variable que nmo es nula
            val permisosFineLocation = ContextCompat //Pasamos el contexto
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION // permiso que van a chequear
                )
            //Vemos si la respuesta del permisosFine nos proporciona permisos Granted
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true //tenemos permisos
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true // no tenemos aun permisos
        }
    }
    //
    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    //Setear listene de acciones/ Dar clicl, mover, mover camara,etc
    fun escucharListeners(){
        mapa.setOnPolygonClickListener { //Click a polilinea
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag //ID
        }

        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener ${it}")
            it.tag //ID
        }

        mapa.setOnMarkerClickListener {   //Los marcadores rojos
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag //ID de la etiqueta ya podriamos haceruna logica cuando se manejen estos listeners.
            return@setOnMarkerClickListener true
        }

        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }

        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }

        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }





    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat //Pasamos el contexto
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION // permiso que van a chequear
            )
        //Vemos si la respuesta del permisosFine nos proporciona permisos Granted
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) { //De si tener permisos establecemos verdadero
            permisos = true
        } else {
            ActivityCompat.requestPermissions(  //Si no tenemos los permisos los pedimos
                this, //Contexto
                arrayOf(    //Arreglo permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 //Código de petición de los permisos
            )
        }
    }


}