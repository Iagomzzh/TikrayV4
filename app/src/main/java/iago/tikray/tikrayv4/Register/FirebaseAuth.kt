package iago.tikray.tikrayv4.Register

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.SignInMethodQueryResult
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FirebaseAuth @Inject constructor() {
    val auth = FirebaseAuth.getInstance()


    var estado: Boolean = false

   fun register(correo: String, passwd: String): Boolean {
        return auth.createUserWithEmailAndPassword(correo, passwd).isSuccessful
    }
}


