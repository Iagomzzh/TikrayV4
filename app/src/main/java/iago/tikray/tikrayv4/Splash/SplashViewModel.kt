package iago.tikray.tikrayv4.Splash

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
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
            navigationController.navigate(Ruta.PaginaPrincipal.route)



        }
        else{
            navigationController.navigate(Ruta.PaginaPrincipal.route)


        }
    }
}