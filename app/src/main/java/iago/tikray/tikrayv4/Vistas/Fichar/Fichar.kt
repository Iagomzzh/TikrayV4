package iago.tikray.tikrayv4.Vistas.Fichar

import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.LocationOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.MenuNavegacion

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Fichar(ficharModelView: FicharModelView, navigation: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.tikrayColor1))
    )
    {
        val estadoPermiso by ficharModelView.estadoDelPermisoUbicacion.observeAsState(initial = true)
        val dialogoError by ficharModelView.dialogoDeError.observeAsState(initial = false)
        val obtenerUbi by ficharModelView.obtenerUbi.observeAsState(initial = false)
        val fichajeCorrecto by ficharModelView.fichajeCorrecto.observeAsState(initial = 3)
        val estadoFichajes by ficharModelView.estadoFichaje.observeAsState()



        var icono = Icons.Rounded.LocationOff


        var color = Color.White

        if ( fichajeCorrecto == 2){


            icono = Icons.Rounded.Error
            color = Color.Red


        }

        else if (fichajeCorrecto == 1){
            icono = Icons.Rounded.Check
            color = Color.Green



        }
        else if (fichajeCorrecto == 0){
            color = Color.Transparent



        }

        var ag by remember {
            mutableStateOf(false)
        }


        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (estadoPermiso) {
                ficharModelView.cambiarEstadoDelPermiso(granted)
                Log.d("Estado", "El permiso esta: $granted, $estadoPermiso")
                if (ag) {
                    ficharModelView.cambiarObtenerUbi(true)
                }


            } else {
                ficharModelView.cambiarEstadoDelPermiso(granted)
                Log.d("Estado", "El permiso esta: $granted")
                ficharModelView.cambiarObtenerUbi(false)


            }


        }
        val context = LocalContext.current

        if (obtenerUbi) {
            LaunchedEffect(key1 = ficharModelView) {

                ficharModelView.funcionEnteraParaLaUbicacion(context)
                ficharModelView.cambiarObtenerUbi(false)


            }
        }




        val (menuNavegacion, botonFicharEntrada, botonFicharSalida, iconoEstado, loading, estadoFichaje) = createRefs()

        Button(onClick = {
            permissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")
            permissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
            ficharModelView.cambiarEntradaOSalida("entrada")
            ag = true


        }, modifier = Modifier.constrainAs(botonFicharEntrada) {

            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }) {
            Text(text = "Fichar Entrada")


        }

        Icon(imageVector = icono, contentDescription = null, tint = color, modifier = Modifier
            .size(45.dp)
            .constrainAs(iconoEstado) {
                start.linkTo(botonFicharEntrada.end)
                end.linkTo(parent.end)
                top.linkTo(botonFicharEntrada.bottom)
                bottom.linkTo(botonFicharSalida.top)
            })

        if (fichajeCorrecto == 0){
            CircularProgressIndicator(modifier = Modifier.constrainAs(loading){
                start.linkTo(botonFicharEntrada.end)
                end.linkTo(parent.end)
                top.linkTo(botonFicharEntrada.bottom)
                bottom.linkTo(botonFicharSalida.top)

            })
        }


        Button(onClick = {
            permissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")
            permissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
            ficharModelView.cambiarEntradaOSalida("salida")
            ag = true
        }, modifier = Modifier.constrainAs(botonFicharSalida) {

            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(botonFicharEntrada.bottom, margin = 10.dp)

        }) {
            Text(text = "Fichar Salida")

        }

        ficharModelView.Alpulsar(estadoPermiso = estadoPermiso, dialogoDeError = dialogoError)

        Text(
            text = estadoFichajes.toString(),
            color = Color.White,
            modifier = Modifier.constrainAs(estadoFichaje) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            })





        Box(modifier = Modifier.constrainAs(menuNavegacion) {
            bottom.linkTo(parent.bottom)
        }) {
            MenuNavegacion(navigation)


        }


    }


}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Preview() {
    Fichar(FicharModelView(), rememberNavController())

}