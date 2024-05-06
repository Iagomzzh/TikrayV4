package iago.tikray.tikrayv4.Vistas.RecuperarContrasenya

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.Register.Colorss
import iago.tikray.tikrayv4.Vistas.Register.colorsButton

@Composable
fun PaginaContrasenyaOlvidada(recuperarContrsenyaViewModel: RecuperarContrsenyaViewModel) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))) {
        val campoCorreo = recuperarContrsenyaViewModel.emailForReset.observeAsState(initial = " ")

        val (campoPasswd, texto, codigo, boton) = createRefs()


        OutlinedTextField(
            value = campoCorreo, onValueChange = { }, label = { Text(text = "Correo ") },
            modifier = Modifier.constrainAs(campoPasswd) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }, colors = Colorss()
        )
        Button(onClick = { /*TODO*/ }, colors = colorsButton(), modifier = Modifier.constrainAs(boton){
            top.linkTo(campoPasswd.bottom, margin = 10.dp)
            start.linkTo(campoPasswd.start)
            end.linkTo(campoPasswd.end)
        }) {
            Text(text = "Solicitar")
            
        }

    }




}

@Preview
@Composable
private fun Preview() {
    PaginaContrasenyaOlvidada()

}