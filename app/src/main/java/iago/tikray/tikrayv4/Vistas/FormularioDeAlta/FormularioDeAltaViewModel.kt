package iago.tikray.tikrayv4.Vistas.FormularioDeAlta

import android.app.TimePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Navegacion.MainActivity
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.Vistas.Register.Colorss1
import iago.tikray.tikrayv4.Vistas.Register.Colorss2
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class FormularioDeAltaViewModel @Inject constructor() : ViewModel() {


    private val _estadoHorario = MutableLiveData<Boolean>()
    val estadoHorario: LiveData<Boolean> = _estadoHorario


    private val _selectedText = MutableLiveData<String>()
    var selectedText: LiveData<String> = _selectedText

    private val _expanded = MutableLiveData<Boolean>()
    val expanded: LiveData<Boolean> = _expanded

    private val _nombreCompleto = MutableLiveData<String>()
    val nombreCompleto: LiveData<String> = _nombreCompleto

    private val _numTelefono = MutableLiveData<String>()
    val numTelefono: LiveData<String> = _numTelefono


    fun cambiar(horario: Boolean) {
        _estadoHorario.value = horario

    }

    fun cambiarTexto(texto: String) {
        _selectedText.value = texto
    }

    fun cambiarExpanded(expandir: Boolean) {
        _expanded.value = expandir

    }

    private val _horaSelect = MutableLiveData<Int>()
    val horaSelect: LiveData<Int> = _horaSelect


    private val _minSelect = MutableLiveData<Int>()
    val minSelect: LiveData<Int> = _minSelect


    private val _horaSelect1 = MutableLiveData<Int>()
    val horaSelect1: LiveData<Int> = _horaSelect1


    private val _minSelect1 = MutableLiveData<Int>()
    val minSelect1: LiveData<Int> = _minSelect1

    private val _formularioDeAltaCompletado = MutableLiveData<Boolean>()
    val formulariodeAltaCompletado:LiveData<Boolean> = _formularioDeAltaCompletado


    fun cambiarNombrecompleto(nombre: String) {
        _nombreCompleto.value = nombre
    }

    fun cambiarTelefono(telefono: String) {
        _numTelefono.value = telefono
    }


    fun mostrarTimePicker(context: Context) {
        val timePickerDialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                _horaSelect.value = hourOfDay
                _minSelect.value = minute
            },
            _horaSelect.value ?: 0,
            _minSelect.value ?: 0,
            true
        )
        timePickerDialog.show()

    }


    fun mostrarTimePicker1(context: Context) {
        val timePickerDialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                _horaSelect1.value = hourOfDay
                _minSelect1.value = minute
            },
            _horaSelect1.value ?: 0,
            _minSelect1.value ?: 0,
            true
        )
        timePickerDialog.show()

    }


    //Función para hacer el menu desplegable

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropDown() {

        val expanded: Boolean by _expanded.observeAsState(initial = false)


        val listaCargos =
            listOf("Administrativo", "Marketing", "Informatico", "Comerical", "Soporte")

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

    fun enabledOrDisabled(): Boolean {
        return if (_nombreCompleto.value.toString()
                .isNotEmpty() && _numTelefono.value.toString().length == 9 && selectedText.value.toString() != "Puesto de trabajo" && horaSelect.value != null && horaSelect1.value != null

        ) {
            android.util.Log.d("Valor de hora select: ", "${horaSelect.value}")
            true
        } else {
            false
        }


    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun guardarDatos(


    formularioDeAltaViewModel: FormularioDeAltaViewModel,
        firebaseAuth: FirebaseAuth,
        navigationController:NavHostController
    ) {

        val db = FirebaseFirestore.getInstance()

        val userEmail = firebaseAuth.currentUser?.email
        val nombreCompleto = formularioDeAltaViewModel.nombreCompleto.value
        val numTelefono = formularioDeAltaViewModel.numTelefono.value
        val selectedText = formularioDeAltaViewModel.selectedText.value

        val horaInicio = formularioDeAltaViewModel.horaSelect.value?.let { hour ->
            formularioDeAltaViewModel.minSelect.value?.let { minute ->
                LocalTime.of(hour, minute)
            }
        }

        val horaFinal = formularioDeAltaViewModel.horaSelect1.value?.let { hour ->
            formularioDeAltaViewModel.minSelect1.value?.let { minute ->
                LocalTime.of(hour, minute)
            }
        }

        if (userEmail != null) {
            db.collection("usersInformation").document(userEmail).set(
                hashMapOf(
                    "NombreCompleto" to nombreCompleto,
                    "address" to userEmail,
                    "telefono" to numTelefono,
                    "horaInicio" to horaInicio.toString(),
                    "horaFinal" to horaFinal.toString(),
                    "PuestoTrabajo" to selectedText

                )
            )

            navigationController.navigate(Ruta.MenuEntrada.route)
        }


    }


















}


//    @Composable
//    fun MiMenuDesplegable() {
//
//        val selectedTextValue by selectedText.observeAsState(initial = "")
//        val expandedValue by expanded.observeAsState(initial = false)
//        Column(modifier = Modifier.padding(20.dp)) {
//
//            OutlinedTextField(value = selectedTextValue, onValueChange = { cambiarTexto(it) },
//                enabled = false,
//                readOnly = true,
//                modifier = Modifier
//                    .clickable { cambiarExpanded(true) }
//                    .fillMaxWidth()
//
//            )
//            DropdownMenu(
//                expanded = expandedValue,
//                onDismissRequest = { cambiarExpanded(false) },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                opcionesMenuHorario.forEach { opcion ->
//                    DropdownMenuItem(onClick = {
//                        cambiarExpanded(false)
//                        cambiarTexto(opcion)
//                    }) {
//                        Text(opcion)
//                    }
//                }
//            }
//        }
//    }
//}


