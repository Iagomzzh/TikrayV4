package iago.tikray.tikrayv4.Vistas.MenuEntrada

import android.content.ClipData.Item
import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.compose.rememberNavController
import iago.tikray.tikrayv4.R
import java.time.format.TextStyle

@Composable
fun MenuEntrada(
    menuEntradaViewModel: MenuEntradaViewModel,
    navigationController: NavHostController
) {


    LazyColumn(Modifier.fillMaxSize()) {

        item { EmpleadosReciclyerView(menuEntradaViewModel = menuEntradaViewModel) }
        }

    }












@Composable
fun EmpleadosReciclyerView(menuEntradaViewModel: MenuEntradaViewModel) {
    val indice: Int by menuEntradaViewModel.indice.observeAsState(initial = 0)
    val nombreCompletoDB: List<String> by menuEntradaViewModel.nombreCompletoDB.observeAsState(initial = listOf("a"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            val (imagen, nombre, telefono, puesto, estado, correo, horario) = createRefs()

            val name = menuEntradaViewModel.nombreCompletoDB

            Image(painter = painterResource(id = R.drawable.add_profile_image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(200.dp))
                    .background(
                        Color.White
                    )
                    .constrainAs(imagen) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                    })










            Text(
                text = ,
                style = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 19.sp),
                modifier = Modifier.constrainAs(nombre) {
                    start.linkTo(imagen.start, margin = 100.dp)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 20.dp)


                })


            Text(text = "iagomzzh@gmail.com",  color =  Color.White,  modifier = Modifier.constrainAs(correo) {
                start.linkTo(nombre.start)
                top.linkTo(nombre.bottom, margin = 10.dp)
            }
            )

   Text(text = "telf: 693940882",  color =  Color.White,  modifier = Modifier.constrainAs(telefono) {
                start.linkTo(nombre.start)
                top.linkTo(correo.bottom, margin = 10.dp)
            }
            )

 Text(text = "estado: Activo",  color =  Color.White,  modifier = Modifier.constrainAs(estado) {
                start.linkTo(imagen.start)
                end.linkTo(imagen.end)
                top.linkTo(imagen.bottom, margin = 5.dp)
            }
            )
    Text(text = "informático",  color =  Color.White,  modifier = Modifier.constrainAs(puesto) {
                start.linkTo(imagen.start)
                end.linkTo(imagen.end)
                bottom.linkTo(imagen.top, margin = 5.dp)
            }
            )




        }


    }


}





//@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
//    MenuEntrada( rememberNavController())

}