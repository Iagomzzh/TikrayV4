package iago.tikray.tikrayv4.Vistas.MenuEntrada

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import iago.tikray.tikrayv4.Navegacion.Ruta
import javax.inject.Inject


class MenuEntradaViewModel @Inject constructor() : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    fun logOut(navigationController: NavHostController) {
        auth.signOut()
    }


    private val _nombreCompletoDB = MutableLiveData<List<String>?>()
    val nombreCompletoDB: LiveData<List<String>?> = _nombreCompletoDB

    private val _numeroDeEmpleados = MutableLiveData<Int>()
    val numeroDeEmpleados: LiveData<Int> = _numeroDeEmpleados

    private val _puestoTrabajoDB = MutableLiveData<List<String>>()
    val puestoTrabajoDB: LiveData<List<String>> = _puestoTrabajoDB

    private val _addressDB = MutableLiveData<List<String>>()
    val address: LiveData<List<String>> = _addressDB

    private val _horaInicioDB = MutableLiveData<List<String>>()
    val horaInicioDB: LiveData<List<String>> = _horaInicioDB

    private val _horaFinalDB = MutableLiveData<List<String>>()
    val horaFinalDB: LiveData<List<String>> = _horaFinalDB

    private val _telefonoDB = MutableLiveData<List<String>>()
    val telefonoDB: LiveData<List<String>> = _telefonoDB


    private val _nombreCompletoDBS = MutableLiveData<String?>()
    val nombreCompletoDBS: LiveData<String?> = _nombreCompletoDBS

    private val _numeroDeEmpleadosS = MutableLiveData<String>()
    val numeroDeEmpleadosS: LiveData<String> = _numeroDeEmpleadosS

    private val _puestoTrabajoDBS = MutableLiveData<String>()
    val puestoTrabajoDBS: LiveData<String> = _puestoTrabajoDBS

    private val _addressDBS = MutableLiveData<String>()
    val addressS: LiveData<String> = _addressDBS

    private val _horaInicioDBS = MutableLiveData<String>()
    val horaInicioDBS: LiveData<String> = _horaInicioDBS

    private val _horaFinalDBS = MutableLiveData<String>()
    val horaFinalDBS: LiveData<String> = _horaFinalDBS

    private val _telefonoDBS = MutableLiveData<String>()
    val telefonoDBS: LiveData<String> = _telefonoDBS

    private val _sumarParaAvanzar = MutableLiveData<String?>()
    val sumarParaAvanzar: LiveData<String?> = _sumarParaAvanzar


    fun cambiarDatos(
        nombreCompleto: String,
        puesto: String,
        correo: String,
        horaInicio: String,
        horaFinal: String,
        telefono:String
    ) {
        _nombreCompletoDBS.value = nombreCompleto
        _puestoTrabajoDBS.value = puesto
        _addressDBS.value = correo
        _horaInicioDBS.value = horaInicio
        _horaFinalDBS.value = horaFinal
        _telefonoDBS.value = telefono

    }


    fun puestoTrabajoDB(lista: List<String>) {
        _puestoTrabajoDB.value = lista


    }


    fun addressDB(lista: List<String>) {
        _addressDB.value = lista


    }

    fun horaInicioDB(lista: List<String>) {
        _horaInicioDB.value = lista


    }

    fun horaFinalDB(lista: List<String>) {
        _horaFinalDB.value = lista


    }

    fun telefonoDB(lista: List<String>) {
        _telefonoDB.value = lista


    }


    fun obtenerSizeDeDocument() {

        val db = FirebaseFirestore.getInstance()

        db.collection("usersInformation").get()
            .addOnSuccessListener { result ->
                _numeroDeEmpleados.value = result.size()
                println("Número de documentos: ${_numeroDeEmpleados.value}")
            }
            .addOnFailureListener { exception ->
                println("Error obteniendo documentos: $exception")
            }
    }


    fun imprimirInformacion(datoQueRecorrer: String) {
        val db = FirebaseFirestore.getInstance()
        var a: List<String> = listOf()
        val nombres = mutableListOf<String>()

        db.collection("usersInformation").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val nombre: String? = document.getString(datoQueRecorrer)




                    if (nombre != null) {
                        nombres.add(nombre)
                    }
                }

                when (datoQueRecorrer) {
                    "NombreCompleto" -> _nombreCompletoDB.value = nombres
                    "address" -> _addressDB.value = nombres
                    "PuestoTrabajo" -> _puestoTrabajoDB.value = nombres
                    "horaFinal" -> _horaFinalDB.value = nombres
                    "horaInicio" -> _horaInicioDB.value = nombres
                    "telefono" -> _telefonoDB.value = nombres


                }


            }
            .addOnFailureListener { exception ->
                println("Error obteniendo documentos: $exception")
            }


    }
}






