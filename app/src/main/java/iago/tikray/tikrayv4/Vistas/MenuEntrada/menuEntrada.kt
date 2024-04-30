package iago.tikray.tikrayv4.Vistas.MenuEntrada

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun MenuEntrada(

    menuEntradaViewModel: MenuEntradaViewModel,
    navigationController: NavHostController


) {


    LazyColumn(Modifier
            .background(colorResource(id = R.color.black)).fillMaxSize(0.1f)) {

        menuEntradaViewModel.obtenerSizeDeDocument()
        menuEntradaViewModel.numeroDeEmpleados.value?.let {
            items(it) {
                EmpleadosReciclyerView(
                    menuEntradaViewModel, navigationController, it
                )
            }
        }





        Log.d("s", "d")

        Log.d("numero de empleados: , ", "${menuEntradaViewModel.numeroDeEmpleados.value}")
    }

}


@Composable
fun EmpleadosReciclyerView(
    menuEntradaViewModel: MenuEntradaViewModel,
    navigationController: NavHostController,
    i: Int
) {
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp, top = 6.dp, bottom = 6.dp)
            .height(150.dp)
            .clickable { navigationController.navigate(Ruta.MasInformacion.route)

                menuEntradaViewModel.cambiarDatos(
                    menuEntradaViewModel.nombreCompletoDB.value?.get(i) ?: "null",
                    menuEntradaViewModel.puestoTrabajoDB.value?.get(i) ?: "null",
                    menuEntradaViewModel.address.value?.get(i) ?: "null",
                    menuEntradaViewModel.horaInicioDB.value?.get(i) ?: "null",
                    menuEntradaViewModel.horaFinalDB.value?.get(i) ?: "null",
                    menuEntradaViewModel.telefonoDB.value?.get(i) ?: "null"
                )}


    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.tikrayColor1))
        ) {
            menuEntradaViewModel.obtenerSizeDeDocument()


            val (imagen, nombre, telefono, puesto, estado, correo, textoHorario, horario) = createRefs()
            val margenMiddle = createGuidelineFromStart(0.5f)

            Image(painter = painterResource(id = R.drawable.add_profile_image),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .clip(shape = RoundedCornerShape(50.dp))
                    .background(
                        Color.White
                    )
                    .clickable { menuEntradaViewModel.logOut(NavHostController(context))
                        }
                    .constrainAs(imagen) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                    })

                    val a = i


            val nombreCompletoDB = menuEntradaViewModel.nombreCompletoDB.value
            val horarioInicioDB = menuEntradaViewModel.horaInicioDB.value
            val horarioFinalDB = menuEntradaViewModel.horaFinalDB.value
            val puestoDB = menuEntradaViewModel.puestoTrabajoDB.value









            Text(
                text = "${nombreCompletoDB?.get(i)}",
                color = Color.White, fontSize = 19.sp,
                modifier = Modifier.constrainAs(nombre) {
                    start.linkTo(imagen.end, margin = 20.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)


                })

            Text(text = "Activo", color = Color.Green, modifier = Modifier.constrainAs(estado) {
                start.linkTo(imagen.start)
                end.linkTo(imagen.end)
                top.linkTo(imagen.bottom)
                bottom.linkTo(parent.bottom)
            }
            )
            Text(
                text = "${puestoDB?.get(i)}",
                color = Color.Gray,
                modifier = Modifier.constrainAs(puesto) {
                    start.linkTo(imagen.start)
                    top.linkTo(parent.top, margin = 7.dp)

                }


            )




        }


    }
}


//@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
//    MenuEntrada( rememberNavController())

}