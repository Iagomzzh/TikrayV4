package iago.tikray.tikrayv4.MenuEntrada

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class MenuEntradaViewModel @Inject constructor() : ViewModel() {
        private val auth = FirebaseAuth.getInstance()
    fun logOut() {
        auth.signOut()
    }



}