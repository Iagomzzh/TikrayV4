package iago.tikray.tikrayv4.Register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.SignInMethodQueryResult
import java.lang.Exception


val auth = FirebaseAuth.getInstance()
private val _estadoRegister = MutableLiveData<Boolean>()
val estadoRegister: LiveData<Boolean> = _estadoRegister


fun register(correo: String, passwd: String) {
    auth.fetchSignInMethodsForEmail(correo).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val signInMethods = task.result ?: return@addOnCompleteListener
            if (signInMethods.signInMethods?.isEmpty() == true) {
                auth.createUserWithEmailAndPassword(correo, passwd)
                    .addOnCompleteListener { taskk ->
                        if (taskk.isSuccessful) {
                            _estadoRegister.value = true
                            Log.d("Registro satisfactorio", "El registro se ha completado correctamente")
                        } else {
                            _estadoRegister.value = false
                            Log.d("Error", "El usuario ya esta registrado")

                        }
                    }
            }
        } else {
            Log.d("Error:", "$task")
        }
    }




}