package iago.tikray.tikrayv4.FormularioDeAlta

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class FormularioDeAltaViewModel @Inject constructor() : ViewModel() {


    private val _estadoHorario = MutableLiveData<Boolean>()
    val estadoHorario: LiveData<Boolean> = _estadoHorario


    private val _selectedText = MutableLiveData<String>()
    val selectedText: LiveData<String> = _selectedText

    private val _expanded = MutableLiveData<Boolean>()
    val expanded: LiveData<Boolean> = _expanded


    val opcionesMenuHorario = listOf(
        "Jornada completa mañana",
        "Jornada completa tarde",
        "Media jornada mañana",
        "Media jornada tarde"
    )


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