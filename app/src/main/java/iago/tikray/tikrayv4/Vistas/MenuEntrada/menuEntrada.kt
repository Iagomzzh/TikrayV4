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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import iago.tikray.tikrayv4.R


@Composable
fun MenuEntrada(

    menuEntradaViewModel: MenuEntradaViewModel,
    navigationController: NavHostController


    ) {




    



    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
    ) {


        menuEntradaViewModel.obtenerSizeDeDocument()



        Log.d(
            "lista de empleados por nombre",
            "${menuEntradaViewModel.nombreCompletoDB.value} :::${menuEntradaViewModel.nombreCompletoDB}"
        )



        menuEntradaViewModel.numeroDeEmpleados.value?.let {
            items(it) {
                EmpleadosReciclyerView(
                    menuEntradaViewModel = menuEntradaViewModel, it

                )
            }
        }





        Log.d("s", "d")

        Log.d("numero de empleados: , ", "${menuEntradaViewModel.numeroDeEmpleados.value}")
    }

}


@Composable
fun EmpleadosReciclyerView(menuEntradaViewModel: MenuEntradaViewModel, i: Int) {
    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp, top = 6.dp, bottom = 6.dp)
            .height(150.dp)


    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.tikrayColor1))
        ) {

            val sumarParaAvzanzar: Int by menuEntradaViewModel.sumarParaAvanzar.observeAsState(
                initial = 0
            )
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
                    .clickable { menuEntradaViewModel.logOut(NavHostController(context)) }
                    .constrainAs(imagen) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                    })


            val nombreCompletoDB = menuEntradaViewModel.nombreCompletoDB.value
            val addressDB = menuEntradaViewModel.address.value
            val telephoneDB = menuEntradaViewModel.telefonoDB.value
            val horarioInicioDB = menuEntradaViewModel.horaInicioDB.value
            val horarioFinalDB = menuEntradaViewModel.horaFinalDB.value
            val puestoDB = menuEntradaViewModel.puestoTrabajoDB.value





            Text(
                text = "${nombreCompletoDB?.get(i)}",
                style = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 19.sp),
                modifier = Modifier.constrainAs(nombre) {
                    start.linkTo(puesto.end, margin = 9.dp)
                    top.linkTo(parent.top, margin = 2.dp)


                })
            Log.d("CHIVATO INDICE", " $sumarParaAvzanzar")


            Text(
                text = "${addressDB?.get(i)}",
                color = Color.LightGray,
                modifier = Modifier.constrainAs(correo) {
                    start.linkTo(imagen.end, margin = 10.dp)
                    top.linkTo(nombre.bottom, margin = 25.dp)
                }
            )

            Text(
                text = "${telephoneDB?.get(i)}",
                color = Color.LightGray,
                modifier = Modifier.constrainAs(telefono) {
                    start.linkTo(margenMiddle)
                    start.linkTo(correo.start)
                    top.linkTo(correo.bottom, margin = 10.dp)
                }
            )

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
            Text(
                text = "HORARIO",
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.constrainAs(textoHorario) {

                    end.linkTo(parent.end, margin = 60.dp)
                    top.linkTo(telefono.top)

                })

            Text(text = "Inicio: ${horarioInicioDB?.get(i)} Final: ${horarioFinalDB?.get(i)} ",
                style = TextStyle(
                    Color.Gray
                ),
                modifier = Modifier.constrainAs(horario) {
                    end.linkTo(parent.end, margin = 8.dp)
                    top.linkTo(textoHorario.bottom, margin = 20.dp)
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