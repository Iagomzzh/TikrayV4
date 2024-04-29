package iago.tikray.tikrayv4.Vistas.InformacionCompleta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.FormularioDeAlta.FormularioDeAltaViewModel
import iago.tikray.tikrayv4.Vistas.MenuEntrada.MenuEntradaViewModel


@Composable
fun InformacionEnGrande(menuEntradaViewModel: MenuEntradaViewModel) {


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.tikrayColor1))
    ) {
        val (nombreCompleto, imagen, puesto, estado, horario, telefono, correo) = createRefs()
        val topMargin = createGuidelineFromTop(0.1f)
        val topMargin1 = createGuidelineFromTop(0.50f)
        val startMargin = createGuidelineFromStart(0.1f)
        Text(
            text = "${menuEntradaViewModel.puestoTrabajoDBS.value}",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(nombreCompleto) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(topMargin)
            })

        Image(
            painter = painterResource(id = R.drawable.add_profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(145.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .background(
                    Color.White,
                )
                .constrainAs(imagen) {
                    top.linkTo(nombreCompleto.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )


        Text(text = "${menuEntradaViewModel.nombreCompletoDBS.value}", color = Color.White, fontSize = 18.sp, modifier = Modifier.constrainAs(puesto){

            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(imagen.bottom, margin = 10.dp)

        })

        Text(text = "Correo: ${menuEntradaViewModel.addressS.value}", color = Color.White, modifier = Modifier.constrainAs(correo){
            start.linkTo(startMargin)
            bottom.linkTo(topMargin1)



        })
        Text(
            text = "Telf: ${menuEntradaViewModel.telefonoDBS.value}",
            color = Color.White,
            modifier = Modifier.constrainAs(telefono) {
                start.linkTo(startMargin)
                top.linkTo(correo.bottom, margin = 20.dp)
            })
        Text(text = "Estado: Activo", color = Color.Green ,modifier = Modifier.constrainAs(estado){
            start.linkTo(startMargin)
            top.linkTo(telefono.bottom, margin = 20.dp)

        })

        Text(text = "Horario:      Inicio: ${menuEntradaViewModel.horaInicioDBS.value}    Final: ${menuEntradaViewModel.horaFinalDBS.value}", color = Color.White, modifier = Modifier.constrainAs(horario){
            start.linkTo(startMargin)
            top.linkTo(estado.bottom, margin = 20.dp)
        })






    }

}


