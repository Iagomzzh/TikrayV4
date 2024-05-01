package iago.tikray.tikrayv4.Vistas.Fichar

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.google.android.gms.location.*
import iago.tikray.tikrayv4.AlertDialogExample
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


@Suppress("DEPRECATION")
@HiltViewModel
class FicharModelView @Inject constructor() : ViewModel() {


    private val _estadoDelPermisoUbicacion = MutableLiveData<Boolean>()
    val estadoDelPermisoUbicacion: LiveData<Boolean> = _estadoDelPermisoUbicacion

    private val _dialogoDeError = MutableLiveData<Boolean>()
    val dialogoDeError: LiveData<Boolean> = _dialogoDeError

    private val _obtenerUbi = MutableLiveData<Boolean>()
    val obtenerUbi: LiveData<Boolean> = _obtenerUbi


    fun cambiarObtenerUbi(estado:Boolean) {
        _obtenerUbi.value = estado
    }


    private val _ubicacion = MutableLiveData<Location?>()
    val ubicacion: LiveData<Location?> = _ubicacion

    private val _ubicacionX = MutableLiveData<Double?>()
    val ubicacionX: LiveData<Double?> = _ubicacionX

    private val _ubicacionY = MutableLiveData<Double?>()
    val ubicacionY: LiveData<Double?> = _ubicacionY


    private val _distancia = MutableLiveData<Double?>()
    val distancia: LiveData<Double?> = _distancia


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
            _ubicacionX.value = location.latitude
            _ubicacionY.value = location.longitude


        }
    }


    fun cambiarEstadoDelPermiso(estado: Boolean) {
        _estadoDelPermisoUbicacion.value = estado
    }

    suspend fun ejecutarBoton(context: Context) {
        if (_estadoDelPermisoUbicacion.value != null && _estadoDelPermisoUbicacion.value == true) {
            solicitarActualizacionesUbicacion(context)
            obtenerUbicacion(context)
            val ubicacionNecesariaX = 41.42791245505382
            val ubicacionNecesariaY = 2.190342861050696
            esperarUbicacion()
            val ubicacionObtenida = ubicacionX

            _distancia.value = calcularDistancia(
                _ubicacionX.value?.toDouble() ?: 1.0,
                _ubicacionY.value?.toDouble() ?: 1.0,
                ubicacionNecesariaX,
                ubicacionNecesariaY
            ).toDouble()
            Log.d(
                "la distancia es:",
                " ${
                    calcularDistancia(
                        _ubicacionX.value?.toDouble() ?: 1.0,
                        _ubicacionY.value?.toDouble() ?: 1.0,
                        ubicacionNecesariaX,
                        ubicacionNecesariaY
                    ).toInt()
                }"
            )


        }
    }

    suspend fun esperarUbicacion(): Location {
        while (_ubicacion.value == null) {
            delay(1000) // Espera un segundo antes de verificar de nuevo
        }
        return _ubicacion.value!!
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


    fun cambiarDialogoErrorEstado(dialogoDeError: Boolean) {
        _dialogoDeError.value = dialogoDeError
    }


    @Composable
    fun Alpulsar(estadoPermiso: Boolean, dialogoDeError: Boolean) {



        if (!estadoPermiso || dialogoDeError) {
            cambiarDialogoErrorEstado(true)
            cambiarEstadoDelPermiso(true)
            AlertDialogExample(
                dismiss = { cambiarDialogoErrorEstado(!dialogoDeError) },
                confirm = { cambiarDialogoErrorEstado(!dialogoDeError) },
                textTitle = "Error al intentar acceder a su ubicación",
                textBody = "Parece que la app no tiene permisos para acceder a la ubicación, cambie el permiso y vuelva a intentarlo"
            )

        }
        else{


        }
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

