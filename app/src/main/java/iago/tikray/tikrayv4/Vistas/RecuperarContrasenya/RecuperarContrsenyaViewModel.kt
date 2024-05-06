package iago.tikray.tikrayv4.Vistas.RecuperarContrasenya

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.rpc.context.AttributeContext
import com.google.rpc.context.AttributeContext.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Firebase.AuthService
import javax.inject.Inject

@HiltViewModel
class RecuperarContrsenyaViewModel @Inject constructor(): ViewModel() {

    private val auth = FirebaseAuth.getInstance()


    private val _emailForReset = MutableLiveData<String?>()
    val emailForReset:LiveData<String?> = _emailForReset


    fun actualizarDatos(it:String){

        _emailForReset.value = it
    }

    fun recuperarContrasenya(){
        auth.sendPasswordResetEmail()




    }


}