package iago.tikray.tikrayv4.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Navegacion.Ruta
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    // Navegación




    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password


    fun changeDatos(correo: String, contrasenya: String): Boolean {
        _email.value = correo
        _password.value = contrasenya

        return if (_email.value!!.isNotEmpty() && _password.value!!.isNotEmpty() && _password.value!!.length > 6 && esCorreoValido(_email.value!!))
        { true }
        else
        { false }
    }

    fun esCorreoValido(correo: String): Boolean {
        return '@' in correo
    }
}


fun navegarPasswdOlvidada(navigationController: NavController): () -> Unit {
    val nav: Any = navigationController.navigate(Ruta.PaginaRecuperarContrasenya.route)
    return { nav }
}