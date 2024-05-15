package iago.tikray.tikrayv4.Vistas.Splash

import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Firebase.AuthService
import iago.tikray.tikrayv4.Navegacion.Ruta
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authService: AuthService): ViewModel() {

    private fun isUSerLogged(): Boolean {
        return authService.isUserLogged()
    }


    fun checkDestination(navigationController: NavHostController) {
        val isUserLogged:Boolean = isUSerLogged()
        if(isUserLogged) {

            navigationController.navigate(Ruta.MenuEntrada.route)
            //temporal
            //navigationController.navigate(Ruta.FormularioDeAlta.route)






        }
        else{
            navigationController.navigate(Ruta.PaginaPrincipal.route)


        }
    }





}