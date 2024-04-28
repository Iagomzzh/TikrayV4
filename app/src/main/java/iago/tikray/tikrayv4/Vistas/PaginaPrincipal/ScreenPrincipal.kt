package iago.tikray.tikrayv4.Vistas.PaginaPrincipal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.ui.theme.Typography

@Composable
fun ScreenPrincipal(navigationController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier
            .background(colorResource(id = R.color.tikrayColor1))
            .fillMaxSize()
    ) {
        val customTextStyle = TextStyle(fontFamily = FontFamily(
            Font(R.font.kodemono_semibold, FontWeight.Bold),
            Font(R.font.kodemonor_egular, FontWeight.Normal),
            Font(R.font.kodemono_bold, FontWeight.ExtraBold),
        )
        )


        // VARIABLES PARA LA COLOCACIÓN DE LOS ELEMENTOS EN EL CONSTRAINT LAYOUT

        val (logo, titulo, boton1, boton2) = createRefs() // -- REFERENCIAS
        val margenSuperior = createGuidelineFromTop(0.25f) // MARGEN DE ARRIBA

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //LOGO IMAGEN PARTE SUPERIOR

        Image(
            painter = painterResource(id = R.drawable.logo_empresa),
            contentDescription = "Foto logo empresa",
            modifier = Modifier.constrainAs(logo) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(margenSuperior)
            })

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //TITULO EMPRESA

        Text(
            text = "TikRay",
            style = TextStyle(color = Color.White, fontSize = 35.sp, fontFamily = customTextStyle.fontFamily),
            modifier =
            Modifier.constrainAs(titulo) {
                top.linkTo(logo.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // BOTON PARA IR A LA PANTALLA DE REGISTRO

        Button(onClick = { navegarAlRegister(navigationController) },
            Modifier
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp)
                .constrainAs(boton1) {
                    top.linkTo(logo.bottom, margin = 100.dp)
                }) {
            Text(text = "SIGN UP")
        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // BOTON PARA IR A LA PANTALLA DE LOGIN

        Button(onClick = { navegarAlLogin(navigationController) },
            Modifier
                .fillMaxWidth()
                .padding(start = 75.dp, end = 75.dp)
                .constrainAs(boton2) {
                    top.linkTo(boton1.bottom, margin = 5.dp)
                }) {
            Text(text = "LOGIN")

        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    }
}



// PREVIEW
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    ConstraintLayout(modifier = Modifier.background(colorResource(id = R.color.tikrayColor1))) {
        ScreenPrincipal(rememberNavController())

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
