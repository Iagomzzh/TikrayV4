@file:Suppress("NAME_SHADOWING")

package iago.tikray.tikrayv4.Vistas.FormularioDeAlta

import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.auth.FirebaseAuth
import iago.tikray.tikrayv4.AlertDialogExample
import iago.tikray.tikrayv4.Navegacion.MainActivity
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.Register.Colorss
import iago.tikray.tikrayv4.Vistas.Register.Colorss1
import iago.tikray.tikrayv4.Vistas.Register.colorsButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch




@OptIn(ExperimentalPermissionsApi::class)

//fun CameraPreview() {
  //  val context = LocalContext.current
    //val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    //val permissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    //val imageCapture = remember { ImageCapture.Builder().build() }

    //val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

  /*  AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val cameraView = CameraView(context)
            cameraView.bindToLifecycle(
                lifecycleOwner = LocalLifecycleOwner.current,
                cameraSelector = cameraSelector,
                imageCapture = imageCapture
            )
            cameraView
        }
    )

    // Handle permission request and capture image
    if (permissionState.hasPermission) {
        // Capture image logic here
    } else {
        // Request permission
        LaunchedEffect(permissionState) {
            permissionState.launchPermissionRequest()
        }
    }
}
*/
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Formulario(formularioDeAltaViewModel: FormularioDeAltaViewModel, navHostController: NavHostController) {





    val estadoPermiso = formularioDeAltaViewModel.estadoPermiso.observeAsState(initial = false)
    val estadoAlertDialog = formularioDeAltaViewModel.mostrarAlertDialog.observeAsState(initial = false)


    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (estadoPermiso.value ) {
            formularioDeAltaViewModel.cambiarEstadoPermiso(granted)
            Log.d("Estado", "El permiso esta: $granted, ${estadoPermiso.value}")
            formularioDeAltaViewModel.cambiarMostrarAlertDialog(false)




        } else {
            formularioDeAltaViewModel.cambiarEstadoPermiso(granted)
            formularioDeAltaViewModel.cambiarMostrarAlertDialog(true)
            Log.d("Estado", "El permiso esta: $granted, ${formularioDeAltaViewModel.mostrarAlertDialog.value}")







        }


    }

    if (estadoAlertDialog.value)
        AlertDialogExample(
            dismiss = { formularioDeAltaViewModel.cambiarMostrarAlertDialog(false) },
            confirm = {  formularioDeAltaViewModel.cambiarMostrarAlertDialog(false)},
            textTitle = "No has concedido permisos",
            textBody = "Para que puedas usar todas las funciones, sera necesario que des permisos para que podamos usar la camara de tu dispositivo"
        )









    val showLoading = remember { mutableStateOf(false) }

    //Instancias de variables
    val hora: Int by formularioDeAltaViewModel.horaSelect.observeAsState(initial = 0)
    val minutos: Int by formularioDeAltaViewModel.minSelect.observeAsState(initial = 0)

    val hora1: Int by formularioDeAltaViewModel.horaSelect1.observeAsState(initial = 0)
    val minutos1: Int by formularioDeAltaViewModel.minSelect1.observeAsState(initial = 0)


    val nombreCompleto:String by formularioDeAltaViewModel.nombreCompleto.observeAsState(initial = "")
    val numTelefono:String by formularioDeAltaViewModel.numTelefono.observeAsState(initial = "")


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.tikrayColor1))
    ) {

        val (imagen, nombre, telefono, circularCharge, textoExplicativo, inicioHorario, inicioTexto, finalTexto, finalHorario, puestoTrabajo, botonContinue) = createRefs()
        val topMargen = createGuidelineFromTop(0.1f)


        Image(painter = painterResource(id = R.drawable.add_profile_image),
            contentDescription = " ",
            Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(200.dp))
                .background(
                    Color.White
                )
                .clickable { permissionLauncher.launch("android.permission.CAMERA") }
                .constrainAs(imagen) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topMargen)


                })

        //TEXTFIELD NOMBRE
        OutlinedTextField(value = nombreCompleto,
            onValueChange = {formularioDeAltaViewModel.cambiarNombrecompleto(it)},
            label = { Text(text = "Nombre Completo") },
            colors = Colorss1(),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.constrainAs(nombre) {

                top.linkTo(imagen.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            })


        OutlinedTextField(value = numTelefono,
            onValueChange = { nuevoTelefono ->
                if (nuevoTelefono.length <= 9) { // Limita a 10 caracteres
                    formularioDeAltaViewModel.cambiarTelefono(nuevoTelefono)
                }
            },
            label = { Text(text = "Teléfono") },
            colors = Colorss1(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone,   ),
            modifier = Modifier.constrainAs(telefono) {

                top.linkTo(nombre.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            })
        Box(Modifier.constrainAs(puestoTrabajo) {
            start.linkTo(parent.start)
            top.linkTo(telefono.bottom, margin = 20.dp)
        }) {
            formularioDeAltaViewModel.DropDown()
        }

        Text(text = "Indica tu horario laboral", fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .constrainAs(textoExplicativo) {

                    top.linkTo(puestoTrabajo.bottom, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)


                }
                .padding(start = 50.dp, end = 50.dp))

        val context = LocalContext.current
        Text(
            text = "Inicio",
            color = Color.White,
            fontSize = 13.sp,
            modifier = Modifier.constrainAs(inicioTexto) {
                start.linkTo(telefono.start)
                end.linkTo(inicioHorario.end)
                top.linkTo(textoExplicativo.bottom, margin = 15.dp)
            })

        Text(
            text = "Final",
            color = Color.White,
            fontSize = 13.sp,
            modifier = Modifier.constrainAs(finalTexto) {
                start.linkTo(finalHorario.start)
                end.linkTo(finalHorario.end)
                top.linkTo(textoExplicativo.bottom, margin = 15.dp)
            })


        OutlinedTextField(value = "$hora : $minutos",
            onValueChange = { },
            colors = Colorss(),
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .size(width = 120.dp, height = 60.dp)
                .clickable { formularioDeAltaViewModel.mostrarTimePicker(context) }
                .constrainAs(inicioHorario) {

                    top.linkTo(inicioTexto.bottom, margin = 10.dp)
                    start.linkTo(telefono.start)
                })

        OutlinedTextField(value = "$hora1 : $minutos1",
            onValueChange = { },
            colors = Colorss(),
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .size(width = 120.dp, height = 60.dp)
                .clickable { formularioDeAltaViewModel.mostrarTimePicker1(context) }
                .constrainAs(finalHorario) {

                    top.linkTo(finalTexto.bottom, margin = 10.dp)
                    end.linkTo(telefono.end)
                })



        Button(onClick = { CoroutineScope(Dispatchers.Main).launch {
            showLoading.value = true
            delay(3000L) // Retraso de 5 segundos
            showLoading.value = false
            formularioDeAltaViewModel.guardarDatos(formularioDeAltaViewModel, FirebaseAuth(), navHostController)
        }       },
            Modifier
                .constrainAs(botonContinue) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(inicioHorario.bottom, margin = 50.dp)

                }
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp),
            enabled = formularioDeAltaViewModel.enabledOrDisabled(),
            colors = colorsButton())
        {
            Text(text = "Continuar")

        }


        if (showLoading.value) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(circularCharge){
                start.linkTo(botonContinue.start)
                end.linkTo(botonContinue.end)
                top.linkTo(botonContinue.bottom, margin = 5.dp)
            }) // Muestra el indicador de progreso cuando showLoading es true
        }




    }


}

fun FirebaseAuth(): FirebaseAuth {
    return FirebaseAuth.getInstance()

}











