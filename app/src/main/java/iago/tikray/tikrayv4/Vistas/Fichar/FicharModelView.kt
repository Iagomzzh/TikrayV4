package iago.tikray.tikrayv4.Vistas.Fichar

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.google.android.gms.location.*
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.rpc.context.AttributeContext.Auth
import iago.tikray.tikrayv4.AlertDialogExample
import iago.tikray.tikrayv4.Firebase.Firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Suppress("DEPRECATION")
@HiltViewModel
class FicharModelView @Inject constructor() : ViewModel() {

    val db = FirebaseFirestore.getInstance()
    val firebaseAuth = FirebaseAuth.getInstance()


    private val _estadoDelPermisoUbicacion = MutableLiveData<Boolean>()
    val estadoDelPermisoUbicacion: LiveData<Boolean> = _estadoDelPermisoUbicacion

    private val _dialogoDeError = MutableLiveData<Boolean>()
    val dialogoDeError: LiveData<Boolean> = _dialogoDeError

    private val _obtenerUbi = MutableLiveData<Boolean>()
    val obtenerUbi: LiveData<Boolean> = _obtenerUbi


    fun cambiarObtenerUbi(estado: Boolean) {
        _obtenerUbi.value = estado
    }


    private val _ubicacion = MutableLiveData<Location?>()
    val ubicacion: LiveData<Location?> = _ubicacion
    private val _direccionFichaje = MutableLiveData<String?>()
    val direecionFichaje: LiveData<String?> = _direccionFichaje

    fun imprimirInformacion(datoQueRecorrer: String) {
        val db = FirebaseFirestore.getInstance()
        var a: List<String> = listOf()
        val nombres = mutableListOf<String>()

        db.collection("ultimoFichaje").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nombre: String? = document.getString("estado")

                }
            }
    }

    private val _ubicacionX = MutableLiveData<Double?>()
    val ubicacionX: LiveData<Double?> = _ubicacionX

    private val _ubicacionY = MutableLiveData<Double?>()
    val ubicacionY: LiveData<Double?> = _ubicacionY


    private val _distancia = MutableLiveData<Double?>()
    val distancia: LiveData<Double?> = _distancia


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
        Log.d("variables", "${_estadoDelPermisoUbicacion.value}")

        if (_estadoDelPermisoUbicacion.value != null && _estadoDelPermisoUbicacion.value == true) {
            obtenerUbicacion(context)

            esperarUbicacion()
            val ubicacionObtenida = ubicacionX


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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateString(): String {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return LocalDate.now().format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTimeString(): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return LocalTime.now().format(formatter)
    }


    fun cambiarDialogoErrorEstado(dialogoDeError: Boolean) {
        _dialogoDeError.value = dialogoDeError
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    suspend fun funcionEnteraParaLaUbicacion(context: Context) {
        if (_estadoDelPermisoUbicacion.value != false) {
            val fusedLocationClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)

            val locationRequest = LocationRequest.create().apply {
                interval = 10000 // Actualizaciones de ubicación cada 10 segundos
                fastestInterval =
                    5000 // Actualizaciones de ubicación más rápidas si están disponibles
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

            delay(1000L)
            obtenerUbicacion(context)
            esperarUbicacion()
            val ubicacionNecesariaX = 41.453380392340705
            val ubicacionNecesariaY = 2.1862865649926837
            _distancia.value = calcularDistancia(
                _ubicacionX.value?.toDouble() ?: 1.0,
                _ubicacionY.value?.toDouble() ?: 1.0,
                ubicacionNecesariaX,
                ubicacionNecesariaY
            ).toDouble()
            Log.d(
                "la distancia es:",
                " ${distancia.value}"
            )

            val distanciaToInt = distancia.value?.toInt()
            if (distanciaToInt != null && distanciaToInt < 80) {
                val userEmail = firebaseAuth.currentUser?.email.toString()
                val horaActual = getCurrentTimeString()
                val fecha = getCurrentDateString()
                db.collection("ultimoFichaje").document(userEmail).set(

                    hashMapOf(
                        "correo" to userEmail,
                        "hora" to horaActual,
                        "fecha" to fecha,



                        )
                )

                db.collection("fichaje").document().set(
                    hashMapOf(
                        "correo" to userEmail,
                        "hora" to horaActual,
                        "fecha" to fecha
                    )
                )


            }


        }
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

        } else {


        }
    }


}

