package iago.tikray.tikrayv4.Register
import com.google.firebase.auth.FirebaseAuth

import javax.inject.Inject

class FirebaseAuth @Inject constructor() {
    val auth = FirebaseAuth.getInstance()

    // FUNCIÓN MAIN PARA EL REGISTRO, ESTA FUNCIÓN ES LLAMADA EN EL REGISTERVIEWMODEL PARA AÑADIRLE FUNCIONES

    fun register(correo: String, passwd: String): Boolean {
        return auth.createUserWithEmailAndPassword(correo, passwd).isSuccessful
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}


