package iago.tikray.tikrayv4.RecuperarContrasenya

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R

@Composable
fun PaginaContrasenyaOlvidada(navigationController: NavHostController) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))) {



        Text(text = "Hola", color = Color.White)
    }




}

@Preview
@Composable
private fun Preview() {
    PaginaContrasenyaOlvidada(navigationController = rememberNavController())

}