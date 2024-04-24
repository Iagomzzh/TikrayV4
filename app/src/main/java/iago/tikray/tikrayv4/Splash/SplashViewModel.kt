package iago.tikray.tikrayv4.Splash

import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Firebase.AuthService
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authService: AuthService) {

    private fun isUSerLogged(): Boolean {
        return authService.isUserLogged()
    }


    fun checkDestination() {
        val isUserLogged:Boolean = isUSerLogged()
        if(isUserLogged) {

        }
    }
}