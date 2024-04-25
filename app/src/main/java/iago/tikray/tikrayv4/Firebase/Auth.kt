package iago.tikray.tikrayv4.Firebase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class AuthService @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    fun isUserLogged():Boolean {
        return getCurrentUser() != null

    }
    private fun getCurrentUser() = firebaseAuth.currentUser




    }


