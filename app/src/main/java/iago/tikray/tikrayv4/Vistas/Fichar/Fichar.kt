package iago.tikray.tikrayv4.Vistas.Fichar

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.AlertDialogExample
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.MenuNavegacion
import kotlinx.coroutines.CoroutineScope

@Composable
fun Fichar(ficharModelView: FicharModelView, navigation:NavHostController) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))){
        val estadoPermiso by ficharModelView.estadoDelPermisoUbicacion.observeAsState(initial = true)
        val dialogoError by ficharModelView.dialogoDeError.observeAsState(initial = false)
        val obtenerUbi by ficharModelView.obtenerUbi.observeAsState(initial = false)


        val permissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            granted -> if (estadoPermiso){
                ficharModelView.cambiarEstadoDelPermiso(granted)
                Log.d("Estado", "El permiso esta: $granted, $estadoPermiso" )
                ficharModelView.cambiarObtenerUbi(true)




        }
            else{
            ficharModelView.cambiarEstadoDelPermiso(granted)
            Log.d("Estado", "El permiso esta: $granted")
            ficharModelView.cambiarObtenerUbi(false)



        }




        }
        val context = LocalContext.current

        if (obtenerUbi){
            LaunchedEffect(key1 = ficharModelView) {
                ficharModelView.funcionEnteraParaLaUbicacion(context)
                ficharModelView.cambiarObtenerUbi(false)



            }
        }












        val (menuNavegacion, botonFichar) = createRefs()

        Button(onClick = {permissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")
            permissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")







            }, modifier = Modifier.constrainAs(botonFichar){

            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(menuNavegacion.top)
        } ) {
            Text(text = "Fichar")

        }


        ficharModelView.Alpulsar(estadoPermiso = estadoPermiso , dialogoDeError = dialogoError )




        Box(modifier = Modifier.constrainAs(menuNavegacion){
            bottom.linkTo(parent.bottom)
        }) {
            MenuNavegacion(navigation)


        }




    }






}











@Preview
@Composable
private fun Preview() {
    Fichar(FicharModelView(), rememberNavController())

}