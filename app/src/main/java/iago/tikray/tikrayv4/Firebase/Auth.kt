package iago.tikray.tikrayv4.Firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.rpc.context.AttributeContext.Auth
import iago.tikray.tikrayv4.Register.RegisterViewModel
import iago.tikray.tikrayv4.Splash.SplashViewModel
import javax.inject.Inject

class AuthService @Inject constructor(firebaseAuth: FirebaseAuth,) {
    fun isUserLogged():Boolean {
        return getCurrentUser() != null

    }


    private fun getCurrentUser() = isUserLogged()


}