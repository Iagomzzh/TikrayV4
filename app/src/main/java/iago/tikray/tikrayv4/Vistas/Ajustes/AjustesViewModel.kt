package iago.tikray.tikrayv4.Vistas.Ajustes

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Navegacion.Ruta
import javax.inject.Inject


@HiltViewModel
class AjustesViewModel @Inject constructor(): ViewModel(){

    private val auth = FirebaseAuth.getInstance()
    fun logOut(navigation: NavHostController) {

        auth.signOut()
        navigation.navigate(Ruta.PaginaPrincipal.route)

    }
}