package iago.tikray.tikrayv4.Vistas.Login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.AlertDialogExample
import iago.tikray.tikrayv4.Navegacion.Ruta
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()


    // VARIABLES PARA EL LOGIN
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password


    //Variable para poder tener constancia de si el registro ha sido correcto o no
    private val _goToNextLogin = MutableLiveData<Int>()
    val goToNextLogin: LiveData<Int> = _goToNextLogin

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // CAMBIAR LOS DATOS Y DE PASO COMPROBAR SI LOS CAMPOS SIGEN LAS NORMAS
    fun changeDatos(correo: String, contrasenya: String): Boolean {
        _email.value = correo
        _password.value = contrasenya

        return if (_email.value!!.isNotEmpty() && _password.value!!.isNotEmpty() && _password.value!!.length > 6 && esCorreoValido(
                _email.value!!
            )
        ) {
            true
        } else {
            false
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun esCorreoValido(correo: String): Boolean {
        return '@' in correo
    }

    // FUNCION PARA HACER EL LOGIN

    fun login(navigationController: NavController) {
        auth.signInWithEmailAndPassword(_email.value.toString(), _password.value.toString())

            .addOnCompleteListener { task ->
                try {
                    if (task.isSuccessful) {
                        _goToNextLogin.value = 1
                        navigationController.navigate(Ruta.MenuEntrada.route)

                        Log.d(
                            "Chivato Login",
                            "Usuario Logeado correctamente ${task.result} ${_goToNextLogin.value}"
                        )
                    } else {
                        _goToNextLogin.value = 2
                        Log.d(
                            "Chivato Login",
                            "Error en el registro ${task.result}, ${_goToNextLogin.value}"
                        )
                    }


                } catch (e: Exception) {
                    _goToNextLogin.value = 2
                    Log.d(
                        "Chivato Login",
                        "Error en el Login: $e ${_goToNextLogin.value}"
                    )


                }

            }
    }


    //FUNCIÓN PARA CREAR LOS ALERTDIALOGS PARA CUANDO SE PULSE EL BOTÓN DE REGISTER NOTIFIQUE AL USUARIO COMO HA IDO, Y PARA CONTROLAR SI SE MUESTRAN O NO LOS ALERTSDIALOGS
    @Composable
    fun DialogoLogin(valor: Int) {
        var a by remember { mutableIntStateOf(0) }

        if (valor == 2 && a == 0) {
            AlertDialogExample(
                dismiss = {
                    _goToNextLogin.value = 0
                    a = 1
                    restaurarDatosLogin()
                },
                confirm = {
                    _goToNextLogin.value = 0
                    a = 1
                    restaurarDatosLogin()
                },
                textTitle = "Error",
                textBody = "Error en el Login:  No se ha podido Iniciar sesion con este usuario, por un error desconocido, comprueba que este correo este registrado"
            )

        } else if (valor == 1) {
            AlertDialogExample(
                dismiss = {
                    _goToNextLogin.value = 0
                    a = 1

                },
                confirm = {
                    _goToNextLogin.value = 0
                    a = 1
                },
                textTitle = "Login Completado",
                textBody = "Tu usuario ha sido Logeado correctamente"
            )


        } else {
            a = 0
        }

    }

    private fun restaurarDatosLogin() {
        _email.value = ""
        _password.value = ""
    }



}

// NAVEGACION HACIA "HAS OLVIDADO LA CONTRASENYA
fun navegarPasswdOlvidada(navigationController: NavController): () -> Unit {
    val nav: Any = navigationController.navigate(Ruta.PaginaRecuperarContrasenya.route)
    return { nav }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


