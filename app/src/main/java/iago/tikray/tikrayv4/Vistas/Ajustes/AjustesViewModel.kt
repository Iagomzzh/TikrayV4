package iago.tikray.tikrayv4.Vistas.Ajustes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.Vistas.Register.Colorss1
import iago.tikray.tikrayv4.Vistas.Register.Colorss2
import javax.inject.Inject


@HiltViewModel
class AjustesViewModel @Inject constructor(): ViewModel(){

    private val auth = FirebaseAuth.getInstance()

    private val _userMail = MutableLiveData<String>()
    val userMail: LiveData<String> = _userMail

    private val _expanded = MutableLiveData<Boolean>()
    val expanded: LiveData<Boolean> = _expanded

    private val _selectedText = MutableLiveData<String>()
    var selectedText: LiveData<String> = _selectedText

    fun cambiarExpanded(expandir: Boolean) {
        _expanded.value = expandir

    }

    fun userMail(){
        _userMail.value = auth.currentUser?.email
    }
    fun logOut(navigation: NavHostController) {

        auth.signOut()
        navigation.navigate(Ruta.PaginaPrincipal.route)

    }




    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropDown() {

        val expanded: Boolean by _expanded.observeAsState(initial = false)


        val listaCargos =
            listOf("Activo", "No molestar", "Reunido", "Vacaciones", "Inactivo")

        if (_selectedText.value == null) {
            _selectedText.value = "Puesto de trabajo"
        }


        val seleccion = if (_selectedText.value.toString() == "Puesto de trabajo") {
            Colorss2()

        } else {
            Colorss1()
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ExposedDropdownMenuBox(

                expanded = expanded,
                onExpandedChange = { cambiarExpanded(!expanded) }) {
                OutlinedTextField(
                    value = _selectedText.value.toString(), onValueChange = {},
                    colors = seleccion,
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { cambiarExpanded(false) }) {
                    listaCargos.forEachIndexed { index, text ->
                        DropdownMenuItem(text = { Text(text = text) }, onClick = {
                            _selectedText.value = listaCargos[index]
                            cambiarExpanded(false)
                        }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)

                    }

                }

            }


        }


    }
}