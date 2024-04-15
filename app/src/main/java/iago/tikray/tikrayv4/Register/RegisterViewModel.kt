@file:Suppress("UNUSED_EXPRESSION")

package iago.tikray.tikrayv4.Register

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RegisterViewModel : ViewModel() {


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
            Color.Transparent
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
        val icono = if (iconoEstado) {Icons.Rounded.Visibility} else{Icons.Rounded.VisibilityOff}
        return icono
    }


    fun mostrarPassword(estado:Boolean): VisualTransformation {
        if (!estado) {
            return PasswordVisualTransformation()

        } else {

            return VisualTransformation.None


        }
    }
    fun registrar(correo:String, contrasenya: String) {
        register(correo, contrasenya)
    }

}


