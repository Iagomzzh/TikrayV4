package iago.tikray.tikrayv4.FormularioDeAlta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Register.Colorss

@Composable
fun Formulario(formularioDeAltaViewModel: FormularioDeAltaViewModel) {


    //Instancias de variables
    val hora: Int by formularioDeAltaViewModel.horaSelect.observeAsState(initial = 0)
    val minutos: Int by formularioDeAltaViewModel.minSelect.observeAsState(initial = 0)

    val hora1: Int by formularioDeAltaViewModel.horaSelect1.observeAsState(initial = 0)
    val minutos1: Int by formularioDeAltaViewModel.minSelect1.observeAsState(initial = 0)


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.tikrayColor1))
    ) {

        val (imagen, nombre, telefono, textoExplicativo, inicioHorario, inicioTexto, finalTexto, finalHorario, puestoTrabajo, botonContinue) = createRefs()
        val topMargen = createGuidelineFromTop(0.1f)


        Image(painter = painterResource(id = R.drawable.add_profile_image),
            contentDescription = " ",
            Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(200.dp))
                .background(
                    Color.White
                )
                .constrainAs(imagen) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topMargen)


                })

        //TEXTFIELD NOMBRE
        OutlinedTextField(value = "",
            onValueChange = {},
            label = { Text(text = "Nombre Completo") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.constrainAs(nombre) {

                top.linkTo(imagen.bottom, margin = 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)


            })


        OutlinedTextField(value = "",
            onValueChange = {},
            label = { Text(text = "Teléfono") },
            colors = Colorss(),
            maxLines = 1,
            singleLine = true,
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



        Button(onClick = {  }, Modifier.constrainAs(botonContinue){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(inicioHorario.bottom, margin = 50.dp)

        }.fillMaxWidth()
            .padding(start = 75.dp, end = 75.dp))
        {
            Text(text = "Continuar")

        }




    }


}

@Preview
@Composable
private fun Preview() {
    Formulario(formularioDeAltaViewModel = FormularioDeAltaViewModel())

}









