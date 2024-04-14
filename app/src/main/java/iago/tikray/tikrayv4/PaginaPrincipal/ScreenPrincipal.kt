package iago.tikray.tikrayv4.PaginaPrincipal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import iago.tikray.tikrayv4.R

@Composable
fun ScreenPrincipal(navigationController: NavHostController) {
    ConstraintLayout(modifier = Modifier
        .background(colorResource(id = R.color.tikrayColor1))
        .fillMaxSize()) {
        val (logo, titulo, boton1, boton2) = createRefs()
        val margenSuperior = createGuidelineFromTop(0.25f)

        Image(painter = painterResource(id = R.drawable.logo_empresa), contentDescription = "Foto logo empresa" , modifier = Modifier.constrainAs(logo){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(margenSuperior)
        })

        Button(onClick = { navegarBoton1(navigationController) },
            Modifier
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp)
                .constrainAs(boton1) {
                    top.linkTo(logo.bottom, margin = 100.dp)
                }) {
            Text(text = "SIGN UP", )

        }
        Button(onClick = { navegarBoton2(navigationController) },
            Modifier
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp)
                .constrainAs(boton2) {
                    top.linkTo(boton1.bottom, margin = 5.dp)
                }) {
            Text(text = "LOGIN", )

        }










    }

}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    ConstraintLayout(modifier = Modifier.background(colorResource(id = R.color.tikrayColor1))) {

    }

}
