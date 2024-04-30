package iago.tikray.tikrayv4.Vistas.Ajustes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.MenuNavegacion

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AjustesPantalla(navigation:NavHostController, ajustesViewModel: AjustesViewModel) {

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))) {



        val (menuNavegacion, image, nombreCompleto, telefono, botonLogOut ) = createRefs()

        Box(modifier = Modifier.constrainAs(menuNavegacion){
            bottom.linkTo(parent.bottom)
        }) {
            MenuNavegacion(navigation)


        }


        Button(onClick = { ajustesViewModel.logOut(navigation) }, Modifier.constrainAs(botonLogOut){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(menuNavegacion.top, margin = 30.dp)
        }) {
            Text(text = "Logout")
            
        }



    }




    
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    AjustesPantalla(rememberNavController(), AjustesViewModel())

}