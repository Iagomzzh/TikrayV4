package iago.tikray.tikrayv4.Vistas.Fichar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.MenuNavegacion

@Composable
fun Fichar(ficharModelView: FicharModelView, navigation:NavHostController) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))){

        val (menuNavegacion, botonFichar) = createRefs()


        Button(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(botonFichar){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(menuNavegacion.top)
        } ) {
            Text(text = "Fichar")

        }




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