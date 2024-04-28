
package iago.tikray.tikrayv4.Vistas.Register

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.AlertDialogExample

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    //INSTANCIAS

    //Instancia Firebase DataBase
    //private val db = FirebaseFirestore.getInstance()

    //instancia firebase Auth
    private val auth = FirebaseAuth.getInstance()


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //VARIABLES PRO

    //Nombre
    private val _nombre = MutableLiveData<String>()
    val name: LiveData<String> = _nombre


    //Correo
    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo


    //Contrasenya
    private val _contrasenya = MutableLiveData<String>()
    val contrasenya: LiveData<String> = _contrasenya


    //Confirmar contrasenya
    private val _confirmarContrasenya = MutableLiveData<String>()
    val confirmarContrasenya: LiveData<String> = _confirmarContrasenya


    //Estado icono
    private val _estadoIcono = MutableLiveData<Boolean>()
    val estadoIcono: LiveData<Boolean> = _estadoIcono



    //Información del registro, el valor es un 1 si el registro ha sido satisfactorio, si ha sido erroneo es 2
    private val _goToNext = MutableLiveData<Int>()
    val goToNext: LiveData<Int> = _goToNext

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //FUNCIÓN PARA CAMBIAR LOS DATOS Y ACTUALIZARLOS EN EL VIEW MODEL, ADEMAS RETORNA TRUE SI LOS CAMPOS NO ESTAN VACIOS, LAS CONTRASEÑAS COINCIDEN Y EL LENGTH ES SUPERIOR A 6, SI ALGUNO DE ESTAS COMPROBACIONES NO SE CUMPLE RETORNA FALSE
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    // FUNCIÓN PARA RESTAURAR Y BORRAR LOS DATOS DE LOS TEXTFIELDS  DEL REGISTER, DE MOMENTO LO USO PARA CUANDO EL REGISTRO DA UN ERROR
    private fun restaurarDatosRegister() {
        _nombre.value = ""
        _correo.value = ""
        _contrasenya.value = ""
        _confirmarContrasenya.value = ""
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //FUNCION DE REGISTRO ADAPTADA CON ALERTIDALOGS, Y CON GESTION DE EXCEPCIONES

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


                        Log.d(
                            "Chivato Registro",
                            "Error creating user:  ${_goToNext.value}"
                        )
                    }

                } catch (e: Exception) {
                    _goToNext.value = 2
                    Log.d(
                        "Chivato Registro",
                        "Error creating user: $e ${_goToNext.value}"
                    )


                }


            }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // FUNCIÓN PARA QUE DEPENDIENDO DE LA LONGITUD DE LA CONTRASEÑA EL PROGRESS BAR QUE MARCA LA DIFICULTAT DE LA CONSTRASEÑA SE AJUSTE

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // FUNCIÓN PARA MOSTRAR LA PROGRESS BAR O NO, JUGANDO CON EL COLOR TRANSPARENTE, SI HAY 0 CARACTERES NO SE MOSTRARA, A LA QUE HAYA MÁS DE 1 SE MOSTRARA

    fun mostrarBarraProgressBar(contrasenya: String): Color {
        val numeroLenght = contrasenya.length
        return if (numeroLenght == 0) {
            Color.White
        } else {
            Color.White
        }


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // DEPENDIENDO DE LA LONGITUD DE LA CONSTRASEÑA LA PROGRESS EL COLOR TRACKER SE MOSTRARA ROJO, AMARILLO, VERDE
    fun colorProgressBar(contrasenya: String): Color {
        return when (contrasenya.length) {
            in 1..6 -> Color.Red
            in 7..10 -> Color.Yellow
            else -> Color.Green

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // FUNCIÓN QUE CAMBIARA EL COLOR DEL TEXTO QUE NOTIFICA QUE LAS CONSTRASEÑAS DEBEN DE COINCIDIR, JUGANDO CON EL COLOR TRANSPARENTE PARA PODER HACERLO POSIBLE
    fun contrasenyasCoinciden(contrasenya: String, confirmarContrasenya: String): Color {
        return if (contrasenya.isEmpty() || confirmarContrasenya.isEmpty() || contrasenya == confirmarContrasenya) Color.Transparent else Color.Red

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //FUNCIÓN PARA CAMBIAR EL ICONO PARA CAMBIAR EL ESTADO DE LA CONSTRASEÑA
    fun iconoPassword(iconoEstado: Boolean) {
        _estadoIcono.value = !iconoEstado


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //FUNCIÓN PARA CAMBIAR EL ICONO DEL MOSTRAR CONSTRASEÑA O NO, EN LOS TEXTFIELDS DE PASSWORD Y CONFIRMARPASSWORD
    fun elegirIcono(iconoEstado: Boolean): ImageVector {
        val icono = if (iconoEstado) {
            Icons.Rounded.Visibility
        } else {
            Icons.Rounded.VisibilityOff
        }
        return icono
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //FUNCIÓN PARA QUE SE HAGA EL EFECTO DE LOS ASTERISCOS EN LOS TEXTFIELDS DE LA CONSTRASEÑA Y CONFIRMAR CONSTRASEÑA
    fun mostrarPassword(estado: Boolean): VisualTransformation {
        return if (!estado) {
            PasswordVisualTransformation()

        } else {

            VisualTransformation.None


        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    //FUNCIÓN PARA CREAR LOS ALERTDIALOGS PARA CUANDO SE PULSE EL BOTÓN DE REGISTER NOTIFIQUE AL USUARIO COMO HA IDO, Y PARA CONTROLAR SI SE MUESTRAN O NO LOS ALERTSDIALOGS
    @Composable
    fun DialogoRegister(valor: Int) {
        var a by remember { mutableIntStateOf(0) }

        if (valor == 2 && a == 0) {
            AlertDialogExample(
                dismiss = {
                    _goToNext.value = 0
                    a = 1
                    restaurarDatosRegister()
                },
                confirm = {
                    _goToNext.value = 0
                    a = 1
                    restaurarDatosRegister()
                },
                textTitle = "Error",
                textBody = "Error en el registro:  No se ha podido Registrar el usuario, por un error desconocido, comprueba que este correo no este ya registrado"
            )

        } else if (valor == 1) {
            AlertDialogExample(
                dismiss = {
                    _goToNext.value = 0
                    a = 1
                    restaurarDatosRegister()

                },
                confirm = { _goToNext.value = 0
                    a = 1
                          restaurarDatosRegister()},
                textTitle = "Registro Completado",
                textBody = "Tu usuario ha sido registrado"

            )



        } else {
            a = 0
        }


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}








