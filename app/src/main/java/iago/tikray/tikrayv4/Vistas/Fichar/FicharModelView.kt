package iago.tikray.tikrayv4.Vistas.Fichar

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.google.android.gms.location.*


@Suppress("DEPRECATION")
@HiltViewModel
class FicharModelView @Inject constructor() : ViewModel() {


    private val _estadoDelPermisoUbicacion = MutableLiveData<Boolean>()
    val estadoDelPermisoUbicacion: LiveData<Boolean> = _estadoDelPermisoUbicacion

    private val _ubicacion = MutableLiveData<Location?>()
    val ubicacion: LiveData<Location?> = _ubicacion

        private val _ubicacionX = MutableLiveData<Double?>()
    val ubicacionX: LiveData<Double?> = _ubicacionX

        private val _ubicacionY = MutableLiveData<Double?>()
    val ubicacionY: LiveData<Double?> = _ubicacionY




    @SuppressLint("MissingPermission")
    fun solicitarActualizacionesUbicacion(context: Context) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        val locationRequest = LocationRequest.create().apply {
            interval = 10000 // Actualizaciones de ubicación cada 10 segundos
            fastestInterval = 5000 // Actualizaciones de ubicación más rápidas si están disponibles
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                p0 ?: return
                for (location in p0.locations) {
                    Log.d("loca", "$location")
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }


    @SuppressLint("MissingPermission")
    fun obtenerUbicacion(context: Context) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            Log.d("localizacion", "$location")
            val ubicacion = location
            _ubicacion.value = location
            _ubicacionY.value = location.altitude
            _ubicacionX.value = location.latitude


        }
    }


    fun cambiarEstadoDelPermiso(estado: Boolean) {

        _estadoDelPermisoUbicacion.value = estado
    }

    fun ejecutarBoton(context: Context) {
        if (_estadoDelPermisoUbicacion.value!!){
            solicitarActualizacionesUbicacion(context)
            obtenerUbicacion(context)
            val ubicacionNecesariaX = 41.42763888
            val ubicacionNecesariaY = 2.1908888
            val ubicacionObtenida = ubicacionX

            val a = calcularDistancia(_ubicacionX.value!!.toDouble() , _ubicacionY.value!!.toDouble(), ubicacionNecesariaX, ubicacionNecesariaY )






        }
    }


    fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val punto1 = Location("punto1").apply {
            latitude = lat1
            longitude = lon1
        }

        val punto2 = Location("punto2").apply {
            latitude = lat2
            longitude = lon2
        }

        return punto1.distanceTo(punto2)
    }



}


//    private lateinit var fusedLocateClient:FusedLocationProviderClient
//    private lateinit var locationCallBack:LocationCallback
//
//

//
//    fun botonFichar(permision:Boolean){
//        if (!permision){
//
//        }
//    }
//
//

//
//    @SuppressLint("MissingPermission")
//    fun obtenerUbicacion(actividad:Context) {
//         fusedLocateClient = LocationServices.getFusedLocationProviderClient(actividad)
//        try {
//
//            fusedLocateClient.lastLocation.addOnSuccessListener {
//                if (it != null){
//                    Log.d("localizacion", "$it")
//
//
//                }
//                else{
//                    Log.d(" Error localizacion", "$it")
//
//                }
//
//            }
//            val localRequest = LocationRequest.Builder(
//                Priority.PRIORITY_HIGH_ACCURACY,
//                30000).apply { setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
//                setWaitForAccurateLocation(true)}.build()
//            locationCallBack = object : LocationCallback() {
//                override fun onLocationResult(p0: LocationResult) {
//                    super.onLocationResult(p0)
//
//                    for (location in p0.locations){
//                        imp
//                    }
//                }
//            }
//        }
//    }

