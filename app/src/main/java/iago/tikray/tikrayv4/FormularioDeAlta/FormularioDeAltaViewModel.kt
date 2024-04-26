package iago.tikray.tikrayv4.FormularioDeAlta

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iago.tikray.tikrayv4.Register.Colorss
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


    private val _horaSelect1 = MutableLiveData<Int>()
    val horaSelect1: LiveData<Int> = _horaSelect1


    private val _minSelect1 = MutableLiveData<Int>()
    val minSelect1: LiveData<Int> = _minSelect1

    private val _selected = MutableLiveData<List<String>>()
    val selected: LiveData<List<String>> = _selected


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
            listOf("puesto de trabajo", "Administrativo", "Marketing", "Informatico", "Comerical", "Soporte")

        var selectedText by remember { mutableStateOf(listaCargos[0]) }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ExposedDropdownMenuBox(

                expanded = expanded,
                onExpandedChange = { cambiarExpanded(!expanded) }) {
                OutlinedTextField(value = selectedText, onValueChange = {},
                    colors = Colorss(),
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded,)})

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { cambiarExpanded(false) }) {
                    listaCargos.forEachIndexed { index, text ->
                        DropdownMenuItem(text = { Text(text = text) }, onClick = {
                            selectedText = listaCargos[index]
                            cambiarExpanded(false)
                        }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding)

                    }

                }

            }


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