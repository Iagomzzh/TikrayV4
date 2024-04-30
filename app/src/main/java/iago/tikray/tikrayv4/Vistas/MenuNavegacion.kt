package iago.tikray.tikrayv4.Vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.PersonPinCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StickyNote2
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.Navegacion.Ruta
import iago.tikray.tikrayv4.R

@Composable
fun MenuNavegacion(navigation: NavHostController) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(colorResource(id = R.color.tikrayColor1))
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (iconoHome, iconoSettings, iconoNote) = createRefs()
            val startMargin = createGuidelineFromStart(0.05f)
            val endMargin = createGuidelineFromEnd(0.05f)




            Icon(imageVector = Icons.Filled.Home,
                contentDescription = "botonHome",
                tint = Color.White,
                modifier = Modifier

                    .size(100.dp)
                    .clickable { navigation.navigate(Ruta.MenuEntrada.route) }
                    .constrainAs(iconoHome) {
                        start.linkTo(startMargin)
                        top.linkTo(parent.top)


                    })

            Icon(
                imageVector = Icons.Filled.PersonPinCircle,
                contentDescription = "iconoFichar",
                tint = Color.White,
                modifier = Modifier
                    .size(100.dp)
                    .clickable { navigation.navigate(Ruta.Fichar.route) }
                    .constrainAs(iconoNote) {
                        start.linkTo(iconoHome.start)
                        end.linkTo(iconoSettings.end)
                    }
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.Transparent)

                    .constrainAs(iconoSettings) {
                        end.linkTo(endMargin)
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "botonHome",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { navigation.navigate(Ruta.Ajustes.route) }
                        .size(100.dp)

                )
            }


        }


    }


}

@Preview
@Composable
private fun Preview() {
    MenuNavegacion(rememberNavController())

}