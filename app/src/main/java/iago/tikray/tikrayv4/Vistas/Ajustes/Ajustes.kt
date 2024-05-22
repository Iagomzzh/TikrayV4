package iago.tikray.tikrayv4.Vistas.Ajustes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
import iago.tikray.tikrayv4.Vistas.MenuNavegacion
import iago.tikray.tikrayv4.Vistas.Register.Colorss1

@Composable
fun AjustesPantalla(navigation:NavHostController, ajustesViewModel: AjustesViewModel) {

    val mail by ajustesViewModel.userMail.observeAsState(initial = "nulal")
    ajustesViewModel.userMail()

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.tikrayColor1))) {



        val (menuNavegacion, image, nombreCompleto, telefono, botonLogOut, menuActividad) = createRefs()
        val marginTop = createGuidelineFromTop(0.1f)

        Box(modifier = Modifier.constrainAs(menuNavegacion){
            bottom.linkTo(parent.bottom)
        }) {
            MenuNavegacion(navigation)


        }

        Box(modifier = Modifier.constrainAs(menuActividad) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.top)
        }) {
            ajustesViewModel.DropDown()
        }

        Image(painter = painterResource(id = R.drawable.add_profile_image),
            contentDescription = " ",
            Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(200.dp))
                .background(
                    Color.White
                )
                .clickable { }
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(marginTop)


                })


        Button(onClick = { ajustesViewModel.logOut(navigation) }, Modifier.constrainAs(botonLogOut){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(menuNavegacion.top, margin = 30.dp)
        }) {
            Text(text = "Logout")
            
        }

        OutlinedTextField(value = mail, onValueChange ={}, modifier = Modifier.constrainAs(nombreCompleto){
            top.linkTo(image.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)


        },
            readOnly = true,
            colors = Colorss1())






    }






    
}


@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    AjustesPantalla(rememberNavController(), AjustesViewModel())

}