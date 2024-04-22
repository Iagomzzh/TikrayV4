@file:Suppress("UNUSED_EXPRESSION")

package iago.tikray.tikrayv4.Register

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.AlertDialogExample

import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar
import javax.inject.Inject

@Suppress("UnusedEquals")
@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val db = FirebaseFirestore.getInstance()


    //nombre
    private val _nombre = MutableLiveData<String>()
    val name: LiveData<String> = _nombre

    fun cambioDatos(
        nombre: String,
        correo: String,
        contrasenya: String,
        confirmarContrasenya: String
    ): Boolean {
        _nombre.value = nombre
        _correo.value = correo
        _contrasenya.value = contrasenya
        _confirmarContrasenya.value = confirmarContrasenya

        return if (_nombre.value!!.isNotEmpty() && _correo.value!!.isNotEmpty() && _contrasenya.value!!.isNotEmpty() && _confirmarContrasenya.value!!.isNotEmpty() && _contrasenya.value == _confirmarContrasenya.value && _contrasenya.value!!.length > 6) {
            true

        } else {
            false
        }
    }


    //correo
    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo


    //Contrasenya
    private val _contrasenya = MutableLiveData<String>()
    val contrasenya: LiveData<String> = _contrasenya


    //Confirmar contrasenya
    private val _confirmarContrasenya = MutableLiveData<String>()
    val confirmarContrasenya: LiveData<String> = _confirmarContrasenya

    //Boton Register

    private val _botonRegister = MutableLiveData<Boolean>()
    val botonRegister: LiveData<Boolean> = _botonRegister


    //Estado ProgressBar
    private val _progresoBarraContrasenya = MutableLiveData<Float>()
    val progresoBarraContrasenya: LiveData<Float> = _progresoBarraContrasenya

    //Estado icono
    private val _estadoIcono = MutableLiveData<Boolean>()
    val estadoIcono: LiveData<Boolean> = _estadoIcono

    private val _resultadoRegistro = MutableLiveData<AuthResult>()
    val resultadoRegistro: LiveData<AuthResult> = _resultadoRegistro

    private val auth = FirebaseAuth.getInstance()

    // Register correcto

    private val _goToNext = MutableLiveData<Int>()
    val goToNext: LiveData<Int> = _goToNext

    // Estado del registro

    /*      fun registro(): Boolean {
            val registro:Boolean = register(correo = _correo.value.toString(), passwd = contrasenya.value.toString() )
            Log.d("a", registro.toString())
            return registro
        }*/


    fun register(username: String, password: String) {
        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener { task ->
                try {
                    if (task.isSuccessful) {
                        _goToNext.value = 1


                        Log.d(
                            "Chivato Registro",
                            "Usuario creado ${task.result} ${_goToNext.value}"
                        )
                    } else {
                        _goToNext.value = 2
                        _resultadoRegistro.value = task.result

                        Log.d(
                            "Chivato Registro",
                            "Error creating user:  ${_goToNext.value}"
                        )
                    }
                    //modifiyProcessing()

                } catch (e: Exception) {
                    _goToNext.value = 2
                    Log.d(
                        "Chivato Registro",
                        "Error creating user: $e ${_goToNext.value}"
                    )


                }


            }

    }


    fun calcularProgreso(progreso: String): Float {
        val numeroLenght = progreso.length
        return when (numeroLenght) {
            in 1..2 -> 0.1f
            in 3..5 -> 0.4f
            in 6..7 -> 0.6f
            in 8..10 -> 0.8f
            in 11..1000 -> 1f
            else -> 0f

        }


    }

    fun mostrarBarraProgressBar(contrasenya: String): Color {
        val numeroLenght = contrasenya.length
        return if (numeroLenght == 0) {
            Color.White
        } else {
            Color.White
        }


    }


    fun colorProgressBar(contrasenya: String): Color {
        return when (contrasenya.length) {
            in 1..6 -> Color.Red
            in 7..10 -> Color.Yellow
            else -> Color.Green

        }
    }

    fun contrasenyasCoinciden(contrasenya: String, confirmarContrasenya: String): Color {
        return if (contrasenya.isEmpty() || confirmarContrasenya.isEmpty() || contrasenya == confirmarContrasenya) Color.Transparent else Color.Red

    }

    fun iconoPassword(iconoEstado: Boolean) {
        _estadoIcono.value = !iconoEstado


    }


    fun elegirIcono(iconoEstado: Boolean): ImageVector {
        val icono = if (iconoEstado) {
            Icons.Rounded.Visibility
        } else {
            Icons.Rounded.VisibilityOff
        }
        return icono
    }


    fun mostrarPassword(estado: Boolean): VisualTransformation {
        if (!estado) {
            return PasswordVisualTransformation()

        } else {

            return VisualTransformation.None


        }

    }

    fun botonGuardar() {
        val calendar = Calendar.getInstance()
        val dia = calendar.get(Calendar.DAY_OF_MONTH)
        val mes =
            calendar.get(Calendar.MONTH) + 1 // Suma 1 porque los meses en Calendar van de 0 a 11
        val año = calendar.get(Calendar.YEAR)

        db.collection("users").document(correo.value.toString()).set(
            hashMapOf(
                "correo" to correo,
                "Nombre" to name,
                "fecha" to "$dia/$mes/$año"
            )
        )

    }

    @Composable
    fun DialogoRegister(valor: Int) {
        var a by remember {
            mutableStateOf(0)


        }
        if (valor == 2 && a == 0) {
            AlertDialogExample(
                dismiss = {
                    _goToNext.value = 0
                    a = 1
                },
                confirm = {
                    _goToNext.value = 0
                    a = 1
                },
                textTitle = "Error",
                textBody = "Error en el registro:  No se ha podido Registrar el usuario, por un error desconocido, comprueba que este correo no este ya registrado"
            )
        } else if (valor == 1) {
            AlertDialogExample(
                dismiss = { _goToNext.value = 0
                          a = 1},
                confirm = { /*TODO*/ },
                textTitle = "Registro Completado",
                textBody = "Tu usuario ha sido registrado"
            )

        }
        else {a = 0}


    }


}








