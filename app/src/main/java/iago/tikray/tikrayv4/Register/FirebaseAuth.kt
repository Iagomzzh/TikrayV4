package iago.tikray.tikrayv4.Register

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.SignInMethodQueryResult
import java.lang.Exception


val auth = FirebaseAuth.getInstance()





var estado: Boolean = false

@Composable
fun register(correo: String, passwd: String, ) {

    auth.fetchSignInMethodsForEmail(correo).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val signInMethods = task.result ?: return@addOnCompleteListener

            if (signInMethods.signInMethods?.isEmpty() == true) {
                auth.createUserWithEmailAndPassword(correo, passwd)
                    .addOnCompleteListener { taskk ->
                        if (taskk.isSuccessful) {


                            Log.d("Registro satisfactorio", "El registro se ha completado correctamente")
                        } else {
                            Log.d("Error", "El usuario ya esta registrado")

                        }
                    }
            }
        } else {
            Log.d("Error:", "$task")
        }
    }




}