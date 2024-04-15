package iago.tikray.tikrayv4.Register

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception


private val auth = FirebaseAuth.getInstance()
private val _estadoRegister = MutableLiveData<Boolean>()
val estadoRegister: LiveData<Boolean> = _estadoRegister


fun register(correo: String, passwd: String) {
    try {
        auth.createUserWithEmailAndPassword(correo, passwd)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _estadoRegister.value = true
                    Log.d("Registro satisfactorio", "El registro se ha completado correctamente")


                } else {
                    _estadoRegister.value = false
                    Log.d("Error", "Error ${task.result}")
                }
            }

    } catch (e: Exception) {

        Log.d("Error", "Error ")
    }
}





