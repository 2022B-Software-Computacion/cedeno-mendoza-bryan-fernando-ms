package com.example.fsmapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class HGoogleMapsActivity : AppCompatActivity() {

    private lateinit var mapa: GoogleMap //se inicializa despues(lateinit)
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps2)
        solicitarPermisos()
        iniciarLogicaMapa()

    }
    fun iniciarLogicaMapa(){
        val framentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment //El mapa es un fragmento de mapa
        framentoMapa.getMapAsync{ googleMap -> //Obttenemos el mapa a aprtir del R.Id.map
            if (googleMap != null) { //Verificamos que no sea nulo
                mapa = googleMap //asignamos el fragmento al mapa que declaramos antes
                //establecerConfiguracionMapa()
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
                //mapa.isMyLocationEnabled = true //tenemos permisos
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